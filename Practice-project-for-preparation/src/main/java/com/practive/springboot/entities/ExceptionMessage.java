package com.practive.springboot.entities;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionMessage{

    private String errorMessage;
    private HttpStatus statusCode;
}
