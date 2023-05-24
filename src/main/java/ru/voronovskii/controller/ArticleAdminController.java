package ru.voronovskii.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.voronovskii.exception.UserErrorMessage;
import ru.voronovskii.service.ArticleService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
@Tag(name = "Административный контроллер для работы со статьями")
@PreAuthorize("hasRole('ADMIN')")
public class ArticleAdminController {
    private final ArticleService articleService;

    @Operation(summary = "Получение статистики", description = "Получение статистики по статьям за последние дни")
    @ApiResponse(responseCode = "200", description = "Статья успешно добавлена",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))})
    @ApiResponse(responseCode = "400", description = "описание ошибки согласно документации",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserErrorMessage.class))})
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getPublishedArticlesCount(@RequestParam Integer duration) {
       return articleService.getCount(duration);
    }
}
