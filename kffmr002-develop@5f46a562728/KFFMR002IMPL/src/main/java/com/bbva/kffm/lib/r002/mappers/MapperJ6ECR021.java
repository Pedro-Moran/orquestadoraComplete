package com.bbva.kffm.lib.r002.mappers;

import com.bbva.j6ea.lib.r021.J6EAR021;
import com.bbva.kffm.dto.c001.commons.ContractIn;
import com.bbva.kffm.dto.c001.financing.in.RequestFinancingTransactions;
import com.bbva.kffm.lib.r002.utils.LoggerWrapper;

import java.util.ArrayList;
import java.util.List;

public class MapperJ6ECR021 {
	
	private MapperJ6ECR021() {
		super();
	}
	
	/**
	 * METODO QUE MAPEA LOS CONTRATOS PARA LA ENTRADA DE J6ECR021
	 *
	 * @param requestFinancingTransactions Datos de entrada del servicio
	 * @param originClass                  Clase origen
	 * @return Mapeo de contratos para la llamada a J6ECR021
	 */
	public static List<String> mapInContracts(final RequestFinancingTransactions requestFinancingTransactions, final Class<?> originClass) {
		List<String> contractList = new ArrayList<>();
		
		if (null != requestFinancingTransactions && null != requestFinancingTransactions.getContracts() && ! requestFinancingTransactions.getContracts().isEmpty()) {
			for (ContractIn originContract : requestFinancingTransactions.getContracts()) {
				
				contractList.add(originContract.getId());
			}
		}
		
		LoggerWrapper.infoMapInComponent(J6EAR021.class.getSimpleName(), contractList, originClass);
		
		return contractList;
	}
	
}
