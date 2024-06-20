package com.leandersonandre.agenda.controllers;

import com.leandersonandre.agenda.core.entity.Turma;
import com.leandersonandre.agenda.core.service.TurmaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    TurmaServico turmaServico;

   @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("turma/index.html");
        view.addObject("objeto","olá thymeleaf");
        view.addObject("turma",turmaServico.obterTodos());
        return view;
    }

	@GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("turma/visualizar.html");
        var opt = turmaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("turma/editar.html");
        var opt = turmaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }


	@GetMapping("/criar")
    public ModelAndView criarNovoTurma(){
        ModelAndView view = new ModelAndView("turma/criar.html");
        view.addObject("entidade", new Turma());
        return view;
    }

	 @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Turma turma){
        try {
            turmaServico.salvar(turma);
            return new ModelAndView("redirect:/turma/"+turma.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("turma/editar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", turma);
            return model;
        }
    }

	@PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute("entidade")Turma turma){
        try {
            System.out.println(turma);
            turma.setId(0);
            turmaServico.salvar(turma);
            return new ModelAndView("redirect:/turma/"+turma.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("turma/criar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", turma);
            return model;
        }
    }

}
