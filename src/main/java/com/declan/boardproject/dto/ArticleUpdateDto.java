package com.declan.boardproject.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ArticleDto implements Serializable {

    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createdBy;

    protected ArticleDto() {}

    private ArticleDto(String title, String content, String hashtag, LocalDateTime createdAt, String createdBy) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public static ArticleDto of(String title, String content, String hashtag, LocalDateTime createdAt, String createdBy) {
        return new ArticleDto(title, content, hashtag, createdAt, createdBy);
    }

}
