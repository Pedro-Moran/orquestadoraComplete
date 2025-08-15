package com.bbva.kffm.lib.r002.impl;

import com.bbva.j6ea.dto.transactions.accounts.Transactions;
import com.bbva.j6ea.lib.r021.J6EAR021;
import com.bbva.j6ec.dto.cards.transactions.TransactionListContainer;
import com.bbva.j6ec.lib.r021.J6ECR021;
import com.bbva.kffm.dto.c001.financing.in.RequestFinancingTransactions;
import com.bbva.kffm.dto.c001.financing.out.ResponseFinancingTransactions;
import com.bbva.kffm.lib.r002.mappers.MapperJ6EAR021;
import com.bbva.kffm.lib.r002.mappers.MapperJ6ECR021;
import com.bbva.kffm.lib.r002.mappers.MapperKFFMR002;
import com.bbva.kffm.lib.r002.mappers.MapperKSANR101;
import com.bbva.kffm.lib.r002.utils.LoggerWrapper;
import com.bbva.kffm.lib.r002.utils.LoggerWrapper.Level;
import com.bbva.ksan.dto.c101.ContractsOut;

/**
 * The KFFMR002Impl class
 */
public class KFFMR002Impl extends KFFMR002Abstract {
	
	/**
	 * METODO QUE BUSCA EL LISTADO DE TRANSACCIONES FINANCIABLES TANTO DE CREDITO COMO DE DEBITO
	 *
	 * @param request Datos de la peticion al servicio
	 * @return Listado tratado de transacciones financiables ordenadas por ...
	 */
	@Override
	public ResponseFinancingTransactions executeListFinancingTransactions(final RequestFinancingTransactions request) {

		LoggerWrapper.writeCustomMessage(Level.INFO, "ENTRADA A CLASE", this.getClass());

		ResponseFinancingTransactions response = new ResponseFinancingTransactions();

		// Invocacion a KSAN para obtener los ID de cuentas
		ContractsOut ksanContracts = this.ksanR101.executeGetContracts(MapperKSANR101.mapIn(request, this.getClass()));

		// Inovocacion al servicio de movimientos de cuentas
		Transactions responseJ6EAR021 = invokeJ6EAR021(ksanContracts);

		// Inovocacion al servicio de movimientos de tarjetas usando el listado de contratos de entrada
		TransactionListContainer responseJ6ECR021 = invokeJ6ECR021(request);
		
		return MapperKFFMR002.mapOut(response, responseJ6EAR021, responseJ6ECR021);
	}

	/**
	 * METODO QUE BUSCA LOS MOVIMIENTOS DE CUENTAS DEL CLIENTE SIGUIENDO UNOS PARAMETROS DE ENTRADA
	 *
	 * @param ksanContracts Los datos de entrada solicitados
	 * @return Los movimientos de cuentas solicitados
	 */
	private Transactions invokeJ6EAR021(final ContractsOut ksanContracts) {

		Transactions responseJ6EAR021 = j6eaR021.executeGetTransactionList(MapperJ6EAR021.mapIn(ksanContracts, this.getClass()), Boolean.FALSE);

		LoggerWrapper.infoMapOutComponent(J6EAR021.class.getSimpleName(), responseJ6EAR021, this.getClass());

		if (getAdvice() != null) {
			LoggerWrapper.writeCustomMessage(Level.ERROR, "HA HABIDO UN PROBLEMA EN LA INVOCACION A " + J6EAR021.class.getSimpleName(), this.getClass());
		} else {
			LoggerWrapper.infoCorrectInvocation(J6EAR021.class.getSimpleName(), this.getClass());
		}

		return responseJ6EAR021;
	}

	/**
	 * METODO QUE BUSCA LOS MOVIMIENTOS DE TARJETAS DEL CLIENTE SIGUIENDO UNOS PARAMETROS DE ENTRADA
	 *
	 * @param request Los datos de entrada solicitados
	 * @return Los movimientos de cuentas solicitados
	 */
	private TransactionListContainer invokeJ6ECR021(final RequestFinancingTransactions request) {

		TransactionListContainer responseJ6ECR021 = j6ecR021.executeGetCardTransactionList(MapperJ6ECR021.mapInContracts(request, this.getClass()), null);
		LoggerWrapper.infoMapOutComponent(J6ECR021.class.getSimpleName(), responseJ6ECR021, this.getClass());

		if (getAdvice() != null) {
			LoggerWrapper.writeCustomMessage(Level.ERROR, "HA HABIDO UN PROBLEMA EN LA INVOCACION A " + J6ECR021.class.getSimpleName(), this.getClass());
		} else {
			LoggerWrapper.infoCorrectInvocation(J6ECR021.class.getSimpleName(), this.getClass());
		}

		return responseJ6ECR021;
	}

}
