package com.declan.boardproject.repository;

import com.declan.boardproject.config.JpaConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Jpa 테스트")
@Import(JpaConfig.class)
@RequiredArgsConstructor
@DataJpaTest
class ArticleCommentRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public void given_when_then() {

        // given

        // when

        // then
    }

}