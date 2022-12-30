package br.com.tadeu.agenda.de.clientes.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;
import br.com.tadeu.agenda.de.clientes.spring.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("clientes/lista");
		
		List<Cliente> clientes = this.clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		System.out.println(clientes);
		
		return mv;
	}
}
