package com.tenda.cupom.exception;

public class DescontoException extends RuntimeException{
    public DescontoException(){
        super("O valor do desconto de ser maior ou igual a 0,5");
    }
}
