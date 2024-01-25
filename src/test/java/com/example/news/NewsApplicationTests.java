package com.example.news;

import com.example.news.dto.CategoryDTO;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class NewsApplicationTests {
	private final NewsService newsService;

	@Test
	void contextLoads() {
	}

	@Test
	void testSaveCategory() {
		//newsService.saveCategory(new CategoryDTO("IT", "Information Technology"));
	}

}
