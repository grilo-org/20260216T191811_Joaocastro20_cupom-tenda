package com.tenda.cupom.exception;

public class ExpiracaoException extends RuntimeException{
    public ExpiracaoException(){
        super("A data de expiraçao nao pode ser no passado");
    }
}
