package com.example.individual.controller;

import com.example.individual.dto.CommentDto;
import com.example.individual.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 특정 게시물의 모든 댓글 조회(READ)
    @GetMapping("/articles/{id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") Long articleId) {
        List<CommentDto> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 새 댓글 생성하기(CREATE)
    @PostMapping("/articles/{id}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("id") Long articleId,
                                                    @RequestBody CommentDto dto) {
        CommentDto created = commentService.createComment(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 특정 댓글 수정하기(UPDATE)
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") Long commentId,
                                                    @RequestBody CommentDto dto) {
        CommentDto updated = commentService.updateComment(commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 특정 댓글 삭제하기(DELETE)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable("id") Long commentId) {
        CommentDto deleted = commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
