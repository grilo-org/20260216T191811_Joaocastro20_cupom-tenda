package com.tenda.cupom.repository;

import com.tenda.cupom.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CupomRepository extends JpaRepository<Cupom, Long> {
    Optional<Cupom> findByCode(String code);

    void deleteByCode(String code);

    boolean existsByCode(String code);
}
