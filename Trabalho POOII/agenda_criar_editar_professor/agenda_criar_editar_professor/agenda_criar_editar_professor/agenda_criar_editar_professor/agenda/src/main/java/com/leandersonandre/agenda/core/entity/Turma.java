package com.leandersonandre.agenda.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codigo;
    private String professor;
    private String disciplina;
    private String horario;

    public Turma(String professor, String disciplina) {
        this.professor = professor;
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        if (this.professor == null) return "Sem aula";
        return "Disciplina : " + this.disciplina + "| Professor: " + this.professor;
    }
}
