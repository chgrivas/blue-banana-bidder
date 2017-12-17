package com.bidder.api;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.api.dto.response.BidResponseDto;
import com.bidder.domain.Campaign;
import com.bidder.mapper.BidResponseMapper;
import com.bidder.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BidderApiImpl implements BidderApi
{
  @Autowired
  private CampaignService campaignService;

  @Autowired
  private BidResponseMapper bidResponseMapper;

  @Override
  public ResponseEntity<BidResponseDto> submitBid(@RequestBody BidRequestDto bidRequestDto) {

    // Get current campaigns
    List<Campaign> campaignList = campaignService.fetchAllAvailableCampaigns();

    // Match bid with campaigns
    List<Campaign> matchingCampaignList = campaignService.filterMatchingCampaigns(bidRequestDto, campaignList);


    if (matchingCampaignList.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Sort campaigns by price
    Optional<Campaign> winningCampaign = campaignService.getMostExpensiveCampaign(matchingCampaignList);

    // Respond with the correct structure
    return new ResponseEntity<>(bidResponseMapper.covertCampaignToBidResponseDto(bidRequestDto, winningCampaign.get()), HttpStatus.OK);
  }
}
