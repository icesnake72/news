package com.example.news.service;

import com.example.news.dto.CategoryDTO;
import com.example.news.dto.CountryDTO;
import com.example.news.dto.SourceDTO;
import com.example.news.entity.ArticleEntity;
import com.example.news.entity.CategoryEntity;
import com.example.news.entity.CountryEntity;
import com.example.news.entity.SourceEntity;
import com.example.news.repository.ArticleRepository;
import com.example.news.repository.CategoryRepository;
import com.example.news.repository.CountryRepository;
import com.example.news.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;
  private final CountryRepository countryRepository;
  private final SourceRepository sourceRepository;

  public Page<ArticleEntity> findAll(int page, int size) {

    return articleRepository.findAll(PageRequest.of(page, size, Sort.by("publishedAt").descending()));
  }

  public void saveCategory(CategoryDTO categoryDTO) throws DataIntegrityViolationException, ConstraintViolationException {
    CategoryEntity categoryEntity = CategoryEntity.fromCategoryDTO(categoryDTO);
    categoryRepository.save(categoryEntity);
  }

  public List<CategoryDTO> findAllCategories() throws NullPointerException, IllegalArgumentException {
    List<CategoryEntity> categoryEntities = categoryRepository.findAll();
    List<CategoryDTO> categoryDTOs = new ArrayList<>();
    for (CategoryEntity categoryEntity : categoryEntities) {
      categoryDTOs.add(CategoryDTO.fromCategoryEntity(categoryEntity));
    }
    return categoryDTOs;
  }


  public List<CountryDTO> findAllCountries() {
    List<CountryEntity> countryEntities = countryRepository.findAll();
    List<CountryDTO> countryDTOs = new ArrayList<>();
    for (CountryEntity countryEntity : countryEntities) {
      countryDTOs.add(CountryDTO.fromCountryEntity(countryEntity));
    }
    return countryDTOs;
  }

  public void saveCountry(CountryDTO countryDTO) {
    CountryEntity countryEntity = CountryEntity.fromCountryDTO(countryDTO);
    countryRepository.save(countryEntity);
  }

  public List<SourceDTO> findAllSources() {
    List<SourceEntity> sourceEntities = sourceRepository.findAll();
    List<SourceDTO> sourceDTOs = new ArrayList<>();
    for (SourceEntity sourceEntity : sourceEntities) {
      sourceDTOs.add(SourceDTO.fromSourceEntity(sourceEntity));
    }
    return sourceDTOs;
  }
}
