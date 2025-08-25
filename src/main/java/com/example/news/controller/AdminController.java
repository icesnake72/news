package com.example.news.controller;

import com.example.news.dto.CategoryDTO;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  private final NewsService newsService;

  @GetMapping("/main")
  public String adminPage() {
    return "redirect:/admin/category";
  }

  @GetMapping("/category")
  public String categoryPage(Model model) {

    try {
      List<CategoryDTO> categories = newsService.findAllCategories();
      model.addAttribute("categories", categories);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
      return "/error";
    }

    return "/admin/categories";
  }

  @GetMapping("/categoryInputForm")
  public String categoryInput() {
    return "/admin/categoryInput";
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

}
