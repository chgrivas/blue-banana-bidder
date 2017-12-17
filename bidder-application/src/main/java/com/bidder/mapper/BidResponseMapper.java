package com.bidder.mapper;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.api.dto.response.BidResponseDto;
import com.bidder.domain.Campaign;

public interface BidResponseMapper {
    BidResponseDto covertCampaignToBidResponseDto(BidRequestDto bidRequestDto, Campaign campaign);
}
