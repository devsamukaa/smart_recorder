package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.business.BusinessConsumo;
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
    public BusinessConsumo calculoConsumoMensal(@RequestParam int mes, @RequestParam int ano, @RequestParam Long cdInstalacao, @RequestParam boolean isMedicaoDispositivo) {

        BusinessConsumo businessConsumo = new BusinessConsumo(0.00,0.00);
        ContaLuzEntity contaLuzEntity = contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(cdInstalacao);

        mes = mes == 0 ? 1 : mes;
        int mesAnterior = mes == 0 ? 12 : mes - 1;
        int diasMedidos = 0;

        Calendar dataInicioMedicao = Calendar.getInstance();
        Calendar dataTerminoMedicao = Calendar.getInstance();

        if(isMedicaoDispositivo){
            try{

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

                        businessConsumo.setKwh(this.IncrementaKwh(businessConsumo.getKwh(), medicaoFase.getKwh()));

                    }

                }

                businessConsumo.setCusto(this.calculaCustoEnergia(businessConsumo.getKwh(), cdInstalacao, diasMedidos));

            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros não foram passados adequadamente");
            }
        }else if(!isMedicaoDispositivo){

            dataInicioMedicao.setTime(DateHelper.parseDate("01/"+mes+"/"+ano));
            int lastDayOfMonth = dataInicioMedicao.getActualMaximum(Calendar.DAY_OF_MONTH);
            dataTerminoMedicao.setTime(DateHelper.parseDate(lastDayOfMonth+"/"+mes+"/"+ano));

            List<MedicaoFaseEntity> listMedicaoFaseEntity = medicaoFaseRepository.findByDataMedicaoBetweenAndInstalacao_CdInstalacaoAndIsMedicaoDispositivoOrderByDataMedicaoDesc(
                dataInicioMedicao.getTime(),
                dataTerminoMedicao.getTime(),
                cdInstalacao,
                isMedicaoDispositivo
            );

            if(listMedicaoFaseEntity.size() > 0) {
                MedicaoFaseEntity medicaoFaseEntity = listMedicaoFaseEntity.get(0);
                if (medicaoFaseEntity == null)
                    return businessConsumo;

                businessConsumo.setKwh(medicaoFaseEntity.getKwhRelogio() - medicaoFaseEntity.getKwhUltimaConta());

                Calendar dataMedicaoEletropaulo = new GregorianCalendar(ano, mesAnterior, contaLuzEntity.getDataValidade().getDate());
                int diasNoMesMedicaoEletropaulo = dataMedicaoEletropaulo.getActualMaximum(Calendar.DAY_OF_MONTH);
                dataMedicaoEletropaulo.add(Calendar.DAY_OF_YEAR, -7);

                if (medicaoFaseEntity.getDataMedicao().getDate() < dataMedicaoEletropaulo.getTime().getDate()) {

                    diasMedidos = medicaoFaseEntity.getDataMedicao().getDate() + diasNoMesMedicaoEletropaulo - dataMedicaoEletropaulo.getTime().getDate();

                } else {
                    diasMedidos = medicaoFaseEntity.getDataMedicao().getDate() - dataMedicaoEletropaulo.getTime().getDate();
                }

                businessConsumo.setCusto(this.calculaCustoEnergia(businessConsumo.getKwh(), cdInstalacao, diasMedidos));
            }else{
                return businessConsumo;
            }
        }
        return businessConsumo;

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
