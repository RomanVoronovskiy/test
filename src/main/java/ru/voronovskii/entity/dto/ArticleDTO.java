package ru.voronovskii.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Статья")
@AllArgsConstructor
public class ArticleDTO {

    @JsonIgnore
    private long id;

    @JsonIgnore
    @Builder.Default
    @Schema(description = "Версия карточки", example = "17")
    @Min(1)
    private short version = 1;

    @Builder.Default
    @JsonIgnore
    @Schema(description = "Флаг видимости статьи", example = "true")
    private boolean isEnable = true;

    @NotBlank(message = "поле \"Заголовок\" - обязательное")
    @Size(max = 100, message = "Заголовок не должен превышать 100 символов")
    @Schema(description = "Заголовок", example = "Колыбель для кошки")
    private String title;

    @NotBlank(message = "поле \"Автор\" - обязательное")
    @Schema(description = "Автор", example = "Курт Воннегут")
    private String author;

    @NotBlank(message = "поле \"Описание\" - обязательное")
    @Schema(description = "Описание", example = "Рассказчик — писатель, задумавший книгу об атомной бомбе...")
    private String content;

    @NotBlank(message = "поле \"Дата публикации\" - обязательное")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema(description = "Дата публикации", example = "ba4e7665-7335-4871-9b89-1a10375e7e8f")
    private LocalDate datePublished;

}
