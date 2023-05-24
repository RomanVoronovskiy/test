package ru.voronovskii.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.voronovskii.entity.Article;

import java.time.LocalDate;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findFirstByIdOrderByVersionDesc(Long id);

    @Override
    void deleteById(Long aLong);

    Long countByDatePublished(LocalDate date);
}
