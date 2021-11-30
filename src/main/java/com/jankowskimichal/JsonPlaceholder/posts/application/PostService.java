package com.jankowskimichal.JsonPlaceholder.posts.application;

import com.jankowskimichal.JsonPlaceholder.network.port.RestService;
import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostUseCase;
import com.jankowskimichal.JsonPlaceholder.posts.domain.Post;
import com.jankowskimichal.JsonPlaceholder.storage.port.FileStorage;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class PostService implements PostUseCase {

    private final RestService restService;
    private final FileStorage fileStorage;

    @Override
    public void fetchAndStorePosts() {
        List<Post> post = restService.getJson("https://jsonplaceholder.typicode.com/posts");
        CollectionUtils.emptyIfNull(post).forEach(this::storePost);
    }

    private void storePost(Post post) {
        fileStorage.save(post.getTitle(), post.toString());
    }
}
