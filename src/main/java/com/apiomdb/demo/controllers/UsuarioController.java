package com.apiomdb.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apiomdb.demo.component.PeliculaComp;
import com.apiomdb.demo.component.UsuarioComp;
import com.apiomdb.demo.models.entity.Usuario;
import com.apiomdb.demo.service.IUsuarioService;

@Controller
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	Usuario usuario;
	
	@Autowired
	private IUsuarioService usuService;
	
	@Autowired 
	private PeliculaComp peli;
	
	@Autowired 
	private UsuarioComp usu;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		Usuario usu= new Usuario();
		model.addAttribute("usu", usu);
		boolean correcto=false;
		model.addAttribute("correcto",correcto);
		return "login";
	}
	
	@RequestMapping(value = "/logueado", method = RequestMethod.GET)
	public String loguear(Usuario usuario,Model model) {
		boolean correcto=false;
		List<Usuario> usuarios = usuService.findAll();
		for (Usuario u : usuarios) {
			if(u.getMail().equals(usuario.getMail()) && u.getPass().equals(usuario.getPass())) {
				usu.copia(u);
				correcto=true;
				break;
			}
		}
		if(correcto) {
			//model.addAttribute("usuario", usu);
			//model.addAttribute("pelicula", peli);
			return "redirect:/peli/muroActividad";
		}else {
			Usuario usuNuevo = new Usuario();
			model.addAttribute("usu", usuNuevo);
			correcto=true;
			model.addAttribute("correcto",correcto);
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(Model model) {
		Usuario usu= new Usuario();
		model.addAttribute("usu", usu);
		return "registro";
	}
	
	@RequestMapping(value = "/registrado", method = RequestMethod.GET)
	public String registrado(Usuario usu, Model model) {	
		usuService.save(usu);
		return "redirect:login";
	}
	
}
