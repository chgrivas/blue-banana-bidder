package com.bidder.api.dto.response.builder;

import com.bidder.api.dto.response.Bid;

import java.math.BigDecimal;

/**
 * todo: class comment
 *
 * @author cgrivas
 */
public final class BidBuilder {
    private String campaignId;
    private BigDecimal price;
    private String adm;

    private BidBuilder() {
    }

    public static BidBuilder aBid() {
        return new BidBuilder();
    }

    public BidBuilder withCampaignId(String campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public BidBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BidBuilder withAdm(String adm) {
        this.adm = adm;
        return this;
    }

    public Bid build() {
        Bid bid = new Bid();
        bid.setCampaignId(campaignId);
        bid.setPrice(price);
        bid.setAdm(adm);
        return bid;
    }
}
