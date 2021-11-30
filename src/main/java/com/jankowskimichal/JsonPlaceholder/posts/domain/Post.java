package com.jankowskimichal.JsonPlaceholder.posts.domain;

import lombok.Data;

@Data
public class Post {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
