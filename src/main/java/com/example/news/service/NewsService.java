package com.example.news.service;

import com.example.news.entity.ArticleEntity;
import com.example.news.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {
  private final ArticleRepository articleRepository;

  public List<ArticleEntity> findAll() {
    return articleRepository.findAll();
  }
}
