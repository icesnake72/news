package com.example.news.dto;

import lombok.*;
import com.example.news.entity.CountryEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CountryDTO {
  private Long id;
  private String c_code;
  private String name;

  public static CountryDTO fromCountryEntity(@NonNull CountryEntity countryEntity) throws NullPointerException, IllegalArgumentException {
    return CountryDTO.builder()
      .id(countryEntity.getId())
      .c_code(countryEntity.getC_code())
      .name(countryEntity.getName())
      .build();
  }
}
