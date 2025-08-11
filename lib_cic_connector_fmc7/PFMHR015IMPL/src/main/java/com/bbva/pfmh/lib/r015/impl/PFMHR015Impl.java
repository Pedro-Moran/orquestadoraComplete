package com.bbva.pfmh.lib.r015.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.elara.utility.interbackend.cics.dto.HostAttributeResponse;
import com.bbva.elara.utility.interbackend.cics.dto.SendMessageResponse;
import com.bbva.pfmh.dto.fmc7.constant.PFMHC005Constant;
import com.bbva.pfmh.dto.fmc7.ffmm.FFMM7;
import com.bbva.pfmh.dto.fmc7.pague.FFMMPagination;
import com.bbva.pfmh.dto.fmc7.request.FMC7Request;
import com.bbva.pfmh.dto.fmc7.response.FMC7Response;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PFMHR015Impl extends PFMHR015Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PFMHR015Impl.class);

	@Override
	public FMC7Response executeFMC7(FMC7Request request) {
		return executeInternalApiCicsConector(request);
	}

	private FMC7Response executeInternalApiCicsConector(FMC7Request request) {
		LOGGER.info("***** PFMHR015Impl - executeInternalApiCicsConector START ***** request: {}", request);
		Map<String, Object> params = new HashMap<>();

		if (request.getNumCli() != null) {
			params.put(PFMHC005Constant.HostFMC7FieldsIn.NUMCLIE, request.getNumCli());
		}
		params.put(PFMHC005Constant.HostFMC7FieldsIn.INDPAGI, request.getIndPagi());
		params.put(PFMHC005Constant.HostFMC7FieldsIn.TAMPAGI, request.getTamPagi());

		LOGGER.info("***** PFMHR015Impl - params: {}", params);

		FMC7Response response = new FMC7Response();

		try {
			SendMessageResponse sendMessageResponse = this.interBackendCicsUtils.invokeCics(PFMHC005Constant.HostProperties.CICS_KEY_CONNECTION, PFMHC005Constant.HostProperties.FFMM7, params);
			LOGGER.info("sendMessageResponse :: {}", sendMessageResponse);
			if (sendMessageResponse.getHostAdvices().isEmpty()) {
				LOGGER.info("Status OK");
				this.mapperFMC7(sendMessageResponse.getAttributeResponses(), response);
				LOGGER.info("FMC7Response: {}", response);
			} else {
				LOGGER.info("Response {}", sendMessageResponse);
				LOGGER.info("Code: {} Description: {}", sendMessageResponse.getHostAdvices().get(0).getCode(), sendMessageResponse.getHostAdvices().get(0).getDescription());
				response.setHostAdviceCode(sendMessageResponse.getHostAdvices().get(0).getCode());
				response.setHostMessage(sendMessageResponse.getHostAdvices().get(0).getDescription());
			}
		} catch (BusinessException e) {
			LOGGER.info("[executeInternalApiCicsConector] Error al consumir Host: {}", ExceptionUtils.getStackTrace(e));
			getHostAdvice(response, e);
			LOGGER.info("[executeInternalApiCicsConector] FMC7Response Advice = [{}] , message = [{}]", response.getHostAdviceCode(), response.getHostMessage());
		}
		LOGGER.info("***** PFMHR015Impl - executeInternalApiCicsConector END *****");
		return response;
	}

	private void mapperFMC7(List<HostAttributeResponse> result, FMC7Response response) {
		List<FFMM7> list = new ArrayList<>();

		for (HostAttributeResponse res : result) {
			LOGGER.info("mapperFMC7 - res.getFormat(): {}", res.getFormat());

			if (res.getFormat().trim().equalsIgnoreCase(PFMHC005Constant.HostProperties.FFMM7)) {
				String value = res.getValue();
				int index = 0;

				try {
					FFMM7 ffmm7 = new FFMM7();

					ffmm7.setIdContr(extractString(value, index, 20));
					index += 20;
					ffmm7.setNumCuot(extractBigDecimal(value, index, 17, 8));
					index += 17;
					ffmm7.setSalDisp(extractBigDecimal(value, index, 17, 2));
					index += 17;
					ffmm7.setdMonEsd(extractString(value, index, 3));
					index += 3;
					ffmm7.setIdSubPr(extractString(value, index, 4));
					index += 4;
					ffmm7.setdSubPro(extractString(value, index, 50));
					index += 50;
					ffmm7.setIdMonFn(extractString(value, index, 35));
					index += 35;
					ffmm7.setSalCont(extractBigDecimal(value, index, 17, 2));
					index += 17;
					ffmm7.setValCuot(extractBigDecimal(value, index, 17, 8));
					index += 17;
					ffmm7.setcTipNum(extractString(value, index, 1));
					index += 1;
					ffmm7.setdTipNum(extractString(value, index, 30));

					list.add(ffmm7);
				} catch (BusinessException e) {
					LOGGER.error("Error al procesar el valor: {}. Índices fuera de rango o datos inválidos.", value, e);
				}
			}
		}

		response.setFfmm7(list);
	}

	public FFMMPagination mapPaginationFFMM(final FFMMPagination ffmmPagination) {
		if (ffmmPagination == null) {
			return null;
		}
		FFMMPagination pagination = new FFMMPagination();
		pagination.setIdpagin(ffmmPagination.getIdpagin());
		pagination.setTampagi(ffmmPagination.getTampagi());

		return pagination;
	}

	private String extractString(String value, int startIndex, int length) {
		return safeSubstring(value, startIndex, startIndex + length).trim();
	}

	private BigDecimal extractBigDecimal(String value, int startIndex, int length, int scale) {
		String substring = safeSubstring(value, startIndex, startIndex + length);
		return cleanAndConvertToBigDecimal(substring).movePointLeft(scale);
	}

	private BigDecimal cleanAndConvertToBigDecimal(String value) {
		if (value == null || value.trim().isEmpty()) {
			return BigDecimal.ZERO;
		}
		String cleanedValue = value.trim().replaceAll("\\D", "");
		return new BigDecimal(cleanedValue);
	}

	private String safeSubstring(String value, int beginIndex, int endIndex) {
		if (value == null || beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex) {
			return "";
		}
		return value.substring(beginIndex, endIndex);
	}


	private void getHostAdvice(FMC7Response response, BusinessException e) {
		String[] errors = e.getMessage().split(";");
		String messageAdvice = errors[1].replace("HostAdvice", "").trim();
		messageAdvice = messageAdvice.substring(2, messageAdvice.length() - 2);
		Map<String, String> map = Arrays.stream(messageAdvice.split(","))
				.map(s -> s.replace("'", "").split("="))
				.collect(Collectors.toMap(s -> s[0].trim(), s -> s[1].trim()));
		response.setHostAdviceCode(map.get("code"));
		response.setHostMessage(map.get("description"));
	}
}
