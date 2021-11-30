package com.jankowskimichal.JsonPlaceholder.network;

import com.jankowskimichal.JsonPlaceholder.network.port.RestService;
import org.springframework.stereotype.Service;

@Service
class RestServiceImpl implements RestService {
    @Override
    public <T> T getJson(String url) {
        // todo
        return null;
    }
}
