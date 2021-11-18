package com.arthurezeagbo.exercise2.model.response;

import com.arthurezeagbo.exercise2.config.StatusCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {

    private String statusMessage;
    private StatusCodes status;
    private T data;

}
