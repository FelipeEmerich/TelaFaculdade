package com.leandersonandre.agenda.core.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandersonandre.agenda.core.entity.Sala;
import com.leandersonandre.agenda.core.repository.SalaRepository;

@Service
public class SalaServico {

    @Autowired
    SalaRepository salaRepository;

    public List<Sala> obterTodos() {
        return salaRepository.findAll();
    }

    public Optional<Sala> obterPeloId(long id) {
        return salaRepository.findById(id);
    }

    public void salvar(Sala sala) {
        if(Strings.isBlank(sala.getNum())){
            throw new RuntimeException("Favor informar o numero da sala");
        }
        salaRepository.save(sala);
    }
}
