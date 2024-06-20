package com.leandersonandre.agenda.controllers;

import com.leandersonandre.agenda.core.entity.Professor;
import com.leandersonandre.agenda.core.service.ProfessorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaServico disciplinaServico;


	@GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("disciplina/index.html");
        view.addObject("objeto","olá thymeleaf");
        view.addObject("disciplinas",disciplinaServico.obterTodos());
        return view;
    }


	@GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("disciplina/visualizar.html");
        var opt = disciplinaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("disciplina/editar.html");
        var opt = disciplinaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

 	@GetMapping("/criar")
    public ModelAndView criarNovoDisciplina(){
        ModelAndView view = new ModelAndView("disciplina/criar.html");
        view.addObject("entidade", new Disciplina());
        return view;
    }

	@PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Disciplina disciplina){
        try {
            disciplinaServico.salvar(disciplina);
            return new ModelAndView("redirect:/disciplina/"+disciplina.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("disciplina/editar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", disciplina);
            return model;
        }
    }

	@PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute("entidade")Disciplina disciplina){
        try {
            System.out.println(disciplina);
            disciplina.setId(0);
            disciplinaServico.salvar(disciplina);
            return new ModelAndView("redirect:/disciplina/"+disciplina.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("disciplina/criar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", disciplina);
            return model;
        }
    }

}















    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disciplina createDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplinaDetails) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);

        if (!disciplinaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Disciplina disciplina = disciplinaOptional.get();
        disciplina.setNome(disciplinaDetails.getNome());
        disciplina.setProfessor(disciplinaDetails.getProfessor());

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);

        if (!disciplinaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        disciplinaRepository.delete(disciplinaOptional.get());
        return ResponseEntity.noContent().build();
    }
}
