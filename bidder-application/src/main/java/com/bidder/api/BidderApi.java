package com.bidder.api;

import com.bidder.api.dto.request.BidRequestDto;
import com.bidder.api.dto.response.BidResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BidderApi {

    @RequestMapping(method = RequestMethod.POST, value = "/bid")
    ResponseEntity<BidResponseDto> submitBid(BidRequestDto bidRequestDto);
}
