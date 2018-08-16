package riverway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riverway.Exception.UnAuthenticationException;
import riverway.Exception.UnAutorizedException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class SecurityControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String emptyResultData() {
        log.debug("EntityNotFoundException");
        return "데이터를 찾을 수 없습니다.";
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void unAuthentication() {
        log.debug("UnAuthenticationException");
    }

    @ExceptionHandler(UnAutorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void unAutorized() {
        log.debug("UnAutorizedException");
    }
}
