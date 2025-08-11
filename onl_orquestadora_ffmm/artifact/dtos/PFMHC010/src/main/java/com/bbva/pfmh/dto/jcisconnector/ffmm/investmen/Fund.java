package com.bbva.pfmh.dto.jcisconnector.ffmm.investmen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class Fund implements Serializable {

    private static final long serialVersionUID = 6422224538537777928L;

    private String fundId;
    private String alias;
    private BigDecimal ownedShares;
    private FundPosition fundPosition;
    private NetAssetValue netAssetValue;
    private AccountedBalance accountedBalance;
    private Title title;
    private String serie;
    private List<NumberFormat> numberFormats;
    private List<FundCategory> categories;
    private FundCurrency currency;
    private Date openingDate;
    private Country country;
    private Date netAssetValueDate;
    private Assets assets;
    private BigDecimal currentYearReturn;
    private BigDecimal lastYearReturn;
    private ManagerCompany managerCompany;
    private AvailableFundPosition availableFundPosition;


    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getOwnedShares() {
        return ownedShares;
    }

    public void setOwnedShares(BigDecimal ownedShares) {
        this.ownedShares = ownedShares;
    }

    public FundPosition getFundPosition() {
        return fundPosition;
    }

    public void setFundPosition(FundPosition fundPosition) {
        this.fundPosition = fundPosition;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public AvailableFundPosition getAvailableFundPosition() {
        return availableFundPosition;
    }

    public void setAvailableFundPosition(AvailableFundPosition availableFundPosition) {
        this.availableFundPosition = availableFundPosition;
    }

    public ManagerCompany getManagerCompany() {
        return managerCompany;
    }

    public void setManagerCompany(ManagerCompany managerCompany) {
        this.managerCompany = managerCompany;
    }

    public BigDecimal getLastYearReturn() {
        return lastYearReturn;
    }

    public void setLastYearReturn(BigDecimal lastYearReturn) {
        this.lastYearReturn = lastYearReturn;
    }

    public BigDecimal getCurrentYearReturn() {
        return currentYearReturn;
    }

    public void setCurrentYearReturn(BigDecimal currentYearReturn) {
        this.currentYearReturn = currentYearReturn;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Date getNetAssetValueDate() {
        return netAssetValueDate;
    }

    public void setNetAssetValueDate(Date netAssetValueDate) {
        this.netAssetValueDate = netAssetValueDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public FundCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FundCurrency currency) {
        this.currency = currency;
    }

    public List<NumberFormat> getNumberFormats() {
        return numberFormats;
    }

    public void setNumberFormats(List<NumberFormat> numberFormats) {
        this.numberFormats = numberFormats;
    }

    public List<FundCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FundCategory> categories) {
        this.categories = categories;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public NetAssetValue getNetAssetValue() {
        return netAssetValue;
    }

    public void setNetAssetValue(NetAssetValue netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    public AccountedBalance getAccountedBalance() {
        return accountedBalance;
    }

    public void setAccountedBalance(AccountedBalance accountedBalance) {
        this.accountedBalance = accountedBalance;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundId='" + fundId + '\'' +
                ", alias='" + alias + '\'' +
                ", ownedShares=" + ownedShares +
                ", fundPosition=" + fundPosition +
                ", netAssetValue=" + netAssetValue +
                ", accountedBalance=" + accountedBalance +
                ", title=" + title +
                ", serie='" + serie + '\'' +
                ", numberFormats=" + numberFormats +
                ", categories=" + categories +
                ", currency=" + currency +
                ", openingDate=" + openingDate +
                ", country=" + country +
                ", netAssetValueDate=" + netAssetValueDate +
                ", assets=" + assets +
                ", currentYearReturn=" + currentYearReturn +
                ", lastYearReturn=" + lastYearReturn +
                ", managerCompany=" + managerCompany +
                ", availableFundPosition=" + availableFundPosition +
                '}';
    }
}
