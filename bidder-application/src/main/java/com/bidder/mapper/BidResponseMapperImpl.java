package com.bidder.mapper;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.api.dto.response.BidResponseDto;
import com.bidder.api.dto.response.builder.BidBuilder;
import com.bidder.api.dto.response.builder.BidResponseDtoBuilder;
import com.bidder.domain.Campaign;
import org.springframework.stereotype.Component;

@Component
public class BidResponseMapperImpl implements BidResponseMapper {
    @Override
    public BidResponseDto covertCampaignToBidResponseDto(BidRequestDto bidRequestDto, Campaign campaign) {
        return BidResponseDtoBuilder.aBidResponseDto()
                .withId(bidRequestDto.getId())
                .withBid(BidBuilder.aBid()
                    .withCampaignId(campaign.getId())
                    .withPrice(campaign.getPrice())
                    .withAdm(campaign.getAdm())
                    .build())
                .build();
    }
}
