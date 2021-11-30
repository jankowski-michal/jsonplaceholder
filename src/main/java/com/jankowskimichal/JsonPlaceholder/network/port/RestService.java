package com.jankowskimichal.JsonPlaceholder.network.port;

public interface RestService {

    <T> T getJson(String url);
}
