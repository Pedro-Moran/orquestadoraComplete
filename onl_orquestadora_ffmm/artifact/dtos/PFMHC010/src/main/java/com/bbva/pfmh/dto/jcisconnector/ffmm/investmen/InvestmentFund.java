package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InvestmentFund implements Serializable {

    private static final long serialVersionUID = -3562884476438809440L;

    private String number;
    private String investmentFundId;
    private InvestmentFundNumberType numberType;
    private InvestmentFundType investmentFundType;
    private String alias;
    private boolean isVisible;
    private String description;
    private List<Delivery> delivery;
    private RelatedContract relatedContract;
    private PositionSize positionSize;
    private Date openingDate;
    private Title title;
    private ModalityContrating modalityContrating;
    private Integer ownedShares;
    private SubscriptionMoney subscriptionMoney;
    private PaymentMethod paymentMethod;
    private String associatedAccount;
    private String contractEmail;
    private List<SendInformation> sendInformation;
    private String deliveryIndicator;
    private String customerId;
    private NetAssetValue netAssetValue;
    private RiskProfile riskProfile;
    private String concept;
    private ExchangeRate exchangeRate;
    private List<Fund> funds;
    private Fees fees;
    private String offerId;
    private TaxesSubscription taxes;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getInvestmentFundId() {
        return investmentFundId;
    }

    public void setInvestmentFundId(String investmentFundId) {
        this.investmentFundId = investmentFundId;
    }

    public InvestmentFundNumberType getNumberType() {
        return numberType;
    }

    public void setNumberType(InvestmentFundNumberType numberType) {
        this.numberType = numberType;
    }

    public ModalityContrating getModalityContrating() {
        return modalityContrating;
    }

    public void setModalityContrating(ModalityContrating modalityContrating) {
        this.modalityContrating = modalityContrating;
    }

    public String getAssociatedAccount() {
        return associatedAccount;
    }

    public void setAssociatedAccount(String associatedAccount) {
        this.associatedAccount = associatedAccount;
    }

    public String getDeliveryIndicator() {
        return deliveryIndicator;
    }

    public void setDeliveryIndicator(String deliveryIndicator) {
        this.deliveryIndicator = deliveryIndicator;
    }

    public TaxesSubscription getTaxes() {
        return taxes;
    }

    public void setTaxes(TaxesSubscription taxes) {
        this.taxes = taxes;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public RiskProfile getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(RiskProfile riskProfile) {
        this.riskProfile = riskProfile;
    }

    public NetAssetValue getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(NetAssetValue netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<SendInformation> getSendInformation() {
        return sendInformation;
    }

    public void setSendInformation(List<SendInformation> sendInformation) {
        this.sendInformation = sendInformation;
    }

    public String getContractEmail() {
        return contractEmail;
    }

    public void setContractEmail(String contractEmail) {
        this.contractEmail = contractEmail;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public SubscriptionMoney getSubscriptionMoney() {
        return subscriptionMoney;
    }

    public void setSubscriptionMoney(SubscriptionMoney subscriptionMoney) {
        this.subscriptionMoney = subscriptionMoney;
    }

    public Integer getOwnedShares() {
        return ownedShares;
    }

    public void setOwnedShares(Integer ownedShares) {
        this.ownedShares = ownedShares;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public PositionSize getPositionSize() {
        return positionSize;
    }

    public void setPositionSize(PositionSize positionSize) {
        this.positionSize = positionSize;
    }

    public RelatedContract getRelatedContract() {
        return relatedContract;
    }

    public void setRelatedContract(RelatedContract relatedContract) {
        this.relatedContract = relatedContract;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public InvestmentFundType getInvestmentFundType() {
        return investmentFundType;
    }

    public void setInvestmentFundType(InvestmentFundType investmentFundType) {
        this.investmentFundType = investmentFundType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "InvestmentFund{" +
                "number='" + number + '\'' +
                ", investmentFundId='" + investmentFundId + '\'' +
                ", numberType=" + numberType +
                ", investmentFundType=" + investmentFundType +
                ", alias='" + alias + '\'' +
                ", isVisible=" + isVisible +
                ", description='" + description + '\'' +
                ", delivery=" + delivery +
                ", relatedContract=" + relatedContract +
                ", positionSize=" + positionSize +
                ", openingDate=" + openingDate +
                ", title=" + title +
                ", modalityContrating=" + modalityContrating +
                ", ownedShares=" + ownedShares +
                ", subscriptionMoney=" + subscriptionMoney +
                ", paymentMethod=" + paymentMethod +
                ", associatedAccount='" + associatedAccount + '\'' +
                ", contractEmail='" + contractEmail + '\'' +
                ", sendInformation=" + sendInformation +
                ", deliveryIndicator='" + deliveryIndicator + '\'' +
                ", customerId='" + customerId + '\'' +
                ", netAssetValue=" + netAssetValue +
                ", riskProfile=" + riskProfile +
                ", concept='" + concept + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", funds=" + funds +
                ", fees=" + fees +
                ", offerId='" + offerId + '\'' +
                ", taxes=" + taxes +
                '}';
    }
}
