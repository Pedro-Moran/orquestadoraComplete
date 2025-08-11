package com.bbva.pfmh.lib.r010.impl.cics;

import com.bbva.elara.library.AbstractLibrary;
import com.bbva.ksan.dto.ce01.ContractOut;
import com.bbva.ksan.lib.re01.KSANRE01;
import com.bbva.kusu.dto.users.entity.AliasFavContractEntity;
import com.bbva.kusu.lib.r325.KUSUR325;
import com.bbva.pfmh.dto.fmc7.ffmm.FFMM7;
import com.bbva.pfmh.dto.fmc7.request.FMC7Request;
import com.bbva.pfmh.dto.fmc7.response.FMC7Response;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.FundsNumberTypeIdOutputEnum;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;

import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.InvestmentFundNumberType;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.InvestmentFundType;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.AvailableFundPosition;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.InvestmentFund;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.FundPosition;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.FundCurrency;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.NetAssetValue;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.Title;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.Fund;
import com.bbva.pfmh.lib.r010.impl.utils.ValidationUtils;
import com.bbva.pfmh.lib.r015.PFMHR015;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FMC7Connection extends AbstractLibrary {

    private static final Logger LOGGER = LoggerFactory.getLogger(FMC7Connection.class);
    private PFMHR015 pfmhR015;
    private KSANRE01 ksanre01;
    private KUSUR325 kusur325;
    private List<String> uniqueErrorCodes;
    private String defaultError;
    private static final String INVESTMENT_FUND_TYPE_SIMPLE = "SIMPLE";
    private static final String PFMHCUSTOMERID = "Es obligatorio indicar el customerId";
    private static final String PFMH_NOT_DPS = "No existen DPS a mostrar con esos datos ingresados";
    private static final String FME2026 = "FME2026";
    private static final String FME2059 = "FME2059";
    private static final String FME2099 = "FME2099";
    private static final String FME2084 = "FME2084";
    private static final String FME2060 = "FME2060";
    private static final String FME2100 = "FME2100";
    private static final String FME2092 = "FME2092";

    public List<OutputInvestmentFundsDTO> executeFMC7Transaction(InputListInvestmentFundsDTO input) {
        List<OutputInvestmentFundsDTO> response = Collections.emptyList();
        if (!ValidationUtils.validationInputIsNullOrEmpty(input)) {
            LOGGER.info("***** PFMH010Impl - input: {} *****", input);
            LOGGER.info("***** PFMH010Impl - participantDTO: {} *****", input.getCustomerId());
            response = executeFMC7Input(input);
        } else {
            this.addAdviceWithDescription(" PFMH", PFMHCUSTOMERID);
            LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - FFMM  is null *****");
        }
        return response;
    }

    public List<OutputInvestmentFundsDTO> executeFMC7Input(InputListInvestmentFundsDTO input) {
        LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - Start *****");
        LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - request: {}", input);

        FMC7Request request = new FMC7Request();

        if (input.getPageSize() != null) {
            request.setTamPagi(input.getPageSize());
        }

        request.setNumCli(input.getCustomerId());
        request.setIndPagi(input.getPaginationKey());
        LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - request: {}", request);

        FMC7Response response = this.pfmhR015.executeFMC7(request);
        LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - response: {}", response);

        if (response.getHostAdviceCode() != null) {
            this.addAdviceWithDescription(matchErrorCodeHost(response), response.getHostMessage());
            LOGGER.info("***** PFMH010Impl - executeFMC7Transaction - Error at FMC7 execution - Host advice code: {}", response.getHostAdviceCode());
            return Collections.emptyList();
        }

        return mapFMC7ouput(response, input);
    }

    public List<OutputInvestmentFundsDTO> mapFMC7ouput(FMC7Response responsefmc7, InputListInvestmentFundsDTO input) {
        LOGGER.info("***** PFMH010Impl - mapFMC7ouput - Start response: {} *****", responsefmc7);
        List<OutputInvestmentFundsDTO> dtoIntInvestmentFundsList = new ArrayList<>();

        if (responsefmc7.getFfmm7() == null || responsefmc7.getFfmm7().isEmpty()) {
            this.addAdviceWithDescription("PFMH_DPS",PFMH_NOT_DPS);
            return Collections.emptyList();
        }

        for (FFMM7 ffmm7 : responsefmc7.getFfmm7()) {
            if (ffmm7 != null) {
                InvestmentFund investmentFund = new InvestmentFund();
                investmentFund.setNumber(ffmm7.getIdContr());
                investmentFund.setInvestmentFundId(ffmm7.getIdContr());
                investmentFund.setNumberType(mapOutNumberType(ffmm7.getcTipNum(), ffmm7.getdTipNum()));
                investmentFund.setInvestmentFundType(new InvestmentFundType());
                investmentFund.getInvestmentFundType().setId(INVESTMENT_FUND_TYPE_SIMPLE);
                investmentFund.getInvestmentFundType().setName(INVESTMENT_FUND_TYPE_SIMPLE);

                if (ffmm7.getIdSubPr() != null || ffmm7.getNumCuot() != null || ffmm7.getSalCont() != null || ffmm7.getdMonEsd() != null || ffmm7.getdSubPro() != null || ffmm7.getIdMonFn() != null || ffmm7.getSalDisp() != null || ffmm7.getValCuot() != null) {
                    investmentFund.setFunds(mapOutFunds(responsefmc7));
                }

                OutputInvestmentFundsDTO dtoIntInvestmentFund = new OutputInvestmentFundsDTO();
                dtoIntInvestmentFund.setData(Collections.singletonList(investmentFund));
                if(validarContratoEnKsanYHost("PE" + ffmm7.getIdContr(), dtoIntInvestmentFund))
                    dtoIntInvestmentFundsList.add(dtoIntInvestmentFund);

                investmentFund.setVisible(getVisible("PE" + ffmm7.getIdContr(),input.getCustomerId()));
            }
        }

        LOGGER.info("***** PFMH010Impl - mapFMC7ouput - dtoIntInvestmentFundsList: {} *****", dtoIntInvestmentFundsList);
        return dtoIntInvestmentFundsList;
    }

    public boolean getVisible(String globalContractId, String profileId) {
        List<AliasFavContractEntity> contractEntityListIn = new ArrayList<>();
        AliasFavContractEntity contractEntity = new AliasFavContractEntity();
        contractEntity.setGContractId(globalContractId);
        contractEntityListIn.add(contractEntity);

        List<AliasFavContractEntity> outKusuR325 = kusur325.executeGetAliasFavoriteContractsList(
                profileId, contractEntityListIn);
        LOGGER.info("[getVisible] - result of kusu: {}", outKusuR325);
        if (outKusuR325 != null && !outKusuR325.isEmpty()) {
            return Boolean.parseBoolean(outKusuR325.get(0).getgVisibleContractIndType());
        } else {
            return false;
        }
    }

    public List<Fund> mapOutFunds(FMC7Response response) {
        LOGGER.info("***** PFMH010Impl - mapOutFunds - Start FMC7Response: {} *****", response);
        List<Fund> dtoOutList = new ArrayList<>();

        if (response.getFfmm7() == null || response.getFfmm7().isEmpty()) {
            LOGGER.warn("La lista FFMM7  está vacía o es nula.");
            return dtoOutList;
        }
        var ffmm7 = response.getFfmm7().get(0);
        Fund fund = new Fund();
        fund.setFundId(ffmm7.getIdSubPr());
        fund.setOwnedShares(ffmm7.getNumCuot());
        FundPosition fundPosition = new FundPosition();
        fundPosition.setAmount(ffmm7.getSalCont());
        fundPosition.setCurrency(ffmm7.getdMonEsd());
        fund.setFundPosition(fundPosition);
        Title title = new Title();
        title.setId(ffmm7.getIdSubPr());
        title.setName(ffmm7.getdSubPro());
        fund.setTitle(title);
        FundCurrency currency = new FundCurrency();
        currency.setId(ffmm7.getdMonEsd());
        currency.setName(ffmm7.getIdMonFn());
        fund.setCurrency(currency);
        AvailableFundPosition availableFundPosition = new AvailableFundPosition();
        availableFundPosition.setAmount(ffmm7.getSalDisp());
        availableFundPosition.setCurrency(ffmm7.getdMonEsd());
        fund.setAvailableFundPosition(availableFundPosition);
        NetAssetValue netAssetValue = new NetAssetValue();
        netAssetValue.setAmount(ffmm7.getValCuot());
        netAssetValue.setCurrency(ffmm7.getdMonEsd());
        fund.setNetAssetValue(netAssetValue);
        dtoOutList.add(fund);

        LOGGER.info("***** PFMH010Impl - mapOutFunds - Start dtoOutList: {} *****", dtoOutList);
        return dtoOutList;
    }

    public InvestmentFundNumberType mapOutNumberType(final String ctipnum, final String dtipnum) {
        if (StringUtils.isEmpty(ctipnum) && StringUtils.isEmpty(dtipnum)) {
            return null;
        }
        InvestmentFundNumberType dtoOut = new InvestmentFundNumberType();
        if (!StringUtils.isEmpty(ctipnum)) {
            dtoOut.setId(FundsNumberTypeIdOutputEnum.getValueFromKey(ctipnum));
        }
        dtoOut.setName(dtipnum);
        return dtoOut;
    }


    public String matchErrorCodeHost(FMC7Response response) {
        if (uniqueErrorCodes.contains(response.getHostAdviceCode())) {
            return "PFMH" + response.getHostAdviceCode();
        } else {
            return "PFMH" + defaultError;
        }
    }

    public void initializeErrorCodeList() {
        defaultError = FME2026;
        uniqueErrorCodes = Arrays.asList(FME2026, FME2059, FME2099, FME2084, FME2060, FME2100, FME2092);
    }

    public boolean validarContratoEnKsanYHost(String contratoGlobal, OutputInvestmentFundsDTO dto) {
        ContractOut contratoKsan = validateContractKsanRE01(contratoGlobal);
        boolean existeEnHost = validateContractHostLocal (dto, contratoGlobal);
        return contratoKsan != null && existeEnHost;
    }


    public ContractOut validateContractKsanRE01(String globalContractId) {
        LOGGER.info("***** PFMHR010Impl - validateContractKsanRE01 - Start *****");
        ContractOut contractOut = ksanre01.executeGetContract(globalContractId, null, null, null);
        if (contractOut == null) {
            LOGGER.error("No se ha podido obtener el contrato de KSAN");
            return null;
        }
        LOGGER.info("***** PFMHR010Impl - validateContractKsanRE01 - End *****");
        return contractOut;
    }


    public boolean validateContractHostLocal(OutputInvestmentFundsDTO dto, String contratoGlobal) {
        LOGGER.info("***** PFMHR010Impl - validateContractHostLocal - Start *****");
        if (dto == null || dto.getData() == null || dto.getData().isEmpty()) {
            LOGGER.error("El DTO de fondos de inversión no contiene contratos");
            return false;
        }
        String contratoHost = contratoGlobal.substring(2); // Elimina "PE"
        return dto.getData().stream()
                .anyMatch(fund -> contratoHost.equals(fund.getNumber()));
    }

    public void setPfmhR015(PFMHR015 pfmhR015) {
        this.pfmhR015 = pfmhR015;
    }

    public void setKsanre01(KSANRE01 ksanre01) {
        this.ksanre01 = ksanre01;
    }
    public void setKusur325(KUSUR325 kusur325) {
        this.kusur325 = kusur325;
    }
}

