package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostFileMapper;
import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostUseCase;
import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import com.jankowskimichal.JsonPlaceholder.rest.port.RestService;
import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
class PostService implements PostUseCase {

    private final RestService restService;
    private final FileStorage fileStorage;
    private final PostFileMapper fileMapper;
    private final String url;

    public PostService(RestService restService, FileStorage fileStorage, PostFileMapper fileMapper,@Value("${posts.url}") String url) {
        this.restService = restService;
        this.fileStorage = fileStorage;
        this.fileMapper = fileMapper;
        this.url = url;
    }

    @Override
    public void fetchAndStorePosts() {
        Post[] posts = restService
                .getJson(url, Post[].class)
                .orElse(new Post[0]);
        Arrays.stream(posts).forEach(this::storePost);
    }

    private void storePost(Post post) {
        String fileName = fileMapper.getFileName(post);
        String content = fileMapper.getFileContent(post);
        fileStorage.save(fileName, content);
    }
}
