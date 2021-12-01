package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostFileMapperImplTest {
    PostFileMapperImpl mapper = new PostFileMapperImpl();

    @Test
    public void getFileNameTest() {
        // given
        Post post = getPost(1L);

        // when
        String fileName = mapper.getFileName(post);


        //then
        assertEquals("1.json", fileName);
    }

    @Test
    public void getFileNameTestForNullId() {
        // given
        Post post = getPost(null);

        // when
        String fileName = mapper.getFileName(post);

        // then
        assertEquals("null.json", fileName);
    }

    private Post getPost(Long id) {
        Post post = new Post();
        post.setUserId(2L);
        post.setId(id);
        post.setTitle("a title");
        post.setBody("a body");
        return post;
    }

}