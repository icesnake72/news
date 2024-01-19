package com.example.news.controller;

import com.example.news.entity.ArticleEntity;
import com.example.news.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

  private final NewsService newsService;

  @GetMapping("/")
  public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

    List<ArticleEntity> articles = newsService.findAll();
    model.addAttribute("articles", articles);

    return "index";
  }

  @GetMapping("/categoryInputForm")
  public String categoryInput() {
    return "categoryInput";
  }
}
