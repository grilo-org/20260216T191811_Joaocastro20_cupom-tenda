package com.tenda.cupom.service;

import com.tenda.cupom.exception.CupomExistenteException;
import com.tenda.cupom.exception.CupomInexistenteException;
import com.tenda.cupom.model.Cupom;
import com.tenda.cupom.repository.CupomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CupomServiceTest {

    @Mock
    private CupomRepository repository;

    @InjectMocks
    private CupomService service;

    //METODO SALVAR

    @Test
    void deveSalvarCupomQuandoCodigoNaoExiste() {
        Cupom cupom = new Cupom(
                "ABC123",
                "Cupom teste",
                BigDecimal.valueOf(1.0),
                OffsetDateTime.now().plusDays(5)
        );

        when(repository.existsByCode("ABC123")).thenReturn(false);
        when(repository.save(cupom)).thenReturn(cupom);

        Cupom salvo = service.salvar(cupom);

        assertNotNull(salvo);
        verify(repository).existsByCode("ABC123");
        verify(repository).save(cupom);
    }

    @Test
    void deveLancarExcecaoQuandoCodigoJaExiste() {
        Cupom cupom = new Cupom(
                "ABC123",
                "Cupom duplicado",
                BigDecimal.valueOf(1.0),
                OffsetDateTime.now().plusDays(5)
        );

        when(repository.existsByCode("ABC123")).thenReturn(true);

        CupomExistenteException exception = assertThrows(
                CupomExistenteException.class,
                () -> service.salvar(cupom)
        );

        assertEquals("Ja existe cupom com o codigo: ABC123", exception.getMessage());

        verify(repository).existsByCode("ABC123");
        verify(repository, never()).save(any());
    }

    //METODO BUSCAR
    @Test
    void deveRetornarCupomQuandoCodeExistir() {
        Cupom cupom = new Cupom(
                "ABC123",
                "Cupom teste",
                BigDecimal.valueOf(1.0),
                OffsetDateTime.now().plusDays(5)
        );

        when(repository.findByCode("ABC123"))
                .thenReturn(Optional.of(cupom));

        Optional<Cupom> resultado = service.buscarCode("ABC123");

        assertTrue(resultado.isPresent());
        assertEquals("ABC123", resultado.get().getCode());
        verify(repository).findByCode("ABC123");
    }

    @Test
    void deveLancarExceptionQuandoCodeNaoExistir() {
        when(repository.findByCode("TESTE"))
                .thenReturn(Optional.empty());

        assertThrows(CupomInexistenteException.class, () -> {
            service.buscarCode("TESTE");
        });

        verify(repository).findByCode("TESTE");
    }

    //METODO DELETAR
    @Test
    void deveDeletarQuandoCodeExistir() {
        String code = "ABC123";

        when(repository.existsByCode(code)).thenReturn(true);

        service.deletarId(code);

        verify(repository).existsByCode(code);
        verify(repository).deleteByCode(code);
    }

    @Test
    void deveLancarExcecaoQuandoCodeNaoExistir() {
        String code = "TESTE";

        when(repository.existsByCode(code)).thenReturn(false);

        CupomInexistenteException exception = assertThrows(
                CupomInexistenteException.class,
                () -> service.deletarId(code)
        );

        assertEquals("Não existe cupom com o codigo: TESTE", exception.getMessage());

        verify(repository).existsByCode(code);
        verify(repository, never()).deleteByCode(code);
    }
}
