package com.jankowskimichal.JsonPlaceholder.storage;

import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import org.springframework.stereotype.Service;

@Service
public class LocalFileStorage implements FileStorage {
    @Override
    public void save(String title, String content) {
        // todo
    }
}
