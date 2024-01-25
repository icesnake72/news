package com.example.news.dto;

import com.example.news.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDTO {
  private Long id;
  private String displayName;
  private String name;
  private String description;

  public static CategoryDTO fromCategoryEntity(@NonNull CategoryEntity categoryEntity) throws NullPointerException, IllegalArgumentException {
    return CategoryDTO.builder()
      .id(categoryEntity.getId())
      .name(categoryEntity.getName())
      .displayName(categoryEntity.getDisplayName())
      .description(categoryEntity.getDescription())
      .build();
  }
}
