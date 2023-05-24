package ru.voronovskii.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные пользователя")
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Builder.Default
    @JsonIgnore
    @Schema(description = "Версия статьи", example = "17")
    @Column(name = "version")
    @Min(1)
    private short version = 1;

    @Builder.Default
    @JsonIgnore
    @Schema(description = "Флаг видимости статьи", example = "true")
    @Column(name = "enable")
    private Boolean isEnable = true;

    @NotNull
    @Schema(description = "ID партнёра CRM", example = "ba4e7665-7335-4871-9b89-1a10375e7e8f")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Schema(description = "ID партнёра CRM", example = "ba4e7665-7335-4871-9b89-1a10375e7e8f")
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Column(name = "content", columnDefinition = "text", nullable = false)
    @Schema(description = "ID партнёра CRM", example = "ba4e7665-7335-4871-9b89-1a10375e7e8f")
    private String content;

    @NotNull
    @Schema(description = "ID партнёра CRM", example = "ba4e7665-7335-4871-9b89-1a10375e7e8f")
    @Column(name = "date_published", nullable = false)
    private LocalDate datePublished;

}
