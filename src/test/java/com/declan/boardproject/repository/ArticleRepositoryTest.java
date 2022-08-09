package com.declan.boardproject.repository;

import com.declan.boardproject.config.JpaConfig;
import com.declan.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 테스트")
@Import(JpaConfig.class)
@Transactional
@SpringBootTest
class ArticleRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public ArticleRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("SELECT 테스트")
    @Test
    void 셀렉트테스트() {
        List<Article> articles = articleRepository.findAll();

        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("INSERT 테스트")
    @Test
    void 인설트테스트() {
        long prvCount = articleRepository.count();
        articleRepository.save(Article.of("new article", "new content", "#spring"));

        assertThat(articleRepository.count()).isEqualTo(prvCount + 1);

    }

    @DisplayName("UPDATE 테스트")
    @Test
    void 업데이트테스트() {
        Article article = articleRepository.findById(1L).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재 하지 않습니다.");
            }
        });
        article.setHashtag("#springboot");

        Article savedArticle = articleRepository.saveAndFlush(article); // 업데이트 할 경우 Flush로 밀어줘야한다.

        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", "#springboot");

    }

    @DisplayName("DELETE 테스트")
    @Test
    void 들리트테스트() {
        Article article = articleRepository.findById(1L).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재 하지 않습니다.");
            }
        });

        long preArticleCount = articleRepository.count(); // 기존 게시판 수
        long preArticleCommentCount = articleCommentRepository.count(); // 기존 게시판 댓글 수
        int deleteArticlesSize = article.getArticleComments().size(); // 해당 아이디의 댓글 수 (게시판 삭제하면 댓글도 다 같이 삭제되기 때문에 값을 지정해줘야한다.)

       articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(preArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(preArticleCommentCount - deleteArticlesSize);

    }

}
