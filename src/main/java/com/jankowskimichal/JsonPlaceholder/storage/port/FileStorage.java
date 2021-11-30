package com.jankowskimichal.JsonPlaceholder.storage.port;


public interface FileStorage {

    void save(String title, String content);
}
