package com.jankowskimichal.JsonPlaceholder;

import com.jankowskimichal.JsonPlaceholder.posts.application.port.PostUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonPlaceholderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JsonPlaceholderApplication.class, args);
	}

	private final PostUseCase postUseCase;

	public JsonPlaceholderApplication(PostUseCase postUseCase) {
		this.postUseCase = postUseCase;
	}

	@Override
	public void run(String... args) throws Exception {
		postUseCase.fetchAndStorePosts();
	}
}
