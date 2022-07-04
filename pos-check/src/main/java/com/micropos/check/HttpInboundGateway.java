package com.micropos.check;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;

@Component
public class HttpInboundGateway {

    @Bean
    public IntegrationFlow inGate() {
        return IntegrationFlows.from(Http.inboundGateway("/api/check"))
                .headerFilter("accept-encoding", false)
                .channel("sampleChannel")
                .get();
    }
}