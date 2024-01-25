package com.example.news.repository;

import com.example.news.entity.ArticleEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
  Page<ArticleEntity> findAll(Pageable pageable);
}
