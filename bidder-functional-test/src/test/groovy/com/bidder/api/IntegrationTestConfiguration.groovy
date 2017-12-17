package com.bidder.api

import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

class IntegrationTestConfiguration {
    private final detachedMockFactory = new DetachedMockFactory()

//    @Bean
//    ExternalApiClient externalApiClient() {
//        detachedMockFactory.Mock(ExternalApiClient)
//    }
}