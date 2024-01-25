package com.example.news.controller;

import com.example.news.dto.CategoryDTO;
import com.example.news.dto.CountryDTO;
import com.example.news.dto.SourceDTO;
import com.example.news.entity.ArticleEntity;
import com.example.news.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

  private final NewsService newsService;

  @GetMapping("/")
  public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
    try {
      Page<ArticleEntity> articles = newsService.findAll(0, 10);
      for (ArticleEntity article : articles) {
        System.out.println(article);
      }

      model.addAttribute("articles", articles);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/index";
  }

  @GetMapping("/articles")
  public String articles(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    try {
      Page<ArticleEntity> articles = newsService.findAll(page, size);
      model.addAttribute("articles", articles);

    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/index";
  }

  @GetMapping("/categoryInputForm")
  public String categoryInput() {
    return "/categoryInput";
  }

  @PostMapping("/categorySave")
  public String categorySave(@ModelAttribute CategoryDTO categoryDTO, Model model) {
    try {
      newsService.saveCategory(categoryDTO);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }

    return "redirect:/categories";
  }

  @GetMapping("/categories")
  public String categories(Model model) {
    try {
      List<CategoryDTO> categories = newsService.findAllCategories();
      model.addAttribute("categories", categories);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/categories";
  }

  @GetMapping("/countryInputForm")
  public String countryInput() {
    return "/countryInput";
  }

  @GetMapping("/countries")
  public String countries(Model model) {
    try {
      List<CountryDTO> countries = newsService.findAllCountries();
      model.addAttribute("countries", countries);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/countries";
  }

  @PostMapping("/countrySave")
  public String countrySave(@ModelAttribute CountryDTO countryDTO, Model model) {
    try {
      newsService.saveCountry(countryDTO);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }

    return "redirect:/countries";
  }

  @GetMapping("/sources")
  public String sources(Model model) {
    try {
      List<SourceDTO> sources = newsService.findAllSources();
      model.addAttribute("sources", sources);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/sources";
  }
}
