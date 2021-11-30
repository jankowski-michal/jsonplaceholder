package com.jankowskimichal.JsonPlaceholder.network.port;

import java.util.Optional;

public interface RestService {

    <T> Optional<T> getJson(String url, Class<T> responseType);
}
