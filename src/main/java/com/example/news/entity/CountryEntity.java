package com.example.news.entity;

import com.example.news.dto.CountryDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class CountryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String c_code;

  @Column(nullable = false, unique = true)
  private String name;

  public static CountryEntity fromCountryDTO(CountryDTO countryDTO) {
    return CountryEntity.builder()
      .c_code(countryDTO.getC_code())
      .name(countryDTO.getName())
      .build();
  }
}
