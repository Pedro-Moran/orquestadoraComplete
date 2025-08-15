package com.bbva.kffm.lib.r002.mappers;

import com.bbva.j6ea.dto.transactions.accounts.Transaction;
import com.bbva.j6ea.dto.transactions.accounts.Transactions;
import com.bbva.j6ec.dto.cards.transactions.TransactionList;
import com.bbva.j6ec.dto.cards.transactions.TransactionListContainer;
import com.bbva.kffm.dto.c001.financing.out.FinancingTransaction;
import com.bbva.kffm.dto.c001.financing.out.ResponseFinancingTransactions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MapperKFFMR002 {

	private MapperKFFMR002() {
		super();
	}

	/**
	 * METODO QUE MAPEA LA SALIDA DEL SERVICIO
	 *
	 * @param response Respuesta del servicio previa al mapeo
	 * @return Respuesta del servicio mapeada y ordenada por importe y fecha descendentes
	 */
	public static ResponseFinancingTransactions mapOut(ResponseFinancingTransactions response, final Transactions responseJ6EAR021,
													   final TransactionListContainer responseJ6ECR021) {
		List<FinancingTransaction> financingTransactions = new ArrayList<>();

		// Mapeamos los movimientos de las salidas de las librerias de cuentas y tarjetas
		mapAccountTransactions(financingTransactions, responseJ6EAR021);
		mapCardTransactions(financingTransactions, responseJ6ECR021);
		
		// Ordenamos las fechas por importe y fecha descendente
		if (!financingTransactions.isEmpty()) {
			financingTransactions.sort(Comparator.comparing((FinancingTransaction e) -> e.getOperationAmounts().get(0).getAmount()));
		}

		response.setFinancingTransactions(financingTransactions);

		return response;
	}

	/**
	 * METODO QUE MAPEA EL LISTADO DE TRANSACCIONES DE CUENTAS QUE SERAN DEVUELTAS EN LA SALIDA DEL SERVICIO
	 *
	 * @param financingTransactions Listado de transacciones actuales
	 * @param responseJ6EAR021 Listado de transacciones de cuentas devueltas por la libreria J6EAR021
	 */
	private static void mapAccountTransactions(List<FinancingTransaction> financingTransactions, final Transactions responseJ6EAR021) {
		MapperTransaction mapperTransactions = new MapperTransactionImpl();
		
		if (null != responseJ6EAR021) {
			for (Transaction accountTransaction : responseJ6EAR021.getTransactionList()) {
				FinancingTransaction transaction = mapperTransactions.mapAccountTransaction(accountTransaction);
				
				financingTransactions.add(transaction);
			}
		}
	}

	/**
	 * METODO QUE MAPEA EL LISTADO DE TRANSACCIONES DE TARJETAS QUE SERAN DEVUELTAS EN LA SALIDA DEL SERVICIO
	 *
	 * @param financingTransactions Listado de transacciones actuales
	 * @param responseJ6ECR021 Listado de transacciones de tarjetas devueltas por la libreria J6ECR021
	 */
	private static void mapCardTransactions(List<FinancingTransaction> financingTransactions, final TransactionListContainer responseJ6ECR021) {
		MapperTransaction mapperTransactions = new MapperTransactionImpl();
		
		if (null != responseJ6ECR021) {
			for (TransactionList cardTransaction : responseJ6ECR021.getTransactionList()) {
				FinancingTransaction transaction = mapperTransactions.mapCardTransaction(cardTransaction);
				
				financingTransactions.add(transaction);
			}
		}
	}
	
}
