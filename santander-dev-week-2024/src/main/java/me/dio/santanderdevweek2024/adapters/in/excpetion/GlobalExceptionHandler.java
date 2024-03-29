package me.dio.santanderdevweek2024.adapters.in.excpetion;


import me.dio.santanderdevweek2024.domain.exception.ChampionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ChampionNotFoundException.class)
    public APIError handleDomainException(ChampionNotFoundException domainError){
        return new APIError(domainError.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleDomainException(Exception unexpectedError){
        String message = "Ops. Um erro inesperado ocorreu, recarregue a página";
        logger.error(message,unexpectedError);
        return ResponseEntity.internalServerError().body(new APIError(message));
    }

    public record APIError(String message){}
}
