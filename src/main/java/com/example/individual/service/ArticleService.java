package com.example.individual.service;

import com.example.individual.dto.ArticleDto;
import com.example.individual.entity.Article;
import com.example.individual.entity.Comment;
import com.example.individual.repository.ArticleRepository;
import com.example.individual.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 전체 게시글 조회
    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articleRepository.findAll();
    }

    // 특정 게시글 조회
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 새 게시글 작성(Post)
    public Article createArticle(ArticleDto dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) { return null; }
        return articleRepository.save(article);
    }

    // 특정 게시글 수정(Patch)
    public Article updateArticle(Long id, ArticleDto dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        log.info("dto = " + dto);
        if(target == null || id != article.getId()) { return null; }
        target.patch(article);
        return articleRepository.save(target);
    }

    // 특정 게시글 삭제(Delete)
    public Article deleteArticle(Long id) {
        // 삭제할 게시글 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 게시글에 딸린 댓글들도 삭제
        List<Comment> comments = commentRepository.findByArticleId(id);
        commentRepository.deleteAll(comments);

        // 삭제할 게시글이 없으면 패스
        if(target == null) { return null; }

        // 삭제할 게시글이 있으면 삭제
        articleRepository.delete(target);
        return target;
    }
}
