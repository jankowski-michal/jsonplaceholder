package com.jankowskimichal.JsonPlaceholder.storage;

import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
class LocalFileStorage implements FileStorage {
    private final static Logger LOGGER = LoggerFactory.getLogger(LocalFileStorage.class);

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public void save(String title, String content) {
        LOGGER.info("Saving file {}", title);
        try {
            FileUtils.writeStringToFile(new File(title), content, charset);
        } catch (IOException e) {
            LOGGER.info("Error while saving file {}, error message: {}", title, e.getMessage());
        }
    }
}
