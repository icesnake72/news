package com.example.news.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class ArticleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Builder.Default
  private String author = "";

  @Column
  @Builder.Default
  private String title = "";

  @Column
  @Builder.Default
  private String description = "";

  @Column
  @Builder.Default
  private String url = "";

  @Column
  @Builder.Default
  private String imageUrl = "";

  @Column
  @CreationTimestamp
  private LocalDateTime publishedAt;

  @Column
  @Builder.Default
  private String content = "";

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

  @ManyToOne
  @JoinColumn(name = "source_id")
  private SourceEntity source;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private CountryEntity country;
}
