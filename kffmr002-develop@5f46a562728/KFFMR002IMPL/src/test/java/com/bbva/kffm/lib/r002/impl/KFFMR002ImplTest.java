package com.bbva.kffm.lib.r002.impl;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.j6ea.dto.transactions.accounts.OperationAmount;
import com.bbva.j6ea.dto.transactions.accounts.Transaction;
import com.bbva.j6ea.dto.transactions.accounts.Transactions;
import com.bbva.j6ea.lib.r021.J6EAR021;
import com.bbva.j6ec.dto.cards.transactions.TransactionList;
import com.bbva.j6ec.dto.cards.transactions.TransactionListContainer;
import com.bbva.j6ec.lib.r021.J6ECR021;
import com.bbva.kffm.dto.c001.financing.in.RequestFinancingTransactions;
import com.bbva.ksan.dto.c101.ContractDetail;
import com.bbva.ksan.dto.c101.ContractsOut;
import com.bbva.ksan.dto.c101.Internal;
import com.bbva.ksan.lib.r101.KSANR101;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.typeManufacturers.IntTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.when;

public class KFFMR002ImplTest {

	@InjectMocks
	private KFFMR002Impl kffmR002;
	
	@Mock
	private KSANR101 ksanr101;
	
	@Mock
	private J6EAR021 j6EAR021;
	
	@Mock
	private J6ECR021 j6ECR021;
	
	private static final PodamFactory factory = new PodamFactoryImpl();

	@Before
	public void setUp() throws Exception {
		TypeManufacturer<Integer> manufacturer = new IntTypeManufacturerImpl() {
			
			@Override
			public Integer getInteger(AttributeMetadata attributeMetadata) {
				
				if (attributeMetadata.getPojoClass() == Timestamp.class) {
					return PodamUtils.getIntegerInRange(0, 999999999);
				} else {
					return super.getInteger(attributeMetadata);
				}
			}
		};
		factory.getStrategy().addOrReplaceTypeManufacturer(int.class, manufacturer);
		
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());
	}

	@Test
	public void executeNullTest(){
		
		ContractsOut ksanContracts = getKsanContracts();
		
		when(ksanr101.executeGetContracts(any())).thenReturn(ksanContracts);
		
		when(j6EAR021.executeGetTransactionList(any(), anyBoolean())).thenReturn(null);
		
		when(j6ECR021.executeGetCardTransactionList(any(), any())).thenReturn(null);
		
		kffmR002.executeListFinancingTransactions(new RequestFinancingTransactions());
		Assert.assertEquals(0, BigInteger.ZERO.intValue());
	}
	
	@Test
	public void executeEmptyTest(){
		
		ContractsOut ksanContracts = getKsanContracts();
		
		when(ksanr101.executeGetContracts(any())).thenReturn(ksanContracts);
		
		Transactions transactions = new Transactions();
		List<Transaction> transaction = new ArrayList<>();
		Transaction transactionItem = new Transaction();
		List<OperationAmount> operationAmounts = new ArrayList<>();
		OperationAmount operationAmount = new OperationAmount();
		operationAmount.setAmount(12.34);
		operationAmounts.add(operationAmount);
		transactionItem.setOperationAmounts(operationAmounts);
		transaction.add(transactionItem);
		transactions.setTransactionList(transaction);
		when(j6EAR021.executeGetTransactionList(any(), anyBoolean())).thenReturn(transactions);
		
		TransactionListContainer transactionListContainer = new TransactionListContainer();
		List<TransactionList> transactionList = new ArrayList<>();
		TransactionList transactionListItem = new TransactionList();
		List<com.bbva.j6ec.dto.cards.transactions.OperationAmount> cardOperationAmounts = new ArrayList<>();
		com.bbva.j6ec.dto.cards.transactions.OperationAmount cardOperationAmount = new com.bbva.j6ec.dto.cards.transactions.OperationAmount();
		cardOperationAmount.setAmount(12.36);
		cardOperationAmounts.add(cardOperationAmount);
		transactionListItem.setOperationAmounts(cardOperationAmounts);
		transactionList.add(transactionListItem);
		transactionListContainer.setTransactionList(transactionList);
		when(j6ECR021.executeGetCardTransactionList(any(), any())).thenReturn(transactionListContainer);
		
		kffmR002.executeListFinancingTransactions(new RequestFinancingTransactions());
		Assert.assertEquals(0, BigInteger.ZERO.intValue());
	}
	
	@Test
	public void executeFullTest() {
		
		when(ksanr101.executeGetContracts(any())).thenReturn(factory.manufacturePojo(ContractsOut.class));
		
		when(j6EAR021.executeGetTransactionList(any(), anyBoolean())).thenReturn(factory.manufacturePojo(Transactions.class));
		when(j6ECR021.executeGetCardTransactionList(any(), any())).thenReturn(factory.manufacturePojo(TransactionListContainer.class));
		
		kffmR002.executeListFinancingTransactions(new RequestFinancingTransactions());
		Assert.assertEquals(0, BigInteger.ZERO.intValue());
	}
	
	private static ContractsOut getKsanContracts() {
		ContractsOut ksanContracts = new ContractsOut();
		
		List<ContractDetail> ksanContractsList = new ArrayList<>();
		ContractDetail ksanContract = new ContractDetail();
		ksanContract.setIsContractbbva(Boolean.TRUE);
		Internal internal1 = new Internal();
		internal1.setLocalId("ES0182061500000000512345678901234567XXXXXXXXX");
		ksanContract.setInternal(internal1);
		ContractDetail ksanContract2 = new ContractDetail();
		ksanContract2.setIsContractbbva(Boolean.FALSE);
		Internal internal2 = new Internal();
		internal2.setLocalId("ES0182061500000000512345678901234568XXXXXXXXX");
		ksanContract2.setInternal(internal2);
		ksanContractsList.add(ksanContract);
		ksanContractsList.add(ksanContract2);
		ksanContracts.setContractList(ksanContractsList);
		
		return ksanContracts;
	}
	
}
