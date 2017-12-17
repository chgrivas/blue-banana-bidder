package com.bidder.service;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.domain.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    List<Campaign> fetchAllAvailableCampaigns();

    List<Campaign> filterMatchingCampaigns(BidRequestDto bidRequestDto, List<Campaign> campaignList);

    Optional<Campaign> getMostExpensiveCampaign(List<Campaign> matchingCampaignList);
}