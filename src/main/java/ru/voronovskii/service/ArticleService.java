package ru.voronovskii.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.voronovskii.entity.Article;
import ru.voronovskii.entity.dto.ArticleDTO;
import ru.voronovskii.exception.ErrorMessages;
import ru.voronovskii.exception.UserServiceException;
import ru.voronovskii.repository.ArticleRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    /**
     * @param dto статья которую необходимо создать
     * @return Статус создания статьи
     */
    public ResponseEntity<HttpStatus> createArticle(ArticleDTO dto) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article, "id");
        article.setDatePublished(LocalDate.now());
        articleRepository.save(article);
        return ResponseEntity.ok().build();
    }

    /**
     * @return список всех статей
     */
    public ResponseEntity<Page<Article>> getAllArticles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Article> articlePage = articleRepository.findAll(pageable);
        if (articlePage.getContent().isEmpty()) {
            throw new UserServiceException(ErrorMessages.ARTICLE_LIST_IS_NULL);
        }
        return ResponseEntity.ok(articlePage);
    }

    /**
     * @param id идентификатор статьи
     * @return статья, если такая имеется
     */
    public ResponseEntity<Article> getArticleById(Long id) {
        Optional<Article> articleOpt = articleRepository.findFirstByIdOrderByVersionDesc(id);
        if (articleOpt.isEmpty()) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_IS_NULL);
        }
        if (articleOpt.get().getIsEnable().equals(false)) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_DELETE);
        }
        return ResponseEntity.ok(articleOpt.get());
    }

    /**
     * @param id  идентификатор статьи которую необходимо обновить
     * @param dto данные для обновления статьи
     * @return обновленный вид статьи
     */
    public ResponseEntity<Article> updateArticle(Long id, ArticleDTO dto) {
        Optional<Article> articleOpt = articleRepository.findFirstByIdOrderByVersionDesc(id);
        if (articleOpt.isEmpty()) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_IS_NULL);
        }
        if (articleOpt.get().getIsEnable().equals(false)) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_DELETE);
        }
        short oldVerse = articleOpt.get().getVersion();
        short newVerse = ++oldVerse;
        Article article = articleOpt.get();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setDatePublished(dto.getDatePublished());
        article.setVersion(newVerse);
        articleRepository.saveAndFlush(article);
        return ResponseEntity.ok(article);
    }

    /**
     * @param id идентификатор карточки
     * @return статус удаления карточки
     */
    public ResponseEntity<HttpStatus> deleteArticle(Long id) {
        Optional<Article> articleOpt = articleRepository.findFirstByIdOrderByVersionDesc(id);
        if (articleOpt.isEmpty()) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_IS_NULL);
        }
        if (articleOpt.get().getIsEnable().equals(false)) {
            throw new UserServiceException(ErrorMessages.ARTICLE_BY_ID_DELETE_LATE);
        }

        articleRepository.deleteById(articleOpt.get().getId());
        return ResponseEntity.ok().build();
    }

    /**
     * @param duration продолжительность наблюдения с текущего дня
     * @return статистику по каждому дню
     */
    public ResponseEntity<Map<String, Long>> getCount(Integer duration) {
        Map<String, Long> countMap = new LinkedHashMap<>();
        LocalDate startDate = LocalDate.now().minusDays(duration - 1);
        for (int i = 0; i < duration; i++) {
            LocalDate date = startDate.plusDays(i);
            Long count = articleRepository.countByDatePublished(date);
            countMap.put(date.toString(), count);
        }
        return ResponseEntity.ok(countMap);
    }
}
