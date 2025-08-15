package com.bbva.kffm.lib.r002.mappers;

import com.bbva.kffm.dto.c001.commons.ContractIn;
import com.bbva.kffm.dto.c001.financing.in.RequestFinancingTransactions;
import com.bbva.kffm.lib.r002.utils.LoggerWrapper;

import java.util.ArrayList;
import java.util.List;

public class MapperKSANR101 {

	private MapperKSANR101() {
		super();
	}

	/**
	 * METODO QUE MAPEA LA ENTRADA A KSANR101 PARA OBTENER LOS CONTRATOS DE CUENTAS USANDO LOS CONTRATOS DE LA REQUEST
	 *
	 * @param requestFinancingTransactions Datos de la request
	 * @return Datos necesarios para invocar a KSANR101
	 */
	public static List<String> mapIn(final RequestFinancingTransactions requestFinancingTransactions, final Class<?> originClass) {
		List<String> contractList = new ArrayList<>();

		if (null != requestFinancingTransactions.getContracts() && !requestFinancingTransactions.getContracts().isEmpty()) {
			for (ContractIn requestContract : requestFinancingTransactions.getContracts()) {
				contractList.add(requestContract.getId());
			}
		}

		LoggerWrapper.infoMapInComponent(MapperKSANR101.class.getSimpleName(), contractList, originClass);

		return contractList;
	}

}
