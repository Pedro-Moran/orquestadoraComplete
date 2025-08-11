package com.bbva.pfmh.lib.r015.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.elara.utility.interbackend.cics.InterBackendCicsUtils;
import com.bbva.elara.utility.interbackend.cics.dto.HostAdvice;
import com.bbva.elara.utility.interbackend.cics.dto.HostAttributeResponse;
import com.bbva.elara.utility.interbackend.cics.dto.SendMessageResponse;
import com.bbva.elara.utility.interbackend.cics.dto.Status;
import com.bbva.pfmh.dto.fmc7.constant.PFMHC005Constant;
import com.bbva.pfmh.dto.fmc7.pague.FFMMPagination;
import com.bbva.pfmh.dto.fmc7.request.FMC7Request;
import com.bbva.pfmh.dto.fmc7.response.FMC7Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PFMHR015ImplTest {

	@InjectMocks
	private PFMHR015Impl pfmhR015;

	@Mock
	private InterBackendCicsUtils interBackendCicsUtils;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());
	}

	private SendMessageResponse genereteMockCicsResponse(List<String> formats, List<String> values, List<HostAdvice> hostAdvices, Status status) {
		SendMessageResponse sendMessageResponse;

		SendMessageResponse.Builder builderMessageResponse = SendMessageResponse.newBuilder().setStatus(status);
		for (int i = 0; i < values.size(); i++) {
			HostAttributeResponse hostAttributeResponse;

			HostAttributeResponse.Builder builderAttributeResponse = HostAttributeResponse.newBuilder();
			builderAttributeResponse.setFormat(formats.get(0));
			builderAttributeResponse.setValue(values.get(i));
			hostAttributeResponse = builderAttributeResponse.build();
			builderMessageResponse.addAttributeResponse(hostAttributeResponse);
		}
		builderMessageResponse.addAllHostAdvices(hostAdvices);
		sendMessageResponse = builderMessageResponse.build();

		return sendMessageResponse;
	}

	private FMC7Request getFMC7Request() {
		FMC7Request request = new FMC7Request();
		request.setNumCli("00001171");
		request.setIndPagi("");
		request.setTamPagi(025);
		return request;
	}


	@Test
	public void executeTestOK() {

		List<String> formats = new ArrayList<>();
		formats.add(PFMHC005Constant.HostProperties.FFMM7);
		List<String> values = new ArrayList<>();
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");
		values.add("001101229980000004120000023468104377G0000000005431673GPEN0031BBVA CASH SOLES                                         SOLES                        0000000005431673G0000002314491918GLCODIGO INTERNO DEL BBVA                ");

		List<HostAdvice> advicesList = new ArrayList<>();

		SendMessageResponse sendMessageResponse = genereteMockCicsResponse(formats, values, advicesList, Status.OK);
		Mockito.when(interBackendCicsUtils.invokeCics(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap())).thenReturn(sendMessageResponse);

		FMC7Response response = pfmhR015.executeFMC7(getFMC7Request());
		System.out.println(response);
		Assert.assertNotNull(response);
		Assert.assertEquals(0, pfmhR015.getAdviceList().size());
		Assert.assertEquals("00110122998000000412", response.getFfmm7().get(0).getIdContr());
		Assert.assertEquals(new BigDecimal("234.68104377"), response.getFfmm7().get(0).getNumCuot());
		Assert.assertEquals(new BigDecimal("54316.73"), response.getFfmm7().get(0).getSalDisp());
		Assert.assertEquals("PEN", response.getFfmm7().get(0).getdMonEsd());
		Assert.assertEquals("0031", response.getFfmm7().get(0).getIdSubPr());
		Assert.assertEquals("BBVA CASH SOLES", response.getFfmm7().get(0).getdSubPro().trim());
		Assert.assertEquals("SOLES", response.getFfmm7().get(0).getIdMonFn().trim());
		Assert.assertEquals(new BigDecimal("54316.73"), response.getFfmm7().get(0).getSalCont());
		Assert.assertEquals(new BigDecimal("23.14491918"), response.getFfmm7().get(0).getValCuot());
		Assert.assertEquals("L", response.getFfmm7().get(0).getcTipNum());
		Assert.assertEquals("CODIGO INTERNO DEL BBVA", response.getFfmm7().get(0).getdTipNum().trim());
	}


	@Test
	public void executeTestFatal() {
		List<String> formats = new ArrayList<>();
		List<String> values = new ArrayList<>();
		HostAdvice hostAdvice = new HostAdvice();
		List<HostAdvice> adviceList = new ArrayList<>();
		adviceList.add(hostAdvice);
		SendMessageResponse sendMessageResponse = genereteMockCicsResponse(formats, values, adviceList, Status.FATAL);
		Mockito.when(interBackendCicsUtils.invokeCics(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap())).thenReturn(sendMessageResponse);

		FMC7Response response = pfmhR015.executeFMC7(getFMC7Request());
		Assert.assertNotNull(response);
	}

	@Test
	public void executeTestBusinessException() {
		Mockito.when(interBackendCicsUtils.invokeCics(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap()))
				.thenThrow(new BusinessException("01", true, "Host Transaction Failed;[HostAdvice{code='FMC7', description='NO EXISTEN DATOS A MOSTRAR.                   '}];06 at com.bbva.elara.utility.interbackend.cics.ps10.deformatter.DeformatterCics.validateStatus(DeformatterCics.java:107)", new Throwable()));

		FMC7Response response = pfmhR015.executeFMC7(getFMC7Request());
		Assert.assertNotNull(response);
		Assert.assertEquals("FMC7", response.getHostAdviceCode());
		Assert.assertEquals("NO EXISTEN DATOS A MOSTRAR", response.getHostMessage().trim().replaceAll("\\.$", ""));
	}


	@Test
	public void mapPaginationFFMM() {
		FFMMPagination input = new FFMMPagination();
		input.setIdpagin("123");
		input.setTampagi(10);

		FFMMPagination result = pfmhR015.mapPaginationFFMM(input);

		Assert.assertNotNull(result);

		Assert.assertEquals("123", result.getIdpagin());
		Assert.assertEquals(Integer.valueOf(10), result.getTampagi());
	}

	@Test
	public void testmapPaginationFFMMWithNullInput() {
		FFMMPagination result = pfmhR015.mapPaginationFFMM(null);
		Assert.assertNull(result);
	}
}
