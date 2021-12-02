package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostFileMapper;
import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import com.jankowskimichal.JsonPlaceholder.rest.port.RestService;
import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@SpringBootTest
class PostServiceIT {
    final String url = "https://jsonplaceholder.typicode.com/posts";
    final Charset charset = StandardCharsets.UTF_8;

    @MockBean
    RestService restService;
    @Autowired
    FileStorage fileStorage;
    @Autowired
    PostFileMapper fileMapper;
    @Autowired
    PostService service;

    @BeforeEach
    void setUp() {
        // fixme - use temp dir
        deleteFile("1.json");
        deleteFile("2.json");
    }

    @AfterEach
    void tearDown() {
        deleteFile("1.json");
        deleteFile("2.json");
    }

    private void deleteFile(String fileName) {
        try {
            FileUtils.delete(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchAndStorePostsTest() throws IOException {
        // given
        Post[] posts = {getPost(1L, "body 1"), getPost(2L, "body 2")};
        Mockito.when(restService.getJson(url, Post[].class)).thenReturn(Optional.of(posts));

        // when
        service.fetchAndStorePosts();

        // then
        String file1Content = FileUtils.readFileToString(new File("1.json"), charset);
        String file2Content = FileUtils.readFileToString(new File("2.json"), charset);
        Assertions.assertEquals(
                "[ {\n" +
                        "  \"userId\" : 1,\n" +
                        "  \"id\" : 1,\n" +
                        "  \"title\" : \"a title\",\n" +
                        "  \"body\" : \"body 1\"\n" +
                        "} ]", file1Content);
        Assertions.assertEquals(
                "[ {\n" +
                        "  \"userId\" : 2,\n" +
                        "  \"id\" : 1,\n" +
                        "  \"title\" : \"a title\",\n" +
                        "  \"body\" : \"body 2\"\n" +
                        "} ]", file2Content);
    }

    private Post getPost(Long userId, String body) {
        Post post = new Post();
        post.setUserId(userId);
        post.setId(1L);
        post.setTitle("a title");
        post.setBody(body);
        return post;
    }
}