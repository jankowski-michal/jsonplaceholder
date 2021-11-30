package com.jankowskimichal.JsonPlaceholder.rest;

import com.jankowskimichal.JsonPlaceholder.rest.port.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
class RestServiceImpl implements RestService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestServiceImpl.class);

    private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public <T> Optional<T> getJson(String url, Class<T> responseType) {
        return Optional.ofNullable(get(url, responseType));
    }

    private <T> T get(String url, Class<T> responseType) {
        LOGGER.info("Fetching GET {}", url);
        try {
            ResponseEntity<T> response = this.restTemplate.getForEntity(url, responseType);
            if (response.getStatusCode() == HttpStatus.OK) {
                LOGGER.info("Successfully fetched GET {}", url);
                return response.getBody();
            } else {
                LOGGER.info("Failed to GET {}, response status code: {}", url, response.getStatusCode());
            }
        } catch (RestClientException e) {
            LOGGER.info("Failed to GET {}, message: {}", url, e.getMessage());
        }
        return null;
    }
}
