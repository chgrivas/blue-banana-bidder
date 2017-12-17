package com.bidder.api.dto.response.builder;

import com.bidder.api.dto.response.Bid;
import com.bidder.api.dto.response.BidResponseDto;

/**
 * todo: class comment
 *
 * @author cgrivas
 */
public final class BidResponseDtoBuilder {
    private String id;
    private Bid bid;

    private BidResponseDtoBuilder() {
    }

    public static BidResponseDtoBuilder aBidResponseDto() {
        return new BidResponseDtoBuilder();
    }

    public BidResponseDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public BidResponseDtoBuilder withBid(Bid bid) {
        this.bid = bid;
        return this;
    }

    public BidResponseDto build() {
        BidResponseDto bidResponseDto = new BidResponseDto();
        bidResponseDto.setId(id);
        bidResponseDto.setBid(bid);
        return bidResponseDto;
    }
}
