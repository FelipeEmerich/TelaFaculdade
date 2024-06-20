package com.leandersonandre.agenda.core.service;

import com.leandersonandre.agenda.core.entity.Disciplina;
import com.leandersonandre.agenda.core.repository.DisciplinaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServico {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public List<Disciplina> obterTodos() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> obterPeloId(long id) {
        return disciplinaRepository.findById(id);
    }

    public void salvar(Disciplina disciplina) {
        if(Strings.isBlank(disciplina.getNome())){
            throw new RuntimeException("Favor informar o nome da disciplina:");
        }
        disciplinaRepository.save(disciplina);
    }
}
