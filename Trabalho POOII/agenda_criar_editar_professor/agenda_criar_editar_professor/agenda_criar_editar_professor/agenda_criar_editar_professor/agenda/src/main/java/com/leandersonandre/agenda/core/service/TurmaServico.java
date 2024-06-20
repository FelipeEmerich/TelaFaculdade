package com.leandersonandre.agenda.core.service;

import com.leandersonandre.agenda.core.entity.Turma;
import com.leandersonandre.agenda.core.repository.TurmaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaServico {

    @Autowired
    TurmaRepository turmaRepository;

    public List<Turma> obterTodos() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> obterPeloId(long id) {
        return turmaRepository.findById(id);
    }

    public void salvar(Turma turma) {
        if(Strings.isBlank(turma.getCodigo())){
            throw new RuntimeException("Favor informar o codigo da turma:");
        }
  
        turmaRepository.save(turma);
    }
}
