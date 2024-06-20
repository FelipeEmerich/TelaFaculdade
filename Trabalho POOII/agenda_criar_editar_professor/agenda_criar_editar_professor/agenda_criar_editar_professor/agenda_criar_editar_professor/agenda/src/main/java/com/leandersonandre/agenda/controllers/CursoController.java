package com.leandersonandre.agenda.controllers;

import com.leandersonandre.agenda.core.entity.Curso;
import com.leandersonandre.agenda.core.service.CursoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoServico cursoServico;

   @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("curso/index.html");
        view.addObject("objeto","olá thymeleaf");
        view.addObject("cursos",cursoServico.obterTodos());
        return view;
    }

	@GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("curso/visualizar.html");
        var opt = cursoServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("curso/editar.html");
        var opt = cursoServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }


	@GetMapping("/criar")
    public ModelAndView criarNovoCurso(){
        ModelAndView view = new ModelAndView("curso/criar.html");
        view.addObject("entidade", new Curso());
        return view;
    }

	 @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Curso curso){
        try {
            cursoServico.salvar(curso);
            return new ModelAndView("redirect:/curso/"+curso.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("curso/editar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", curso);
            return model;
        }
    }

	@PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute("entidade")Curso curso){
        try {
            System.out.println(curso);
            curso.setId(0);
            cursoServico.salvar(curso);
            return new ModelAndView("redirect:/curso/"+curso.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("curso/criar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", curso);
            return model;
        }
    }

}
