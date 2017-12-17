package com.bidder.domain;

import java.math.BigDecimal;
import java.util.List;

public class Campaign {
    private String id;
    private String name;
    private BigDecimal price;
    private String adm;
    private List<String> targetedCountries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public List<String> getTargetedCountries() {
        return targetedCountries;
    }

    public void setTargetedCountries(List<String> targetedCountries) {
        this.targetedCountries = targetedCountries;
    }
}
