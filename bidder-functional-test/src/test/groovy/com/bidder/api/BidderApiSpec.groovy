package com.bidder.api

import com.bidder.api.dto.request.App
import com.bidder.api.dto.request.BidRequestDto
import com.bidder.api.dto.request.Device
import com.bidder.api.dto.request.Geo
import com.bidder.api.dto.request.OperatingSystem
import com.bidder.api.dto.response.BidResponseDto
import com.bidder.config.Application
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = Application.class, webEnvironment = DEFINED_PORT)
class BidderApiSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    def "posting a bid request that can be matched responds with status 200 and the proper bid info"() {

        given: "the campaign request responds with the mock campaigns"
          wireMockRule.stubFor(get(urlPathEqualTo("/"))
            .willReturn(aResponse()
              .withBodyFile("../../../../../bidder-functional-test/src/test/resources/__files/mockCampaignApiResponse.json")
              .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
              .withStatus(200)))

        and: "the bid request is a matching one"

          BidRequestDto bidRequestDto = new BidRequestDto(
              id: "e7fe51ce4f6376876353ff0961c2cb0d",
              app: new App(id: "e7fe51ce-4f63-7687-6353-ff0961c2cb0d", name: "Morecast Weather"),
              device: new Device(os: OperatingSystem.Android, geo: new Geo(country: "USA", lat: 0, lon:0))
          )

        when: "the bid gets posted from the exchange"
          ResponseEntity<BidResponseDto> entity = restTemplate.postForEntity('http://localhost/bid', bidRequestDto, BidResponseDto.class)

        then: "the response of the bidder is the expected campaign"
          entity.statusCodeValue == 200
          entity.body.id == 'e7fe51ce4f6376876353ff0961c2cb0d'
          entity.body.bid.campaignId == '5a3dce46'
          entity.body.bid.price  == 1.23
    }

    def "posting a bid request that can NOT be matched responds with status 204 and NO CONTENT"() {

        given: "the campaign request responds with the mock campaigns"
          wireMockRule.stubFor(get(urlPathEqualTo("/"))
            .willReturn(aResponse()
              .withBodyFile("../../../../../bidder-functional-test/src/test/resources/__files/mockCampaignApiResponse.json")
              .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
              .withStatus(200)))

        and: "the bid request is NOT a matching one with the already known campaigns"
          BidRequestDto bidRequestDto = new BidRequestDto(
              id: "e7fe51ce4f6376876353ff0961c2cb0d",
              app: new App(id: "e7fe51ce-4f63-7687-6353-ff0961c2cb0d", name: "Morecast Weather"),
              device: new Device(os: OperatingSystem.Android, geo: new Geo(country: "CYP", lat: 0, lon:0))
          )

        when: "the bid gets posted from the exchange"
          ResponseEntity<BidResponseDto> entity = restTemplate.postForEntity('http://localhost/bid', bidRequestDto, BidResponseDto.class)

        then: "the response of the bidder is 204 No Content"
          entity.statusCodeValue == 204
          entity.body == null
    }
}
