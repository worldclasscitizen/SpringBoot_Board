package com.example.individual.entity;

import com.example.individual.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok Getter
@Setter // lombok Setter
@AllArgsConstructor // lombok All-Arguments-Constructor
@NoArgsConstructor // lombok No-Arguments-Constructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(Article article, CommentDto dto) {
        if(dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패. 댓글 Id 가 없어야 합니다.");
        }
        if(dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패. 게시글의 Id 가 잘못됐습니다.");
        }
        if(dto.getNickname() == null || dto.getNickname().trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 생성 실패. 닉네임을 작성해주세요.");
        }
        if(dto.getBody() == null || dto.getBody().trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 생성 실패. 내용을 작성해주세요.");
        }
        return new Comment(null,
                article,
                dto.getNickname(),
                dto.getBody());
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패. 댓글의 Id 가 잘못됐습니다.");
        }
        if(dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
