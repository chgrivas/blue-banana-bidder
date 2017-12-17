package com.bidder.api

import com.bidder.api.dto.request.BidRequestDto
import com.bidder.mapper.BidResponseMapper
import com.bidder.service.CampaignService
import spock.lang.Specification

class BidderApiImplSpec extends Specification {

    BidderApi bidderApi
    CampaignService mockCampaignService
    BidResponseMapper mockBidResponseMapper

    def setup() {
        mockCampaignService = Mock(CampaignService)
        mockBidResponseMapper = Mock(BidResponseMapper)
        bidderApi = new BidderApiImpl(campaignService: mockCampaignService, bidResponseMapper: mockBidResponseMapper)
    }

    def "no matching campaigns result to 204 no content"() {
        given:
            BidRequestDto bidRequestDto = new BidRequestDto()
        when:
            def response = bidderApi.submitBid(bidRequestDto)
        then:
            1 * mockCampaignService.fetchAllAvailableCampaigns() >> []
            1 * mockCampaignService.filterMatchingCampaigns(_, _) >> []
            response.statusCodeValue == 204
    }
}
