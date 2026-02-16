package com.tenda.cupom.controller;

import com.tenda.cupom.model.Cupom;
import com.tenda.cupom.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("coupon")
public class CupomController {

    @Autowired
    CupomService cupomService;

    @PostMapping
    public Cupom salvar(@RequestBody Cupom cupom){
        return cupomService.salvar(cupom);
    }

    @GetMapping(value = "/{id}")
    public Optional<Cupom> buscarId(@PathVariable String id){
        return cupomService.buscarCode(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarId(@PathVariable String id){
        cupomService.deletarId(id);
    }
}
