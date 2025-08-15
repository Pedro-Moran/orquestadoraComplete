package com.bbva.kffm.lib.r002.mappers;

import com.bbva.j6ea.dto.transactions.accounts.Contract;
import com.bbva.j6ea.dto.transactions.accounts.TransactionIn;
import com.bbva.j6ea.lib.r021.J6EAR021;
import com.bbva.kffm.lib.r002.utils.LoggerWrapper;
import com.bbva.ksan.dto.c101.ContractDetail;
import com.bbva.ksan.dto.c101.ContractsOut;

import java.util.ArrayList;
import java.util.List;

public class MapperJ6EAR021 {
	
	private MapperJ6EAR021() {
		super();
	}
	
	/**
	 * METODO QUE MAPEA LA ENTRADA NECESARIA PARA INVOCAR A J6EAR021
	 *
	 * @param ksanContracts Entrada del servicio
	 * @param originClass   Clase origen
	 * @return Mapeo de entrada para J6EAR021
	 */
	public static TransactionIn mapIn(final ContractsOut ksanContracts, final Class<?> originClass) {
		TransactionIn request = new TransactionIn();
		
		List<Contract> contractsList = mapContractList(ksanContracts);
		
		request.setContracts(contractsList);
		
		LoggerWrapper.infoMapInComponent(J6EAR021.class.getSimpleName(), request, originClass);
		
		return request;
	}
	
	/**
	 * MAPEO DE CONTRATOS PARA LA ENTRADA DE J6EAR021
	 *
	 * @param originContracts Listado de contratos de entrada del servicio
	 * @return Mapeo de contratos para la llamada a J6EAR021
	 */
	private static List<Contract> mapContractList(final ContractsOut originContracts) {
		List<Contract> contractList = new ArrayList<>();
		
		if (null != originContracts && null != originContracts.getContractList() && ! originContracts.getContractList().isEmpty()) {
			for (ContractDetail contractElement : originContracts.getContractList()) {
				if (Boolean.TRUE.equals(contractElement.getIsContractbbva())) {
					contractList.add(getContractInfo(contractElement));
				}
			}
		}
		
		return contractList;
	}
	
	/**
	 * METODO QUE DEVUELVE LA INFORMACION DEL ID DEL CONTRATO
	 *
	 * @param contractDetail Datos del contrato
	 * @return El contrato a devolver
	 */
	private static Contract getContractInfo(final ContractDetail contractDetail) {
		Contract contract = new Contract();
		
		contract.setId(contractDetail.getInternal().getLocalId());
		
		return contract;
	}
	
}
