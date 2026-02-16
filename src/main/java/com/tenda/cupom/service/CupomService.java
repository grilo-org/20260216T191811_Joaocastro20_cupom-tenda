package com.tenda.cupom.service;

import com.tenda.cupom.exception.CupomExistenteException;
import com.tenda.cupom.exception.CupomInexistenteException;
import com.tenda.cupom.model.Cupom;
import com.tenda.cupom.repository.CupomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;

    public Cupom salvar(Cupom cupom){
        if (repository.existsByCode(cupom.getCode())) {
            throw new CupomExistenteException(cupom.getCode());
        }
        return repository.save(cupom);
    }

    public Optional<Cupom> buscarCode(String code){
        return Optional.ofNullable(repository.findByCode(code).orElseThrow(() -> new CupomInexistenteException(code)));
    }

    @Transactional
    public void deletarId(String code) {
        if (!repository.existsByCode(code)) {
            throw new CupomInexistenteException(code);
        }
        repository.deleteByCode(code);
    }
}
