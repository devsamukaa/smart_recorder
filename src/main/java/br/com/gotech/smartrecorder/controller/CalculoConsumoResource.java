package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.*;
import br.com.gotech.smartrecorder.entity.business.BusinessConsumo;
import br.com.gotech.smartrecorder.helper.DateHelper;
import br.com.gotech.smartrecorder.repository.*;
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

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    @Autowired
    BandeiraRepository bandeiraRepository;

    @GetMapping("/mensal")
    public BusinessConsumo calculoConsumoMensal(@RequestParam int mes, @RequestParam int ano, @RequestParam Long cdInstalacao, @RequestParam boolean isMedicaoDispositivo) {

        BusinessConsumo businessConsumo = new BusinessConsumo();
        ContaLuzEntity contaLuzEntity = contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(cdInstalacao);

        mes = mes == 0 ? 1 : mes;
        int mesAnterior = mes == 0 ? 12 : mes - 1;

        Calendar dataInicioMedicao = Calendar.getInstance();
        Calendar dataTerminoMedicao = Calendar.getInstance();

        if(isMedicaoDispositivo){
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


            if(medicoesNoPeriodo.size() > 0) {

                for (MedicaoFaseEntity medicaoFase : medicoesNoPeriodo) {
                    businessConsumo.setKwh(this.IncrementaKwh(businessConsumo.getKwh(), medicaoFase.getKwh()));
                }

                businessConsumo = this.calculaCustoEnergia(businessConsumo.getKwh(), cdInstalacao);
            }

            return businessConsumo;

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
                businessConsumo = this.calculaCustoEnergia(businessConsumo.getKwh(), cdInstalacao);

            }else{
                return businessConsumo;
            }
        }
        return businessConsumo;

    }

    public Double IncrementaKwh(Double kwhAtual, Double kwhNovo){

        return kwhAtual + kwhNovo;

    }

    public BusinessConsumo calculaCustoEnergia(Double kwh, Long cdInstalacao){

        BusinessConsumo consumoResponse = new BusinessConsumo();

        ContaLuzEntity contaLuzEntity = contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(cdInstalacao);
        InstalacaoEntity instalacaoEntity = instalacaoRepository.findById(cdInstalacao).get();
        TipoHabitacaoEntity tipoHabitacaoEntity = instalacaoEntity.getTipoHabitacao();
        BandeiraEntity bandeiraEntity = bandeiraRepository.findByIsBandeiraAtiva(true);

        final Double tusd = tipoHabitacaoEntity.getValorTusd();
        final Double te = tipoHabitacaoEntity.getValorTe();
        Double icms = 0.0;

        if(kwh <= 90) {
            icms = 0.0;
        }else if (kwh > 90 && kwh <= 200) {
            icms = 0.12;
        }else{
            icms = 0.25;
        }

        consumoResponse.setKwh(kwh);
        consumoResponse.setIcms(roundByTwoDecimals(icms * 100));

        final Double custoUsoSistDistribuicaoTotal = kwh * tusd * (1 + icms);
        consumoResponse.setCustoUsoSistDistribuicaoTotal(
                roundByTwoDecimals(custoUsoSistDistribuicaoTotal)
        );

        final Double custoUsoSistDistribuicaoIcms = consumoResponse.getCustoUsoSistDistribuicaoTotal() * icms;
        consumoResponse.setCustoUsoSistDistribuicaoIcms(
                roundByTwoDecimals(custoUsoSistDistribuicaoIcms)
        );

        final Double custoEnergiaTeTotal = kwh * te * (1 + icms);
        consumoResponse.setCustoEnergiaTeTotal(
                roundByTwoDecimals(custoEnergiaTeTotal)
        );

        final Double custoEnergiaTeIcms = consumoResponse.getCustoEnergiaTeTotal() * icms;
        consumoResponse.setCustoEnergiaTeIcms(
                roundByTwoDecimals(custoEnergiaTeIcms)
        );

        final Double adicionalBandeiraTotal = kwh * bandeiraEntity.getValorAdicional() * (1 + icms);
        consumoResponse.setCustoAdicionalBandeiraTotal(
                roundByTwoDecimals(adicionalBandeiraTotal)
        );

        final Double custoAdicionalBandeiraIcms = consumoResponse.getCustoAdicionalBandeiraTotal() * icms;
        consumoResponse.setCustoAdicionalBandeiraIcms(
                roundByTwoDecimals(
                        custoAdicionalBandeiraIcms
                )
        );

        consumoResponse.setBandeira(bandeiraEntity);

        Double totalCustoSemIcmsPisCofins = custoUsoSistDistribuicaoTotal + custoEnergiaTeTotal + adicionalBandeiraTotal;
        Double totalIcmsSemPisCofins = custoUsoSistDistribuicaoIcms + custoEnergiaTeIcms + custoAdicionalBandeiraIcms;

        totalCustoSemIcmsPisCofins = totalCustoSemIcmsPisCofins - totalIcmsSemPisCofins;

        final Double pis = randomByRange(0.004, 0.0099);
        final Double cofins = randomByRange(0.0185, 0.045);

        final Double custoPisPasepTotal = totalCustoSemIcmsPisCofins * pis;
        consumoResponse.setCustoPisPasepTotal(
                roundByTwoDecimals(custoPisPasepTotal)
        );

        final Double custoPisPasepIcms = consumoResponse.getCustoPisPasepTotal() * icms;
        consumoResponse.setCustoPisPasepIcms(
                roundByTwoDecimals(custoPisPasepIcms)
        );

        final Double custoCofinsTotal = totalCustoSemIcmsPisCofins * cofins;
        consumoResponse.setCustoCofinsTotal(
                roundByTwoDecimals(custoCofinsTotal)
        );

        final Double custoCofinsIcms = consumoResponse.getCustoCofinsTotal() * icms;
        consumoResponse.setCustoCofinsIcms(
                roundByTwoDecimals(custoCofinsIcms)
        );

        final Double valorCip = contaLuzEntity.getValorCip();
        consumoResponse.setCustoCipCosip(valorCip);

        final Double totalTodosImpostos = totalIcmsSemPisCofins + custoPisPasepIcms + custoCofinsIcms;
        consumoResponse.setTotalTributos(roundByTwoDecimals(totalTodosImpostos));

        final Double totalCustoComTodosImpostos = totalCustoSemIcmsPisCofins + totalIcmsSemPisCofins + custoPisPasepTotal + custoCofinsTotal + valorCip;
        consumoResponse.setCustoTotal(roundByTwoDecimals(totalCustoComTodosImpostos));

        return consumoResponse;
    }

    Double roundByTwoDecimals (Double value) {
        return Math.round(value * 100.0 ) / 100.0 ;
    }

    Double randomByRange(Double rangeMin, Double rangeMax) {
        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        return  randomValue;
    }
}
