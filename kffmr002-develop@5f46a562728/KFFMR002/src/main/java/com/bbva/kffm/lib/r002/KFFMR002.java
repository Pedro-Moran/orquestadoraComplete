package com.bbva.kffm.lib.r002;

import com.bbva.kffm.dto.c001.financing.in.RequestFinancingTransactions;
import com.bbva.kffm.dto.c001.financing.out.ResponseFinancingTransactions;

/**
 * The  interface KFFMR002
 */
public interface KFFMR002 {

	/**
	 * The executeListFinancingTransactions method
	 */
	ResponseFinancingTransactions executeListFinancingTransactions(RequestFinancingTransactions request);

}
