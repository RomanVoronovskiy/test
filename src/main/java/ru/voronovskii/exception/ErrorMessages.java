package ru.voronovskii.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    ARTICLE_LIST_IS_NULL("E001", "База пуста - статей нет"),
    ARTICLE_BY_ID_IS_NULL("E002", "С данным идентификатором статья не обнаружена"),
    ARTICLE_BY_ID_DELETE("E003", "Данная статья заблокирована, обратитесь к администратору"),
    ARTICLE_BY_ID_DELETE_LATE("E004", "Данная статья уже была удалена"),

    VALIDATION_ERROR("E005", "Ошибка валидации: %s");

    private final String errorCode;
    private final String errorCause;
}
