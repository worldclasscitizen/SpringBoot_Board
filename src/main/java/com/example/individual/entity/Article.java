package com.example.individual.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter // lombok Getter
@Setter // lombok Setter
@AllArgsConstructor // lombok All-Arguments-Constructor
@NoArgsConstructor // lombok No-Arguments-Constructor
@ToString // lombok ToString
// JPA Entity-Listener
@EntityListeners(AuditingEntityListener.class) // automatically manages information such as the entity’s creation time or modification time.
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.getTitle();
        }
        if(article.content != null) {
            this.content = article.getContent();
        }
    }
}
