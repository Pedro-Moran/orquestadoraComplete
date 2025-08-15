package com.bbva.kffm.lib.r002.mappers;

import com.bbva.j6ea.dto.transactions.accounts.AdditionalInformation;
import com.bbva.j6ea.dto.transactions.accounts.Balance;
import com.bbva.j6ea.dto.transactions.accounts.Bank;
import com.bbva.j6ea.dto.transactions.accounts.Category;
import com.bbva.j6ea.dto.transactions.accounts.Channel;
import com.bbva.j6ea.dto.transactions.accounts.Classification;
import com.bbva.j6ea.dto.transactions.accounts.Co2Mark;
import com.bbva.j6ea.dto.transactions.accounts.Comment;
import com.bbva.j6ea.dto.transactions.accounts.CommonAmount;
import com.bbva.j6ea.dto.transactions.accounts.Contract;
import com.bbva.j6ea.dto.transactions.accounts.Detail;
import com.bbva.j6ea.dto.transactions.accounts.ExchangeRate;
import com.bbva.j6ea.dto.transactions.accounts.FinancingType;
import com.bbva.j6ea.dto.transactions.accounts.ItemizeFee;
import com.bbva.j6ea.dto.transactions.accounts.PaymentChannel;
import com.bbva.j6ea.dto.transactions.accounts.Product;
import com.bbva.j6ea.dto.transactions.accounts.ReasonStatus;
import com.bbva.j6ea.dto.transactions.accounts.RewardBenefits;
import com.bbva.j6ea.dto.transactions.accounts.Subproduct;
import com.bbva.j6ea.dto.transactions.accounts.Sustainability;
import com.bbva.j6ea.dto.transactions.accounts.Target;
import com.bbva.j6ea.dto.transactions.accounts.TotalFees;
import com.bbva.j6ea.dto.transactions.accounts.Transaction;
import com.bbva.j6ea.dto.transactions.accounts.TransactionType;
import com.bbva.j6ec.dto.cards.transactions.ItemizeRate;
import com.bbva.j6ec.dto.cards.transactions.Rates;
import com.bbva.j6ec.dto.cards.transactions.TransactionList;
import com.bbva.kffm.dto.c001.commons.Amount;
import com.bbva.kffm.dto.c001.financing.out.AsAmount;
import com.bbva.kffm.dto.c001.financing.out.Attachment;
import com.bbva.kffm.dto.c001.financing.out.Branch;
import com.bbva.kffm.dto.c001.financing.out.Claim;
import com.bbva.kffm.dto.c001.financing.out.Computer;
import com.bbva.kffm.dto.c001.financing.out.Fees;
import com.bbva.kffm.dto.c001.financing.out.FinancingTransaction;
import com.bbva.kffm.dto.c001.financing.out.InternalCode;
import com.bbva.kffm.dto.c001.financing.out.ItemizeRateUnit;
import com.bbva.kffm.dto.c001.financing.out.MoneyFlow;
import com.bbva.kffm.dto.c001.financing.out.NumberType;
import com.bbva.kffm.dto.c001.financing.out.OperationAmount;
import com.bbva.kffm.dto.c001.financing.out.Ratio;
import com.bbva.kffm.dto.c001.financing.out.Reason;
import com.bbva.kffm.dto.c001.financing.out.RelatedContract;
import com.bbva.kffm.dto.c001.financing.out.RewardBenefit;
import com.bbva.kffm.dto.c001.financing.out.Status;
import com.bbva.kffm.dto.c001.financing.out.Subcategory;
import com.bbva.kffm.dto.c001.financing.out.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapperTransactionImpl implements MapperTransaction {
	
	@Override
	public FinancingTransaction mapAccountTransaction(Transaction accountTransaction) {
		if (accountTransaction == null) {
			return null;
		}
		
		FinancingTransaction financingTransaction = new FinancingTransaction();
		
		financingTransaction.setStatus(statusToStatus(accountTransaction.getStatus()));
		financingTransaction.setAdditionalInformation(additionalInformationToAdditionalInformation(accountTransaction.getAdditionalInformation()));
		financingTransaction.setFinancingType(financingTypeToFinancingType(accountTransaction.getFinancingType()));
		financingTransaction.setRates(mapFeesToRates(accountTransaction.getFees()));
		financingTransaction.setId(accountTransaction.getId());
		financingTransaction.setClassification(classificationToClassification(accountTransaction.getClassification()));
		financingTransaction.setContract(contractToContract(accountTransaction.getContract()));
		financingTransaction.setAccountedDate(accountTransaction.getAccountedDate());
		financingTransaction.setValueDate(accountTransaction.getValueDate());
		if (accountTransaction.getOperationDate() != null) {
			financingTransaction.setOperationDate(new Timestamp(accountTransaction.getOperationDate().getTime()));
		}
		financingTransaction.setMoneyFlow(moneyFlowToMoneyFlow(accountTransaction.getMoneyFlow()));
		financingTransaction.setOperationAmounts(operationAmountListToOperationAmountList(accountTransaction.getOperationAmounts()));
		financingTransaction.setConcept(accountTransaction.getConcept());
		financingTransaction.setTransactionNumber(accountTransaction.getTransactionNumber());
		financingTransaction.setTransactionType(transactionTypeToTransactionType(accountTransaction.getTransactionType()));
		financingTransaction.setPaymentChannel(paymentChannelToPaymentChannel(accountTransaction.getPaymentChannel()));
		financingTransaction.setBranch(branchToBranch(accountTransaction.getBranch()));
		financingTransaction.setBank(bankToBank(accountTransaction.getBank()));
		List<String> tags = accountTransaction.getTags();
		if (tags != null) {
			financingTransaction.setTags(new ArrayList<>(tags));
		}
		financingTransaction.setSustainability(sustainabilityToSustainability(accountTransaction.getSustainability()));
		financingTransaction.setHasAttachments(accountTransaction.getHasAttachments());
		financingTransaction.setComments(commentListToCommentList(accountTransaction.getComments()));
		financingTransaction.setExchangeRate(exchangeRateToExchangeRate(accountTransaction.getExchangeRate()));
		financingTransaction.setFees(feesToFees(accountTransaction.getFees()));
		financingTransaction.setChannel(channelToChannel(accountTransaction.getChannel()));
		financingTransaction.setAttachments(attachmentListToAttachmentList(accountTransaction.getAttachments()));
		financingTransaction.setDetail(detailToDetail(accountTransaction.getDetail()));
		
		return financingTransaction;
	}
	
	@Override
	public FinancingTransaction mapCardTransaction(TransactionList cardTransaction) {
		if (cardTransaction == null) {
			return null;
		}
		
		FinancingTransaction financingTransaction = new FinancingTransaction();
		
		financingTransaction.setId(cardTransaction.getId());
		financingTransaction.setClassification(classificationToClassification1(cardTransaction.getClassification()));
		financingTransaction.setContract(contractToContract1(cardTransaction.getContract()));
		financingTransaction.setCardType(cardTransaction.getCardType());
		financingTransaction.setAccountedDate(cardTransaction.getAccountedDate());
		financingTransaction.setValueDate(cardTransaction.getValueDate());
		financingTransaction.setOperationDate(cardTransaction.getOperationDate());
		financingTransaction.setMoneyFlow(moneyFlowToMoneyFlow1(cardTransaction.getMoneyFlow()));
		financingTransaction.setOperationAmounts(operationAmountListToOperationAmountList1(cardTransaction.getOperationAmounts()));
		financingTransaction.setStatus(statusToStatus2(cardTransaction.getStatus()));
		financingTransaction.setConcept(cardTransaction.getConcept());
		financingTransaction.setTransactionNumber(cardTransaction.getTransactionNumber());
		financingTransaction.setTransactionType(transactionTypeToTransactionType1(cardTransaction.getTransactionType()));
		financingTransaction.setAdditionalInformation(additionalInformationToAdditionalInformation1(cardTransaction.getAdditionalInformation()));
		financingTransaction.setFinancingType(financingTypeToFinancingType1(cardTransaction.getFinancingType()));
		financingTransaction.setRates(ratesToRates(cardTransaction.getRates()));
		financingTransaction.setPaymentChannel(paymentChannelToPaymentChannel1(cardTransaction.getPaymentChannel()));
		financingTransaction.setBranch(branchToBranch1(cardTransaction.getBranch()));
		financingTransaction.setBank(bankToBank1(cardTransaction.getBank()));
		List<String> tags = cardTransaction.getTags();
		if (tags != null) {
			financingTransaction.setTags(new ArrayList<>(tags));
		}
		financingTransaction.setSustainability(sustainabilityToSustainability1(cardTransaction.getSustainability()));
		financingTransaction.setHasAttachments(cardTransaction.getHasAttachments());
		financingTransaction.setComments(commentListToCommentList1(cardTransaction.getComments()));
		
		return financingTransaction;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Rates mapFeesToRates(com.bbva.j6ea.dto.transactions.accounts.Fees fees) {
		
		if (null != fees) {
			com.bbva.kffm.dto.c001.financing.out.Rates rates = new com.bbva.kffm.dto.c001.financing.out.Rates();
			
			rates.setItemizeRates(mapItemizeRates(fees.getItemizeFees()));
			
			return rates;
		}
		
		return null;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.ItemizeRate> mapItemizeRates(List<ItemizeFee> itemizeFees) {
		
		List<com.bbva.kffm.dto.c001.financing.out.ItemizeRate> itemizeRatesList = new ArrayList<>();
		
		if (itemizeFees != null && ! itemizeFees.isEmpty()) {
			for (ItemizeFee itemizeFee : itemizeFees) {
				com.bbva.kffm.dto.c001.financing.out.ItemizeRate newItemizeRate = new com.bbva.kffm.dto.c001.financing.out.ItemizeRate();
				
				newItemizeRate.setRateType(itemizeFee.getFeeType());
				
				if (itemizeFee.getAmount() != null) {
					ItemizeRateUnit itemizeRateUnit = new ItemizeRateUnit();
					
					AsAmount asAmount = new AsAmount("AMOUNT", itemizeFee.getAmount().getAmount(), itemizeFee.getAmount().getCurrency());
					itemizeRateUnit.setAsAmount(asAmount);
					
					newItemizeRate.setItemizeRateUnit(itemizeRateUnit);
				}
				
				itemizeRatesList.add(newItemizeRate);
			}
		}
		
		return itemizeRatesList;
	}
	
	protected Reason reasonStatusToReason(ReasonStatus reasonStatus) {
		if (reasonStatus == null) {
			return null;
		}
		
		Reason newReason = new Reason();
		
		newReason.setId(reasonStatus.getId());
		newReason.setName(reasonStatus.getName());
		
		return newReason;
	}
	
	protected Status statusToStatus(com.bbva.j6ea.dto.transactions.accounts.Status status) {
		if (status == null) {
			return null;
		}
		
		Status newStatus = new Status();
		
		newStatus.setReason(reasonStatusToReason(status.getReasonStatus()));
		newStatus.setId(status.getId());
		newStatus.setName(status.getName());
		
		return newStatus;
	}
	
	protected Claim claimToClaim(com.bbva.j6ea.dto.transactions.accounts.Claim claim) {
		if (claim == null) {
			return null;
		}
		
		Claim newClaim = new Claim();
		
		newClaim.setOperationNumber(claim.getName());
		newClaim.setId(claim.getId());
		
		return newClaim;
	}
	
	protected Amount commonAmountToAmount(CommonAmount commonAmount) {
		if (commonAmount == null) {
			return null;
		}
		
		Amount newAmount = new Amount();
		
		newAmount.setAmount(commonAmount.getAmount());
		newAmount.setCurrency(commonAmount.getCurrency());
		
		return newAmount;
	}
	
	protected RewardBenefit rewardBenefitsToRewardBenefit(RewardBenefits rewardBenefits) {
		if (rewardBenefits == null) {
			return null;
		}
		
		RewardBenefit newRewardBenefit = new RewardBenefit();
		
		newRewardBenefit.setId(rewardBenefits.getId());
		newRewardBenefit.setDescription(rewardBenefits.getDescription());
		newRewardBenefit.setAmount(commonAmountToAmount(rewardBenefits.getAmount()));
		newRewardBenefit.setPercentage(rewardBenefits.getPercentage());
		
		return newRewardBenefit;
	}
	
	protected List<RewardBenefit> rewardBenefitsListToRewardBenefitList(List<RewardBenefits> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<RewardBenefit> rewardBenefitList = new ArrayList<>(list.size());
		for (RewardBenefits rewardBenefits : list) {
			rewardBenefitList.add(rewardBenefitsToRewardBenefit(rewardBenefits));
		}
		
		return rewardBenefitList;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Balance balanceToBalance(Balance balance) {
		if (balance == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Balance newBalance = new com.bbva.kffm.dto.c001.financing.out.Balance();
		
		newBalance.setId(balance.getId());
		newBalance.setAmount(balance.getAmount());
		newBalance.setCurrency(balance.getCurrency());
		
		return newBalance;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.Balance> balanceListToBalanceList(List<Balance> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.Balance> balanceList = new ArrayList<>(list.size());
		for (Balance balance : list) {
			balanceList.add(balanceToBalance(balance));
		}
		
		return balanceList;
	}
	
	protected User userToUser(com.bbva.j6ea.dto.transactions.accounts.User user) {
		if (user == null) {
			return null;
		}
		
		User newUser = new User();
		
		newUser.setId(user.getId());
		
		return newUser;
	}
	
	protected Computer computerToComputer(com.bbva.j6ea.dto.transactions.accounts.Computer computer) {
		if (computer == null) {
			return null;
		}
		
		Computer newComputer = new Computer();
		
		newComputer.setId(computer.getId());
		
		return newComputer;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.AdditionalInformation additionalInformationToAdditionalInformation(AdditionalInformation additionalInformation) {
		if (additionalInformation == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.AdditionalInformation newAdditionalInformation = new com.bbva.kffm.dto.c001.financing.out.AdditionalInformation();
		
		newAdditionalInformation.setClaim(claimToClaim(additionalInformation.getClaim()));
		newAdditionalInformation.setRewardBenefit(rewardBenefitsListToRewardBenefitList(additionalInformation.getRewardBenefits()));
		newAdditionalInformation.setVisible(additionalInformation.getIsVisible());
		newAdditionalInformation.setReference(additionalInformation.getReference());
		newAdditionalInformation.setAdditionalData(additionalInformation.getAdditionalData());
		newAdditionalInformation.setBalances(balanceListToBalanceList(additionalInformation.getBalances()));
		newAdditionalInformation.setUser(userToUser(additionalInformation.getUser()));
		newAdditionalInformation.setComputer(computerToComputer(additionalInformation.getComputer()));
		newAdditionalInformation.setBrandId(additionalInformation.getBrandId());
		
		return newAdditionalInformation;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.FinancingType financingTypeToFinancingType(FinancingType financingType) {
		if (financingType == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.FinancingType newFinancingType = new com.bbva.kffm.dto.c001.financing.out.FinancingType();
		
		newFinancingType.setStatus(statusToStatus(financingType.getStatus()));
		newFinancingType.setId(financingType.getId());
		newFinancingType.setName(financingType.getName());
		if (financingType.getExpirationDate() != null) {
			newFinancingType.setExpirationDate(new Timestamp(financingType.getExpirationDate().getTime()));
		}
		
		return newFinancingType;
	}
	
	protected Subcategory subcategoryToSubcategory(com.bbva.j6ea.dto.transactions.accounts.Subcategory subcategory) {
		if (subcategory == null) {
			return null;
		}
		
		Subcategory newSubcategory = new Subcategory();
		
		newSubcategory.setId(subcategory.getId());
		newSubcategory.setName(subcategory.getName());
		
		return newSubcategory;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Category categoryToCategory(Category category) {
		if (category == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Category newCategory = new com.bbva.kffm.dto.c001.financing.out.Category();
		
		newCategory.setId(category.getId());
		newCategory.setName(category.getName());
		newCategory.setSubcategory(subcategoryToSubcategory(category.getSubcategory()));
		
		return newCategory;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Classification classificationToClassification(Classification classification) {
		if (classification == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Classification newClassification = new com.bbva.kffm.dto.c001.financing.out.Classification();
		
		newClassification.setId(classification.getId());
		newClassification.setName(classification.getName());
		newClassification.setCategory(categoryToCategory(classification.getCategory()));
		
		return newClassification;
	}
	
	protected NumberType numberTypeToNumberType(com.bbva.j6ea.dto.transactions.accounts.NumberType numberType) {
		if (numberType == null) {
			return null;
		}
		
		NumberType newNumberType = new NumberType();
		
		newNumberType.setId(numberType.getId());
		newNumberType.setDescription(numberType.getDescription());
		
		return newNumberType;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Subproduct subproductToSubproduct(Subproduct subproduct) {
		if (subproduct == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Subproduct newSubproduct = new com.bbva.kffm.dto.c001.financing.out.Subproduct();
		
		newSubproduct.setId(subproduct.getId());
		newSubproduct.setName(subproduct.getName());
		
		return newSubproduct;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Product productToProduct(Product product) {
		if (product == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Product newProduct = new com.bbva.kffm.dto.c001.financing.out.Product();
		
		newProduct.setId(product.getId());
		newProduct.setName(product.getName());
		newProduct.setSubproduct(subproductToSubproduct(product.getSubproduct()));
		
		return newProduct;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Contract contractToContract(Contract contract) {
		if (contract == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Contract newContract = new com.bbva.kffm.dto.c001.financing.out.Contract();
		
		newContract.setId(contract.getId());
		newContract.setNumber(contract.getNumber());
		newContract.setNumberType(numberTypeToNumberType(contract.getNumberType()));
		newContract.setAlias(contract.getAlias());
		newContract.setProduct(productToProduct(contract.getProduct()));
		
		return newContract;
	}
	
	protected MoneyFlow moneyFlowToMoneyFlow(com.bbva.j6ea.dto.transactions.accounts.MoneyFlow moneyFlow) {
		if (moneyFlow == null) {
			return null;
		}
		
		MoneyFlow newMoneyFlow = new MoneyFlow();
		
		newMoneyFlow.setId(moneyFlow.getId());
		newMoneyFlow.setName(moneyFlow.getName());
		
		return newMoneyFlow;
	}
	
	protected OperationAmount operationAmountToOperationAmount(com.bbva.j6ea.dto.transactions.accounts.OperationAmount operationAmount) {
		if (operationAmount == null) {
			return null;
		}
		
		OperationAmount newOperationAmount = new OperationAmount();
		
		newOperationAmount.setId(operationAmount.getId());
		newOperationAmount.setAmount(operationAmount.getAmount());
		newOperationAmount.setCurrency(operationAmount.getCurrency());
		
		return newOperationAmount;
	}
	
	protected List<OperationAmount> operationAmountListToOperationAmountList(List<com.bbva.j6ea.dto.transactions.accounts.OperationAmount> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<OperationAmount> operationAmountList = new ArrayList<>(list.size());
		for (com.bbva.j6ea.dto.transactions.accounts.OperationAmount operationAmount : list) {
			operationAmountList.add(operationAmountToOperationAmount(operationAmount));
		}
		
		return operationAmountList;
	}
	
	protected InternalCode internalCodeToInternalCode(com.bbva.j6ea.dto.transactions.accounts.InternalCode internalCode) {
		if (internalCode == null) {
			return null;
		}
		
		InternalCode newInternalCode = new InternalCode();
		
		newInternalCode.setId(internalCode.getId());
		newInternalCode.setName(internalCode.getName());
		
		return newInternalCode;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.TransactionType transactionTypeToTransactionType(TransactionType transactionType) {
		if (transactionType == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.TransactionType newTransactionType = new com.bbva.kffm.dto.c001.financing.out.TransactionType();
		
		newTransactionType.setId(transactionType.getId());
		newTransactionType.setName(transactionType.getName());
		newTransactionType.setInternalCode(internalCodeToInternalCode(transactionType.getInternalCode()));
		
		return newTransactionType;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.PaymentChannel paymentChannelToPaymentChannel(PaymentChannel paymentChannel) {
		if (paymentChannel == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.PaymentChannel newPaymentChannel = new com.bbva.kffm.dto.c001.financing.out.PaymentChannel();
		
		newPaymentChannel.setId(paymentChannel.getId());
		newPaymentChannel.setName(paymentChannel.getName());
		
		return newPaymentChannel;
	}
	
	protected Branch branchToBranch(com.bbva.j6ea.dto.transactions.accounts.Branch branch) {
		if (branch == null) {
			return null;
		}
		
		Branch newBranch = new Branch();
		
		newBranch.setId(branch.getId());
		newBranch.setName(branch.getName());
		
		return newBranch;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Bank bankToBank(Bank bank) {
		if (bank == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Bank newBank = new com.bbva.kffm.dto.c001.financing.out.Bank();
		
		newBank.setId(bank.getId());
		
		return newBank;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Co2Mark co2MarkToCo2Mark(Co2Mark co2Mark) {
		if (co2Mark == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Co2Mark newCo2Mark = new com.bbva.kffm.dto.c001.financing.out.Co2Mark();
		
		newCo2Mark.setId(co2Mark.getId());
		
		return newCo2Mark;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Sustainability sustainabilityToSustainability(Sustainability sustainability) {
		if (sustainability == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Sustainability newSustainability = new com.bbva.kffm.dto.c001.financing.out.Sustainability();
		
		if (sustainability.getMoveCo2KgNumber() != null) {
			newSustainability.setMoveCo2KgNumber(String.valueOf(sustainability.getMoveCo2KgNumber()));
		}
		if (sustainability.getSaveCo2KgNumber() != null) {
			newSustainability.setSaveCo2KgNumber(String.valueOf(sustainability.getSaveCo2KgNumber()));
		}
		newSustainability.setCo2Mark(co2MarkToCo2Mark(sustainability.getCo2Mark()));
		
		return newSustainability;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Comment commentToComment(Comment comment) {
		if (comment == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Comment newComment = new com.bbva.kffm.dto.c001.financing.out.Comment();
		
		newComment.setValue(comment.getValue());
		
		return newComment;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.Comment> commentListToCommentList(List<Comment> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.Comment> commentList = new ArrayList<>(list.size());
		for (Comment comment : list) {
			commentList.add(commentToComment(comment));
		}
		
		return commentList;
	}
	
	protected Ratio ratioToRatio(com.bbva.j6ea.dto.transactions.accounts.Ratio ratio) {
		if (ratio == null) {
			return null;
		}
		
		Ratio newRatio = new Ratio();
		
		newRatio.setValue(String.valueOf(ratio.getValue()));
		
		return newRatio;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Target targetToTarget(Target target) {
		if (target == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Target newTarget = new com.bbva.kffm.dto.c001.financing.out.Target();
		
		newTarget.setCurrency(target.getCurrency());
		newTarget.setRatio(ratioToRatio(target.getRatio()));
		
		return newTarget;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.Target> targetListToTargetList(List<Target> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.Target> targetList = new ArrayList<>(list.size());
		for (Target target : list) {
			targetList.add(targetToTarget(target));
		}
		
		return targetList;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.ExchangeRate exchangeRateToExchangeRate(ExchangeRate exchangeRate) {
		if (exchangeRate == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.ExchangeRate newExchangeRate = new com.bbva.kffm.dto.c001.financing.out.ExchangeRate();
		
		newExchangeRate.setTargets(targetListToTargetList(exchangeRate.getTargets()));
		
		return newExchangeRate;
	}
	
	protected Amount totalFeesToAmount(TotalFees totalFees) {
		if (totalFees == null) {
			return null;
		}
		
		Amount newAmount = new Amount();
		
		newAmount.setAmount(totalFees.getAmount());
		newAmount.setCurrency(totalFees.getCurrency());
		
		return newAmount;
	}
	
	protected Fees feesToFees(com.bbva.j6ea.dto.transactions.accounts.Fees fees) {
		if (fees == null) {
			return null;
		}
		
		Fees newFees = new Fees();
		
		newFees.setTotalFees(totalFeesToAmount(fees.getTotalFees()));
		
		return newFees;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Channel channelToChannel(Channel channel) {
		if (channel == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Channel newChannel = new com.bbva.kffm.dto.c001.financing.out.Channel();
		
		newChannel.setId(channel.getId());
		newChannel.setName(channel.getName());
		
		return newChannel;
	}
	
	protected Attachment attachmentToAttachment(com.bbva.j6ea.dto.transactions.accounts.Attachment attachment) {
		if (attachment == null) {
			return null;
		}
		
		Attachment newAttachment = new Attachment();
		
		newAttachment.setId(attachment.getId());
		newAttachment.setName(attachment.getName());
		newAttachment.setFileFormat(attachment.getFileFormat());
		newAttachment.setFileExtension(attachment.getFileExtension());
		if (attachment.getSize() != null) {
			newAttachment.setSize(attachment.getSize().longValue());
		}
		
		return newAttachment;
	}
	
	protected List<Attachment> attachmentListToAttachmentList(List<com.bbva.j6ea.dto.transactions.accounts.Attachment> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<Attachment> attachmentList = new ArrayList<>(list.size());
		for (com.bbva.j6ea.dto.transactions.accounts.Attachment attachment : list) {
			attachmentList.add(attachmentToAttachment(attachment));
		}
		
		return attachmentList;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.detail.Detail detailToDetail(Detail detail) {
		if (detail == null) {
			return null;
		}
		
		return new com.bbva.kffm.dto.c001.financing.out.detail.Detail();
	}
	
	protected Subcategory subcategoryToSubcategory1(com.bbva.j6ec.dto.cards.transactions.Subcategory subcategory) {
		if (subcategory == null) {
			return null;
		}
		
		Subcategory newSubcategory = new Subcategory();
		
		newSubcategory.setId(subcategory.getId());
		newSubcategory.setName(subcategory.getName());
		
		return newSubcategory;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Category categoryToCategory1(com.bbva.j6ec.dto.cards.transactions.Category category) {
		if (category == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Category newCategory = new com.bbva.kffm.dto.c001.financing.out.Category();
		
		newCategory.setId(category.getId());
		newCategory.setName(category.getName());
		newCategory.setSubcategory(subcategoryToSubcategory1(category.getSubcategory()));
		
		return newCategory;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Classification classificationToClassification1(com.bbva.j6ec.dto.cards.transactions.Classification classification) {
		if (classification == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Classification newClassification = new com.bbva.kffm.dto.c001.financing.out.Classification();
		
		newClassification.setId(classification.getId());
		newClassification.setName(classification.getName());
		newClassification.setCategory(categoryToCategory1(classification.getCategory()));
		
		return newClassification;
	}
	
	protected NumberType numberTypeToNumberType1(com.bbva.j6ec.dto.cards.transactions.NumberType numberType) {
		if (numberType == null) {
			return null;
		}
		
		NumberType newNumberType = new NumberType();
		
		newNumberType.setId(numberType.getId());
		newNumberType.setDescription(numberType.getDescription());
		
		return newNumberType;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Subproduct subproductToSubproduct1(com.bbva.j6ec.dto.cards.transactions.Subproduct subproduct) {
		if (subproduct == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Subproduct newSubproduct = new com.bbva.kffm.dto.c001.financing.out.Subproduct();
		
		newSubproduct.setId(subproduct.getId());
		newSubproduct.setName(subproduct.getName());
		
		return newSubproduct;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Product productToProduct1(com.bbva.j6ec.dto.cards.transactions.Product product) {
		if (product == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Product newProduct = new com.bbva.kffm.dto.c001.financing.out.Product();
		
		newProduct.setId(product.getId());
		newProduct.setName(product.getName());
		newProduct.setSubproduct(subproductToSubproduct1(product.getSubproduct()));
		
		return newProduct;
	}
	
	protected RelatedContract relatedContractToRelatedContract(com.bbva.j6ec.dto.cards.transactions.RelatedContract relatedContract) {
		if (relatedContract == null) {
			return null;
		}
		
		RelatedContract newRelatedContract = new RelatedContract();
		
		newRelatedContract.setId(relatedContract.getId());
		newRelatedContract.setTransactionNumber(relatedContract.getTransactionNumber());
		
		return newRelatedContract;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Contract contractToContract1(com.bbva.j6ec.dto.cards.transactions.Contract contract) {
		if (contract == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Contract newContract = new com.bbva.kffm.dto.c001.financing.out.Contract();
		
		newContract.setId(contract.getId());
		newContract.setNumber(contract.getNumber());
		newContract.setNumberType(numberTypeToNumberType1(contract.getNumberType()));
		newContract.setAlias(contract.getAlias());
		newContract.setProduct(productToProduct1(contract.getProduct()));
		newContract.setHolder(contract.isHolder());
		newContract.setRelatedContract(relatedContractToRelatedContract(contract.getRelatedContract()));
		
		return newContract;
	}
	
	protected MoneyFlow moneyFlowToMoneyFlow1(com.bbva.j6ec.dto.cards.transactions.MoneyFlow moneyFlow) {
		if (moneyFlow == null) {
			return null;
		}
		
		MoneyFlow newMoneyFlow = new MoneyFlow();
		
		newMoneyFlow.setId(moneyFlow.getId());
		newMoneyFlow.setName(moneyFlow.getName());
		
		return newMoneyFlow;
	}
	
	protected OperationAmount operationAmountToOperationAmount1(com.bbva.j6ec.dto.cards.transactions.OperationAmount operationAmount) {
		if (operationAmount == null) {
			return null;
		}
		
		OperationAmount newOperationAmount = new OperationAmount();
		
		newOperationAmount.setId(operationAmount.getId());
		newOperationAmount.setAmount(operationAmount.getAmount());
		newOperationAmount.setCurrency(operationAmount.getCurrency());
		
		return newOperationAmount;
	}
	
	protected List<OperationAmount> operationAmountListToOperationAmountList1(List<com.bbva.j6ec.dto.cards.transactions.OperationAmount> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<OperationAmount> opereationAmountList = new ArrayList<>(list.size());
		for (com.bbva.j6ec.dto.cards.transactions.OperationAmount operationAmount : list) {
			opereationAmountList.add(operationAmountToOperationAmount1(operationAmount));
		}
		
		return opereationAmountList;
	}
	
	protected Reason reasonToReason(com.bbva.j6ec.dto.cards.transactions.Reason reason) {
		if (reason == null) {
			return null;
		}
		
		Reason newReason = new Reason();
		
		newReason.setId(reason.getId());
		newReason.setName(reason.getName());
		
		return newReason;
	}
	
	protected Status statusToStatus2(com.bbva.j6ec.dto.cards.transactions.Status status) {
		if (status == null) {
			return null;
		}
		
		Status newStatus = new Status();
		
		newStatus.setId(status.getId());
		newStatus.setName(status.getName());
		newStatus.setReason(reasonToReason(status.getReason()));
		
		return newStatus;
	}
	
	protected InternalCode internalCodeToInternalCode1(com.bbva.j6ec.dto.cards.transactions.InternalCode internalCode) {
		if (internalCode == null) {
			return null;
		}
		
		InternalCode newInternalCode = new InternalCode();
		
		newInternalCode.setId(internalCode.getId());
		newInternalCode.setName(internalCode.getName());
		
		return newInternalCode;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.TransactionType transactionTypeToTransactionType1(com.bbva.j6ec.dto.cards.transactions.TransactionType transactionType) {
		if (transactionType == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.TransactionType newTransactionType = new com.bbva.kffm.dto.c001.financing.out.TransactionType();
		
		newTransactionType.setId(transactionType.getId());
		newTransactionType.setName(transactionType.getName());
		newTransactionType.setInternalCode(internalCodeToInternalCode1(transactionType.getInternalCode()));
		
		return newTransactionType;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Balance balanceToBalance1(com.bbva.j6ec.dto.cards.transactions.Balance balance) {
		if (balance == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Balance newBalance = new com.bbva.kffm.dto.c001.financing.out.Balance();
		
		newBalance.setId(balance.getId());
		newBalance.setAmount(balance.getAmount());
		newBalance.setCurrency(balance.getCurrency());
		
		return newBalance;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.Balance> balanceListToBalanceList1(List<com.bbva.j6ec.dto.cards.transactions.Balance> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.Balance> balanceList = new ArrayList<>(list.size());
		for (com.bbva.j6ec.dto.cards.transactions.Balance balance : list) {
			balanceList.add(balanceToBalance1(balance));
		}
		
		return balanceList;
	}
	
	protected User userToUser1(com.bbva.j6ec.dto.cards.transactions.User user) {
		if (user == null) {
			return null;
		}
		
		User newUser = new User();
		
		newUser.setId(user.getId());
		
		return newUser;
	}
	
	protected Computer computerToComputer1(com.bbva.j6ec.dto.cards.transactions.Computer computer) {
		if (computer == null) {
			return null;
		}
		
		Computer newComputer = new Computer();
		
		newComputer.setId(computer.getId());
		
		return newComputer;
	}
	
	protected Claim claimToClaim1(com.bbva.j6ec.dto.cards.transactions.Claim claim) {
		if (claim == null) {
			return null;
		}
		
		Claim newClaim = new Claim();
		
		newClaim.setId(claim.getId());
		newClaim.setOperationNumber(claim.getOperationNumber());
		
		return newClaim;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.AdditionalInformation additionalInformationToAdditionalInformation1(com.bbva.j6ec.dto.cards.transactions.AdditionalInformation additionalInformation) {
		if (additionalInformation == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.AdditionalInformation newAdditionalInformation = new com.bbva.kffm.dto.c001.financing.out.AdditionalInformation();
		
		newAdditionalInformation.setReference(additionalInformation.getReference());
		newAdditionalInformation.setAdditionalData(additionalInformation.getAdditionalData());
		newAdditionalInformation.setBalances(balanceListToBalanceList1(additionalInformation.getBalances()));
		newAdditionalInformation.setUser(userToUser1(additionalInformation.getUser()));
		newAdditionalInformation.setComputer(computerToComputer1(additionalInformation.getComputer()));
		newAdditionalInformation.setPaymentGateway(additionalInformation.getPaymentGateway());
		newAdditionalInformation.setBrandId(additionalInformation.getBrandId());
		newAdditionalInformation.setClaim(claimToClaim1(additionalInformation.getClaim()));
		newAdditionalInformation.setPaymentTreatmentType(additionalInformation.getPaymentTreatmentType());
		
		return newAdditionalInformation;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.FinancingType financingTypeToFinancingType1(com.bbva.j6ec.dto.cards.transactions.FinancingType financingType) {
		if (financingType == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.FinancingType newFinancingType = new com.bbva.kffm.dto.c001.financing.out.FinancingType();
		
		newFinancingType.setId(financingType.getId());
		newFinancingType.setName(financingType.getName());
		newFinancingType.setStatus(statusToStatus2(financingType.getStatus()));
		if (financingType.getTotalInstallmentsNumber() != null) {
			newFinancingType.setTotalInstallmentsNumber(financingType.getTotalInstallmentsNumber().longValue());
		}
		
		return newFinancingType;
	}
	
	protected ItemizeRateUnit itemizeRateUnitToItemizeRateUnit(com.bbva.j6ec.dto.cards.transactions.ItemizeRateUnit itemizeRateUnit) {
		if (itemizeRateUnit == null) {
			return null;
		}
		
		return new ItemizeRateUnit();
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.ItemizeRate itemizeRateToItemizeRate(ItemizeRate itemizeRate) {
		if (itemizeRate == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.ItemizeRate newItemizeRate = new com.bbva.kffm.dto.c001.financing.out.ItemizeRate();
		
		newItemizeRate.setRateType(itemizeRate.getRateType());
		newItemizeRate.setItemizeRateUnit(itemizeRateUnitToItemizeRateUnit(itemizeRate.getItemizeRateUnit()));
		
		return newItemizeRate;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.ItemizeRate> itemizeRateListToItemizeRateList(List<ItemizeRate> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.ItemizeRate> itemizeRateList = new ArrayList<>(list.size());
		for (ItemizeRate itemizeRate : list) {
			itemizeRateList.add(itemizeRateToItemizeRate(itemizeRate));
		}
		
		return itemizeRateList;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Rates ratesToRates(Rates rates) {
		if (rates == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Rates newRates = new com.bbva.kffm.dto.c001.financing.out.Rates();
		
		newRates.setItemizeRates(itemizeRateListToItemizeRateList(rates.getItemizeRates()));
		
		return newRates;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.PaymentChannel paymentChannelToPaymentChannel1(com.bbva.j6ec.dto.cards.transactions.PaymentChannel paymentChannel) {
		if (paymentChannel == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.PaymentChannel newPaymentChannel = new com.bbva.kffm.dto.c001.financing.out.PaymentChannel();
		
		newPaymentChannel.setId(paymentChannel.getId());
		newPaymentChannel.setName(paymentChannel.getName());
		
		return newPaymentChannel;
	}
	
	protected Branch branchToBranch1(com.bbva.j6ec.dto.cards.transactions.Branch branch) {
		if (branch == null) {
			return null;
		}
		
		Branch newBranch = new Branch();
		
		newBranch.setId(branch.getId());
		newBranch.setName(branch.getName());
		
		return newBranch;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Bank bankToBank1(com.bbva.j6ec.dto.cards.transactions.Bank bank) {
		if (bank == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Bank newBank = new com.bbva.kffm.dto.c001.financing.out.Bank();
		
		newBank.setId(bank.getId());
		
		return newBank;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Co2Mark co2MarkToCo2Mark1(com.bbva.j6ec.dto.cards.transactions.Co2Mark co2Mark) {
		if (co2Mark == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Co2Mark newCo2Mark = new com.bbva.kffm.dto.c001.financing.out.Co2Mark();
		
		newCo2Mark.setId(co2Mark.getId());
		
		return newCo2Mark;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Sustainability sustainabilityToSustainability1(com.bbva.j6ec.dto.cards.transactions.Sustainability sustainability) {
		if (sustainability == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Sustainability newSustainability = new com.bbva.kffm.dto.c001.financing.out.Sustainability();
		
		if (sustainability.getMoveCo2KgNumber() != null) {
			newSustainability.setMoveCo2KgNumber(String.valueOf(sustainability.getMoveCo2KgNumber()));
		}
		if (sustainability.getSaveCo2KgNumber() != null) {
			newSustainability.setSaveCo2KgNumber(String.valueOf(sustainability.getSaveCo2KgNumber()));
		}
		newSustainability.setCo2Mark(co2MarkToCo2Mark1(sustainability.getCo2Mark()));
		
		return newSustainability;
	}
	
	protected com.bbva.kffm.dto.c001.financing.out.Comment commentToComment1(com.bbva.j6ec.dto.cards.transactions.detail.Comment comment) {
		if (comment == null) {
			return null;
		}
		
		com.bbva.kffm.dto.c001.financing.out.Comment newComment = new com.bbva.kffm.dto.c001.financing.out.Comment();
		
		newComment.setValue(comment.getValue());
		
		return newComment;
	}
	
	protected List<com.bbva.kffm.dto.c001.financing.out.Comment> commentListToCommentList1(List<com.bbva.j6ec.dto.cards.transactions.detail.Comment> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		
		List<com.bbva.kffm.dto.c001.financing.out.Comment> commentList = new ArrayList<>(list.size());
		for (com.bbva.j6ec.dto.cards.transactions.detail.Comment comment : list) {
			commentList.add(commentToComment1(comment));
		}
		
		return commentList;
	}
	
}

