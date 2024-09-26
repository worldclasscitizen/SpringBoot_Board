package com.example.individual.dto;


import com.example.individual.entity.Article;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString // lombok ToString
@NoArgsConstructor
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
