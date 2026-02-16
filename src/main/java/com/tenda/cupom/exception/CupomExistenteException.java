package com.tenda.cupom.exception;

public class CupomExistenteException extends RuntimeException{
    public CupomExistenteException(String code){
        super("Ja existe cupom com o codigo: " + code);
    }
}
