package ru.voronovskii.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.voronovskii.entity.Article;
import ru.voronovskii.entity.dto.ArticleDTO;
import ru.voronovskii.exception.UserErrorMessage;
import ru.voronovskii.service.ArticleService;
import ru.voronovskii.service.Validation;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
@Tag(name = "Контроллер для работы со статьями")
@Validated
public class ArticleController {
    @Autowired
    Validation validator;
    private final ArticleService articleService;

    @Operation(summary = "Создание статьи", description = "создание полной карточки статьи")
    @ApiResponse(responseCode = "200", description = "Статья успешно добавлена",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<HttpStatus> createArticle(
            @Validated({ArticleDTO.class}) @Valid @RequestBody ArticleDTO dto) {
        return articleService.createArticle(dto);
    }

    @Operation(summary = "Получение статей", description = "постарнично")
    @ApiResponse(responseCode = "200", description = "Статьи успешно получены",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Article>> getAllArticles(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return articleService.getAllArticles(pageNumber, pageSize);
    }

    @Operation(summary = "Получение статьи", description = "получение конкретной статьи по её идентификатору")
    @ApiResponse(responseCode = "200", description = "Статья успешно добавлена",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Article.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }


    @Operation(summary = "Обновление статьи", description = "обновление конкретной статьи по её идентификатору")
    @ApiResponse(responseCode = "200", description = "Статья успешно добавлена",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Article.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleDTO dto) {
        return articleService.updateArticle(id, dto);
    }

    @Operation(summary = "Удаление статьи", description = "удаление конкретной статьи по её идентификатору")
    @ApiResponse(responseCode = "200", description = "Статья успешно удалена",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

}
