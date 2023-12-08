package fr.fullstack.exam.api.config;

import fr.fullstack.exam.api.dto.ErrorMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.ReadOnlyFileSystemException;

@ControllerAdvice
@Slf4j
public class ExceptionConfig {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorMessageDto internalServerError(Exception ex) {
        ex.printStackTrace();
        return ErrorMessageDto.builder().code("INTERNAL_ERROR").message("An internal error occurred").build();
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessageDto resourceNotFound(ResourceNotFoundException ex) {
        return ErrorMessageDto.builder().code("NOT_FOUND").message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = ReadOnlyFileSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorMessageDto resourceAlreadyExists(ResourceNotFoundException ex) {
        return ErrorMessageDto.builder().code("ALREADY_EXISTS").message(ex.getMessage()).build();
    }
}
