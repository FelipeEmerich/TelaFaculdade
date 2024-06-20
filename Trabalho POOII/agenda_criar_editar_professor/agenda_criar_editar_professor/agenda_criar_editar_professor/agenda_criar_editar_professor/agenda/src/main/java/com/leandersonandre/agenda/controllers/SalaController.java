package com.leandersonandre.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.leandersonandre.agenda.core.entity.Sala;
import com.leandersonandre.agenda.core.service.SalaServico;


@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    SalaServico salaServico;

   @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("sala/index.html");
        view.addObject("objeto","olá thymeleaf");
        view.addObject("sala",salaServico.obterTodos());
        return view;
    }

	@GetMapping("/{id}")
    public ModelAndView visualizar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("sala/visualizar.html");
        var opt = salaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }

	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("sala/editar.html");
        var opt = salaServico.obterPeloId(id);
        opt.ifPresent(entidade -> view.addObject("entidade", entidade));
        return view;
    }


	@GetMapping("/criar")
    public ModelAndView criarNovoSala(){
        ModelAndView view = new ModelAndView("sala/criar.html");
        view.addObject("entidade", new Sala());
        return view;
    }

	 @PostMapping("/atualizar")
    public ModelAndView salvar(@ModelAttribute("entidade")Sala sala){
        try {
            salaServico.salvar(sala);
            return new ModelAndView("redirect:/sala/"+sala.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("sala/editar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", sala);
            return model;
        }
    }

	@PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute("entidade")Sala sala){
        try {
            System.out.println(sala);
            sala.setId(0);
            salaServico.salvar(sala);
            return new ModelAndView("redirect:/sala/"+sala.getId());
        }catch (Exception e){
            ModelAndView model = new ModelAndView("sala/criar.html");
            model.addObject("erro",e.getMessage());
            model.addObject("entidade", sala);
            return model;
        }
    }

}
