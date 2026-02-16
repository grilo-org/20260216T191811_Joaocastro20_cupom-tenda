package com.tenda.cupom.exception;

public class CupomInexistenteException extends RuntimeException{
    public CupomInexistenteException(String code){
        super("Não existe cupom com o codigo: " + code);
    }
}
