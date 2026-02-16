package com.tenda.cupom.exception.handler;

import com.tenda.cupom.exception.CupomExistenteException;
import com.tenda.cupom.exception.CupomInexistenteException;
import com.tenda.cupom.exception.DescontoException;
import com.tenda.cupom.exception.ExpiracaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CupomExistenteException.class)
    public ResponseEntity<String> handleCupomExistente(CupomExistenteException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CupomInexistenteException.class)
    public ResponseEntity<String> handleCupomInexistente(CupomInexistenteException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DescontoException.class)
    public ResponseEntity<String> handleDesconto(DescontoException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ExpiracaoException.class)
    public ResponseEntity<String> handleExpiracao(ExpiracaoException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
