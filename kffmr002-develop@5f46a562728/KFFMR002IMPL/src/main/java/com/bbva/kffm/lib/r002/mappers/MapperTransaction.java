package com.bbva.kffm.lib.r002.mappers;

import com.bbva.j6ea.dto.transactions.accounts.Transaction;
import com.bbva.j6ec.dto.cards.transactions.TransactionList;
import com.bbva.kffm.dto.c001.financing.out.FinancingTransaction;

public interface MapperTransaction {
	
	/**
	 * METODO QUE MAPEA UNA TRANSACCION DEVUELTA POR LA LIBRERIA J6EAR021
	 *
	 * @param accountTransaction Transaccion devuelta por la libreria
	 * @return Transaccino mapeada para la salida del servicio
	 */
	FinancingTransaction mapAccountTransaction(Transaction accountTransaction);
	
	/**
	 * METODO QUE MAPEA UNA TRANSACCION DEVUELTA POR LA LIBRERIA J6ECR021
	 *
	 * @param cardTransaction Transaccion devuelta por la libreria
	 * @return Transaccino mapeada para la salida del servicio
	 */
	FinancingTransaction mapCardTransaction(TransactionList cardTransaction);
	
}
