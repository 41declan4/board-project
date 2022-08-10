package com.declan.boardproject.dto;

import com.declan.boardproject.domain.Article;
import com.declan.boardproject.domain.ArticleComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleCommentDto {

    private Long id;
    private Long articleId;
    private UserAccountDto userAccountDto;
    private String content;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    protected ArticleCommentDto() {
    }

    public ArticleCommentDto(Long id, Long articleId, UserAccountDto userAccountDto, String content, String createdBy, String modifiedBy, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.articleId = articleId;
        this.userAccountDto = userAccountDto;
        this.content = content;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, userAccountDto, content, modifiedBy, createdBy, modifiedAt, createdAt);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                        entity.getId(),
                        entity.getArticle().getId(),
                        UserAccountDto.from(entity.getUserAccount()),
                        entity.getContent(),
                entity.getModifiedBy(),
                        entity.getCreatedBy(),
                        entity.getModifiedAt(),
                entity.getCreatedAt()
                );
    }

    public ArticleComment toEntity(Article entity) {
        return ArticleComment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );
    }
}
