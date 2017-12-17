package com.bidder.gateway;

import com.bidder.domain.Campaign;
import com.bidder.domain.CampaignList;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CampaignGatewayImpl implements CampaignGateway  {

    @Value("${campaign.service.url}")
    private String campaignServiceUrl;

    @Override
    public List<Campaign> getAllCampaigns() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(campaignServiceUrl);
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(restTemplate.getForObject(uriBuilder.toUriString(), Campaign[].class));
    }
}