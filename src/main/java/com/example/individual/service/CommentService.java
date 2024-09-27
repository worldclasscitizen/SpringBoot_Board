package com.example.individual.service;

import com.example.individual.dto.CommentDto;
import com.example.individual.entity.Article;
import com.example.individual.entity.Comment;
import com.example.individual.repository.ArticleRepository;
import com.example.individual.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    // 특정 게시물의 모든 댓글 읽기
    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    // 새 댓글 생성하기
    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패. 대상 게시글이 없습니다."));
        Comment comment = Comment.createComment(article, dto);

        Comment saved = commentRepository.save(comment);
        return CommentDto.createCommentDto(saved);
    }

    // 특정 댓글 수정하기
    public CommentDto updateComment(Long commentId, CommentDto dto) {
        Comment target = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패. 대상 댓글이 없습니다."));
        target.patch(dto);

        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }

    // 특정 댓글 삭제하기
    public CommentDto deleteComment(Long commentId) {
        Comment target = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패. 대상 댓글이 없습니다."));

        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
