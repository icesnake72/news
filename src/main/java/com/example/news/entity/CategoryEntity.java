package com.example.news.entity;

import com.example.news.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String displayName;

  @Column(nullable = false, unique = true, length = 100)
  private String name;

  @Column
  @Builder.Default
  private String description = "";

  public static CategoryEntity fromCategoryDTO(CategoryDTO categoryDTO) {
    return CategoryEntity.builder()
      .name(categoryDTO.getName())
      .displayName(categoryDTO.getDisplayName())
      .description(categoryDTO.getDescription())
      .build();
  }
}
