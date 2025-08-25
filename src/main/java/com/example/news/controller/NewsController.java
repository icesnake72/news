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
    return "redirect:/articles";
  }

  @GetMapping("/articles")
  public String articles(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    try {
      Page<ArticleEntity> articles = newsService.findAll(page, size);
      List<CategoryDTO> categories = newsService.findAllCategories();
      int currentPage = articles.getNumber(); // 현재 페이지 번호
      int startPage = (currentPage/size)*10;  // 시작 페이지 계산
      int endPage = Math.min(articles.getTotalPages() - 1, startPage+9); // 끝 페이지 계산
      System.out.println("page : " + page + ", size : " + size + ", currentPage : " + currentPage + ", startPage : " + startPage + ", endPage : " + endPage);

      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
      model.addAttribute("articles", articles);
      model.addAttribute("categories", categories);

    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/index";
  }

  @GetMapping("/search")
  public String articlesByCategoryAndTitleOrDescription(Model model,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "") String keyword,
                                                        @RequestParam(defaultValue = "0") Long categoryId) {
    try {
      Page<ArticleEntity> articles = newsService.findByCategoryAndTitleOrDescription(categoryId, keyword, keyword, page, size);
      List<CategoryDTO> categories = newsService.findAllCategories();
      int currentPage = articles.getNumber(); // 현재 페이지 번호
      int startPage = (currentPage/size)*10;  // 시작 페이지 계산
      int endPage = Math.min(articles.getTotalPages() - 1, startPage+9); // 끝 페이지 계산
      //System.out.println("page : " + page + ", size : " + size + ", currentPage : " + currentPage + ", startPage : " + startPage + ", endPage : " + endPage);

      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
      model.addAttribute("articles", articles);
      model.addAttribute("categories", categories);
      model.addAttribute("keyword", keyword);
      model.addAttribute("categoryId", categoryId);

    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }
    return "/search";
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
