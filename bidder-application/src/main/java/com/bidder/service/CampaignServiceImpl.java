package com.bidder.service;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.domain.Campaign;
import com.bidder.gateway.CampaignGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignGateway campaignGateway;

    @Override
    public List<Campaign> fetchAllAvailableCampaigns() {
        return campaignGateway.getAllCampaigns();
    }

    @Override
    public List<Campaign> filterMatchingCampaigns(BidRequestDto bidRequestDto, List<Campaign> campaignList) {
        return campaignList.stream()
            .filter(campaign -> campaign.getTargetedCountries().contains(bidRequestDto.getDevice().getGeo().getCountry()))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Campaign> getMostExpensiveCampaign(List<Campaign> matchingCampaignList) {
        return matchingCampaignList.stream().max(Comparator.comparing(Campaign::getPrice));
    }
}
