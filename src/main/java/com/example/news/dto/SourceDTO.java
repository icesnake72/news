package com.example.news.dto;

import lombok.*;
import com.example.news.entity.SourceEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SourceDTO {
  private Long id;
  private String ids;
  private String name;

  public static SourceDTO fromSourceEntity(@NonNull SourceEntity sourceEntity) {
    return SourceDTO.builder()
      .id(sourceEntity.getId())
      .ids(sourceEntity.getIds())
      .name(sourceEntity.getName())
      .build();
  }
}
