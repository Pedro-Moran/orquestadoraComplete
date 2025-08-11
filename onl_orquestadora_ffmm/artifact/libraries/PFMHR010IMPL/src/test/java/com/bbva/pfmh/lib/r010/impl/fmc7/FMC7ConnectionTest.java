package com.bbva.pfmh.lib.r010.impl.fmc7;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.ksan.dto.ce01.ContractOut;
import com.bbva.ksan.lib.re01.KSANRE01;
import com.bbva.kusu.dto.users.entity.AliasFavContractEntity;
import com.bbva.kusu.lib.r325.KUSUR325;
import com.bbva.pfmh.dto.fmc7.ffmm.FFMM7;
import com.bbva.pfmh.dto.fmc7.request.FMC7Request;
import com.bbva.pfmh.dto.fmc7.response.FMC7Response;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.FundsNumberTypeIdOutputEnum;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.Fund;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.InvestmentFund;
import com.bbva.pfmh.lib.r010.impl.cics.FMC7Connection;
import com.bbva.pfmh.lib.r015.PFMHR015;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.aop.framework.Advised;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FMC7ConnectionTest {

    @Spy
    private Context context;

    @Mock
    private PFMHR015 pfmhR015;

    @Mock
    private KSANRE01 ksanre01;

    @Mock
    private KUSUR325 kusur325;

    @InjectMocks
    private FMC7Connection fmc7Connection;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = new Context();
        ThreadContext.set(context);
        getObjectIntrospection();
        fmc7Connection.setPfmhR015(pfmhR015);
        fmc7Connection.initializeErrorCodeList();
    }

    private void getObjectIntrospection() throws Exception {
        if (this.pfmhR015 instanceof Advised) {
            Advised advised = (Advised) this.pfmhR015;
            advised.getTargetSource().getTarget();
        }
    }

    @Test
    public void testExecuteFMC7Transaction_ValidInput() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPaginationKey("");
        input.setPageSize(10);


        FFMM7 ffmm7 = new FFMM7();
        ffmm7.setIdContr("00110122998000000412");
        FMC7Response mockResponse = new FMC7Response();
        mockResponse.setFfmm7(Collections.singletonList(ffmm7));
        when(pfmhR015.executeFMC7(any(FMC7Request.class))).thenReturn(mockResponse);
        FMC7Connection spy = Mockito.spy(fmc7Connection);
        doReturn(new ContractOut()).when(spy).validateContractKsanRE01(any());
        List<OutputInvestmentFundsDTO> result = spy.executeFMC7Transaction(input);

        assertNotNull(result);
        verify(pfmhR015, times(1)).executeFMC7(any(FMC7Request.class));
    }

    @Test
    public void testExecuteFMC7Transaction_NullInput() {
        List<OutputInvestmentFundsDTO> result = fmc7Connection.executeFMC7Transaction(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pfmhR015, never()).executeFMC7(any(FMC7Request.class));
    }

    @Test
    public void testExecuteFMC7Input() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPaginationKey("");
        input.setPageSize(10);

        FMC7Response mockResponse = new FMC7Response();
        mockResponse.setFfmm7(Collections.emptyList());
        when(pfmhR015.executeFMC7(any(FMC7Request.class))).thenReturn(mockResponse);

        List<OutputInvestmentFundsDTO> result = fmc7Connection.executeFMC7Input(input);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pfmhR015, times(1)).executeFMC7(any(FMC7Request.class));
    }

    @Test
    public void testMapFMC7ouput_EmptyResponse() {
        FMC7Response response = new FMC7Response();
        response.setFfmm7(Collections.emptyList());

        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("123");

        List<OutputInvestmentFundsDTO> result = fmc7Connection.mapFMC7ouput(response, input);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testValidateContractHostLocal_ReturnsTrue() {
        OutputInvestmentFundsDTO dto = new OutputInvestmentFundsDTO();
        InvestmentFund fund = new InvestmentFund();
        fund.setNumber("00112233");
        dto.setData(Collections.singletonList(fund));

        boolean result = fmc7Connection.validateContractHostLocal(dto, "PE00112233");
        assertTrue(result);
    }

    @Test
    public void testMapOutFunds_EmptyResponse() {
        FMC7Response response = new FMC7Response();
        response.setFfmm7(Collections.emptyList());
        List<Fund> result = fmc7Connection.mapOutFunds(response);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMapOutFunds_NullResponse() {
        FMC7Response response = new FMC7Response();
        response.setFfmm7(null);
        List<Fund> result = fmc7Connection.mapOutFunds(response);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMapOutNumberType_ValidInput() {
        String ctipnum = null;
        String dtipnum = "Tipo";

        var result = fmc7Connection.mapOutNumberType(ctipnum, dtipnum);

        assertEquals("Tipo", result.getName());
    }

    @Test
    public void testMapOutNumberType_NullInput() {
        var result = fmc7Connection.mapOutNumberType(null, null);

        assertNull(result);
    }

    @Test
    public void testMatchErrorCodeHost_ValidCode() {
        FMC7Response response = new FMC7Response();
        response.setHostAdviceCode("FME2026");

        String result = fmc7Connection.matchErrorCodeHost(response);

        assertEquals("PFMHFME2026", result);
    }

    @Test
    public void testMatchErrorCodeHost_InvalidCode() {
        FMC7Response response = new FMC7Response();
        response.setHostAdviceCode("9999");
        String result = fmc7Connection.matchErrorCodeHost(response);
        assertEquals("PFMHFME2026", result); // Default error
    }

    @Test
    public void testMapOutFunds_ValidResponse() {
        FMC7Response response = new FMC7Response();
        FFMM7 ffmm7 = new FFMM7();
        ffmm7.setIdContr("00110122998000000412");
        ffmm7.setNumCuot(BigDecimal.valueOf(100));
        ffmm7.setSalDisp(BigDecimal.valueOf(2000.0));
        ffmm7.setdMonEsd("USD");
        ffmm7.setIdSubPr("0031");
        ffmm7.setdSubPro("MULT.EST.CONS.S");
        ffmm7.setIdMonFn("USD");
        ffmm7.setSalCont(BigDecimal.valueOf(5000.0));
        ffmm7.setValCuot(BigDecimal.valueOf(50.0));
        ffmm7.setcTipNum("L");
        ffmm7.setdTipNum("CODIGO INTERNO DEL BBVA");
        response.setFfmm7(Collections.singletonList(ffmm7));

        List<Fund> result = fmc7Connection.mapOutFunds(response);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        Fund fund = result.get(0);
        assertEquals("0031", fund.getFundId());
        assertEquals(100, fund.getOwnedShares().intValue());
        assertEquals(5000.0, fund.getFundPosition().getAmount().doubleValue(), 0.01);
        assertEquals("USD", fund.getFundPosition().getCurrency());
        assertEquals("MULT.EST.CONS.S", fund.getTitle().getName());
        assertEquals("USD", fund.getCurrency().getId());
        assertEquals(2000.0, fund.getAvailableFundPosition().getAmount().doubleValue(), 0.01);
        assertEquals(50.0, fund.getNetAssetValue().getAmount().doubleValue(), 0.01);
    }

    @Test
    public void testExecuteFMC7Input_HostAdviceCodeNotNull() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPaginationKey("key123");
        input.setPageSize(10);

        FMC7Response mockResponse = new FMC7Response();
        mockResponse.setHostAdviceCode("2026");
        mockResponse.setHostMessage("Error en la ejecución");
        when(pfmhR015.executeFMC7(any(FMC7Request.class))).thenReturn(mockResponse);
        List<OutputInvestmentFundsDTO> result = fmc7Connection.executeFMC7Input(input);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(pfmhR015, times(1)).executeFMC7(any(FMC7Request.class));
    }
    @Test
    public void testMapOutNumberType_CtipnumNotEmpty() {
        String ctipnum = "CODIGO INTERNO DEL BBVA";
        String dtipnum = "CODIGO INTERNO DEL BBVA";

        // Simula el valor esperado según la lógica de FundsNumberTypeIdOutputEnum
        Object expectedId = FundsNumberTypeIdOutputEnum.getValueFromKey(ctipnum);

        var result = fmc7Connection.mapOutNumberType(ctipnum, dtipnum);

        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertEquals("CODIGO INTERNO DEL BBVA", result.getName());
    }

    @Test
    public void testValidarContratoEnKsanYHost_True() {
        String contratoGlobal = "PE00112233";

        ContractOut contractOut = new ContractOut(); // Simula respuesta de KSAN
        when(ksanre01.executeGetContract(any(), any(), any(), any())).thenReturn(contractOut);

        OutputInvestmentFundsDTO dto = new OutputInvestmentFundsDTO();
        InvestmentFund fund = new InvestmentFund();
        fund.setNumber("00112233"); // sin el PE
        dto.setData(Collections.singletonList(fund));

        boolean result = fmc7Connection.validarContratoEnKsanYHost(contratoGlobal, dto);
        assertTrue(result);
    }

    @Test
    public void testGetVisible_ReturnsTrue() {
        AliasFavContractEntity entity = new AliasFavContractEntity();
        entity.setgVisibleContractIndType("true");
        when(kusur325.executeGetAliasFavoriteContractsList(any(), any()))
                .thenReturn(Collections.singletonList(entity));

        boolean result = fmc7Connection.getVisible("PE00112233", "user123");

        assertTrue(result);
    }
}