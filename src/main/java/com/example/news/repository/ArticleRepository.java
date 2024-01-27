package com.example.news.repository;

import com.example.news.entity.ArticleEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
  Page<ArticleEntity> findAll(Pageable pageable);

  // title과 description에 대한 'like' 검색과 categoryId로 필터링
  @Query("SELECT a FROM ArticleEntity a WHERE a.category.id = :categoryId AND " +
      "(a.title LIKE %:titleKeyword% OR a.description LIKE %:descriptionKeyword%)")
  Page<ArticleEntity> findByCategoryAndTitleOrDescription(Long categoryId,
                                                          String titleKeyword,
                                                          String descriptionKeyword,
                                                          Pageable pageable);

  @Query("SELECT a FROM ArticleEntity a WHERE " +
      "a.title LIKE %:titleKeyword% OR a.description LIKE %:descriptionKeyword%")
  Page<ArticleEntity> findByTitleOrDescription(String titleKeyword,
                                               String descriptionKeyword,
                                               Pageable pageable);

}
