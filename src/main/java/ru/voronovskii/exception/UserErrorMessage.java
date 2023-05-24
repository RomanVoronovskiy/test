package ru.voronovskii.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserErrorMessage {

    private Date timestamp;
    private String errorCode;
    private String errorCause;
}
