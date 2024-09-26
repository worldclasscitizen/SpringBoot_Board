package com.example.individual.controller;

import com.example.individual.dto.ArticleDto;
import com.example.individual.entity.Article;
import com.example.individual.service.ArticleService;
import com.example.individual.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    // 새 게시글 작성 페이지
    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    // 전체 게시글 조회(Read)
    @GetMapping
    public String getAllArticles(Model model) {
        model.addAttribute("articleList", articleService.getAllArticles());
        return "articles/list";
    }

    // 특정 게시글 조회(Read)
    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("articleList", articleService.getAllArticles());
        model.addAttribute("commentDtos", commentService.comments(id));
        return "articles/show";
    }

    // 새 게시글 작성(Post)
    @PostMapping
    public String createArticle(@ModelAttribute ArticleDto dto) {
        Article saved = articleService.createArticle(dto);
        return "redirect:/articles/" + saved.getId();
    }

    // 게시글 수정 페이지 요청(Edit)
    @GetMapping("/{id}/edit")
    public String editArticle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));
        return "articles/edit";
    }

    // 특정 게시글 수정(Patch)
    @PatchMapping("/{id}")
    public String updateArticle(@PathVariable("id") Long id, @ModelAttribute ArticleDto dto) {
        Article updated = articleService.updateArticle(id, dto);
        return "redirect:/articles/" + updated.getId();
    }

    // 특정 게시글 삭제(Delete)
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}
