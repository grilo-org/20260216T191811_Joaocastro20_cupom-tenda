package com.tenda.cupom.model;

import com.tenda.cupom.enums.CupomStatus;
import com.tenda.cupom.exception.DescontoException;
import com.tenda.cupom.exception.ExpiracaoException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CupomTest {
    @Test
    void deveCriarCupomValido() {
        OffsetDateTime futuroUmDia = OffsetDateTime.now().plusDays(1);

        Cupom cupom = new Cupom(
                "abc123",
                "Cupom de teste",
                BigDecimal.valueOf(10.0),
                futuroUmDia
        );

        assertEquals("abc123", cupom.getCode());
        assertEquals("Cupom de teste", cupom.getDescription());
        assertEquals(BigDecimal.valueOf(10.0), cupom.getDiscountValue());
        assertEquals(futuroUmDia, cupom.getExpirationDate());
        assertEquals(CupomStatus.ACTIVE, cupom.getStatus());
        assertFalse(cupom.isPublished());
        assertFalse(cupom.isRedeemed());
    }

    @Test
    void deveLimparCaracteresEspeciaisDoCodigo() {
        OffsetDateTime futuroUmDia = OffsetDateTime.now().plusDays(1);

        Cupom cupom = new Cupom(
                "abc-123@#",
                "Teste",
                BigDecimal.valueOf(5),
                futuroUmDia
        );

        assertEquals("abc123", cupom.getCode());
    }

    @Test
    void deveLancarExcecaoQuandoDescontoForMenorQueMinimo() {
        OffsetDateTime futuroUmDia = OffsetDateTime.now().plusDays(1);
        BigDecimal desconto = BigDecimal.valueOf(0.4);

        DescontoException exception = assertThrows(
                DescontoException.class,
                () -> new Cupom(
                        "ABC123",
                        "Teste",
                        desconto,
                        futuroUmDia
                )
        );

        assertEquals("O valor do desconto de ser maior ou igual a 0,5", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDescontoForNull() {
        OffsetDateTime futuroUmDia = OffsetDateTime.now().plusDays(1);

        assertThrows(
                DescontoException.class,
                () -> new Cupom(
                        "ABC123",
                        "Teste",
                        null,
                        futuroUmDia
                )
        );
    }

    @Test
    void deveLancarExcecaoQuandoDataForNoPassado() {
        OffsetDateTime passadoUmDia = OffsetDateTime.now().minusDays(1);
        BigDecimal desconto = BigDecimal.valueOf(10);

        ExpiracaoException exception = assertThrows(
                ExpiracaoException.class,
                () -> new Cupom(
                        "ABC123",
                        "Teste",
                        desconto,
                        passadoUmDia
                )
        );

        assertEquals("A data de expiraçao nao pode ser no passado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDataForNull() {
        BigDecimal desconto = BigDecimal.valueOf(10);
        assertThrows(
                ExpiracaoException.class,
                () -> new Cupom(
                        "ABC123",
                        "Teste",
                        desconto,
                        null
                )
        );
    }
}
