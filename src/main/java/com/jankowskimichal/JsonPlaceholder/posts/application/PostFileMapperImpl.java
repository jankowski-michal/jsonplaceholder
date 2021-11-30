package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostFileMapper;
import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class PostFileMapperImpl implements PostFileMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostFileMapperImpl.class);

    private final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public String getFileName(Post post) {
        return post.getId() + ".json";
    }

    @Override
    public String getFileContent(Post post) {
        try {
            return writer.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            LOGGER.info("Failed to format post to JSON, post: {}", post.toString());
            return "";
        }
    }
}
