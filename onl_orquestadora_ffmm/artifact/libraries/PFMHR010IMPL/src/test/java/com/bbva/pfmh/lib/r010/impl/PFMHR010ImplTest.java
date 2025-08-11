package com.bbva.pfmh.lib.r010.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.ksan.dto.ce01.ContractOut;
import com.bbva.ksan.lib.re01.KSANRE01;
import com.bbva.kusu.dto.users.entity.AliasFavContractEntity;
import com.bbva.kusu.lib.r325.KUSUR325;
import com.bbva.pfmh.dto.fmc7.ffmm.FFMM7;
import com.bbva.pfmh.dto.fmc7.response.FMC7Response;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import com.bbva.pfmh.lib.r010.impl.cics.FMC7Connection;
import com.bbva.pfmh.lib.r015.PFMHR015;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PFMHR010ImplTest {

    @Spy
    private Context context;

    @Mock
    private PFMHR015 pfmhR015;

    private PFMHR010Impl pfmhr010Impl;

    private FMC7Connection fmc7Connection;

    @Mock
    private KSANRE01 ksanre01;

    @Mock
    private KUSUR325 kusur325;

    @Mock
    private ApplicationConfigurationService applicationConfigurationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        context = new Context();
        ThreadContext.set(context);

        pfmhr010Impl = new PFMHR010Impl();
        fmc7Connection = new FMC7Connection();

        fmc7Connection.setPfmhR015(pfmhR015);
        fmc7Connection.setKsanre01(ksanre01);
        fmc7Connection.setKusur325(kusur325);
        pfmhr010Impl.setPfmhR015(pfmhR015);
        pfmhr010Impl.setFmc7Connection(fmc7Connection);
        pfmhr010Impl.setApplicationConfigurationService(applicationConfigurationService);
    }

    @Test
    public void testExecuteGetFFMMStatements_ReturnsData() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPageSize(10);
        input.setPaginationKey("");

        Mockito.when(pfmhR015.executeFMC7(Mockito.any())).thenReturn(ouputFMC7());

        ContractOut mockContract = Mockito.mock(ContractOut.class);
        Mockito.when(ksanre01.executeGetContract(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any()
        )).thenReturn(mockContract);

        Mockito.when(kusur325.executeGetAliasFavoriteContractsList(
                Mockito.anyString(),
                Mockito.anyList()
        )).thenReturn(new ArrayList<>());

        List<OutputInvestmentFundsDTO> result = pfmhr010Impl.executeGetFFMMStatements(input);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testExecuteGetFFMMStatements_ReturnsEmptyListWhenResponseIsNullOrEmpty()  {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPageSize(10);
        input.setPaginationKey("");

        FMC7Response mockResponse = Mockito.mock(FMC7Response.class);
        Mockito.when(pfmhR015.executeFMC7(Mockito.any())).thenReturn(mockResponse);
        List<OutputInvestmentFundsDTO> result = pfmhr010Impl.executeGetFFMMStatements(input);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    private FMC7Response ouputFMC7() {
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
        return response;
    }

    @Test
    public void testExecuteGetFFMMStatements_ReturnsEmptyListWhenContractNotFound() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPageSize(10);
        input.setPaginationKey("");

        Mockito.when(pfmhR015.executeFMC7(Mockito.any())).thenReturn(ouputFMC7());

        Mockito.when(ksanre01.executeGetContract(
                Mockito.anyString(),
                Mockito.isNull(String.class),
                Mockito.isNull(String.class),
                Mockito.isNull(String.class)
        )).thenReturn(null);

        Mockito.when(kusur325.executeGetAliasFavoriteContractsList(
                Mockito.anyString(),
                Mockito.anyList()
        )).thenReturn(new ArrayList<>());

        List<OutputInvestmentFundsDTO> result = pfmhr010Impl.executeGetFFMMStatements(input);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testExecuteGetFFMMStatements_ReturnsDataWhenContractFoundAndVisible() {
        // Arrange
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        input.setPageSize(10);
        input.setPaginationKey("");

        Mockito.when(pfmhR015.executeFMC7(Mockito.any()))
                .thenReturn(ouputFMC7());

        ContractOut mockContract = Mockito.mock(ContractOut.class);
        Mockito.when(ksanre01.executeGetContract(
                Mockito.anyString(),
                Mockito.isNull(String.class),
                Mockito.isNull(String.class),
                Mockito.isNull(String.class)
        )).thenReturn(mockContract);

        AliasFavContractEntity mockAlias = new AliasFavContractEntity();
        mockAlias.setgVisibleContractIndType("true");
        Mockito.when(kusur325.executeGetAliasFavoriteContractsList(
                Mockito.anyString(),
                Mockito.anyList()
        )).thenReturn(Collections.singletonList(mockAlias));

        List<OutputInvestmentFundsDTO> result = pfmhr010Impl.executeGetFFMMStatements(input);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(0).getData().get(0).isVisible());
    }
}