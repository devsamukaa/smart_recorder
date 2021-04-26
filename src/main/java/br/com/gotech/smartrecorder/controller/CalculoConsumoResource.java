package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.ConsumoEntity;
import br.com.gotech.smartrecorder.entity.ContaLuzEntity;
import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import br.com.gotech.smartrecorder.helper.DateHelper;
import br.com.gotech.smartrecorder.repository.ContaLuzRepository;
import br.com.gotech.smartrecorder.repository.MedicaoFaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/calculo_consumo")
public class CalculoConsumoResource {

    @Autowired
    private ContaLuzRepository contaLuzRepository;

    @Autowired
    private MedicaoFaseRepository medicaoFaseRepository;

    @GetMapping("/mensal")
    public ConsumoEntity calculoConsumoMensal(@RequestParam int mes, @RequestParam int ano, @RequestParam Long cdInstalacao, @RequestParam boolean isMedicaoDispositivo) {

        ConsumoEntity consumoEntity = new ConsumoEntity(0.00,0.00);
        ContaLuzEntity contaLuzEntity = contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(cdInstalacao);

        mes = mes == 0 ? 1 : mes;
        int mesAnterior = mes == 0 ? 12 : mes - 1;
        int diasMedidos = 0;

        if(isMedicaoDispositivo){
            try{
                Calendar dataInicioMedicao = Calendar.getInstance();
                Calendar dataTerminoMedicao = Calendar.getInstance();

                dataInicioMedicao.setTime(DateHelper.parseDate(contaLuzEntity.getDataValidade().getDate()+"/"+mesAnterior+"/"+ano));
                dataTerminoMedicao.setTime(DateHelper.parseDate(contaLuzEntity.getDataValidade().getDate()+"/"+mes+"/"+ano));

                dataInicioMedicao.add(Calendar.DAY_OF_YEAR, -7);
                dataTerminoMedicao.add(Calendar.DAY_OF_YEAR, -7);

                List<MedicaoFaseEntity> medicoesNoPeriodo = medicaoFaseRepository.findByDataMedicaoBetweenAndInstalacao_CdInstalacaoAndIsMedicaoDispositivoOrderByDataMedicaoAsc(
                        dataInicioMedicao.getTime(),
                        dataTerminoMedicao.getTime(),
                        cdInstalacao,
                        isMedicaoDispositivo
                );

                int diaMedido = 100;

                if(medicoesNoPeriodo.size() > 0) {

                    for (MedicaoFaseEntity medicaoFase : medicoesNoPeriodo) {

                        if(diaMedido != medicaoFase.getDataMedicao().getDay()){
                            diasMedidos++;
                            diaMedido = medicaoFase.getDataMedicao().getDay();
                        }

                        consumoEntity.setKwh(this.IncrementaKwh(consumoEntity.getKwh(), medicaoFase.getKwh()));

                    }

                }

                consumoEntity.setCusto(this.calculaCustoEnergia(consumoEntity.getKwh(), cdInstalacao, diasMedidos));

            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros não foram passados adequadamente");
            }
        }else if(!isMedicaoDispositivo){

            MedicaoFaseEntity medicaoFaseEntity = medicaoFaseRepository.getByInstalacao_CdInstalacaoAndIsMedicaoDispositivoOrderByDataMedicaoDesc(cdInstalacao, isMedicaoDispositivo);

            consumoEntity.setKwh(medicaoFaseEntity.getKwhRelogio() - medicaoFaseEntity.getKwhUltimaConta());

            Calendar data = new GregorianCalendar(ano, mesAnterior, contaLuzEntity.getDataValidade().getDate());
            data.add(Calendar.DAY_OF_YEAR, -7);

            Calendar data2 = new GregorianCalendar(ano, mesAnterior, contaLuzEntity.getDataValidade().getDate());

            if(medicaoFaseEntity.getDataMedicao().getDate() < data.getTime().getDate()) {

                int daysInMonth = data2.getActualMaximum(Calendar.DAY_OF_MONTH);

                diasMedidos = medicaoFaseEntity.getDataMedicao().getDate() + daysInMonth - data.getTime().getDate();

            }else{
                diasMedidos = medicaoFaseEntity.getDataMedicao().getDate() - data.getTime().getDate();
            }

            consumoEntity.setCusto(this.calculaCustoEnergia(consumoEntity.getKwh(), cdInstalacao, diasMedidos));

        }

        return  consumoEntity;

    }

    public Double IncrementaKwh(Double kwhAtual, Double kwhNovo){

        return kwhAtual + kwhNovo;

    }

    public Double calculaCustoEnergia(Double kwh, Long cdInstalacao, int diasMedidos){

        ContaLuzEntity contaLuzEntity = contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(cdInstalacao);

        Double valorDoKwh = (contaLuzEntity.getValorPago() - contaLuzEntity.getValorTributos()) / contaLuzEntity.getKwhConta();

        Double tributoPorDia = (contaLuzEntity.getValorTributos()) / 30;

        return Math.round( ((kwh * valorDoKwh) + (tributoPorDia * diasMedidos)) * 100.0 ) / 100.0 ;

    }

}
