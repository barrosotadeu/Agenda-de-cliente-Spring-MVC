package br.com.tadeu.agenda.de.clientes.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tadeu.agenda.de.clientes.spring.dto.RequisicaoCliente;
import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;
import br.com.tadeu.agenda.de.clientes.spring.repository.ClienteRepository;
import jakarta.validation.Valid;

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
	
	@GetMapping("/clientes/novo")
	public String novo(RequisicaoCliente requisicao) {
		
		return "clientes/novo";
	}
	
	@PostMapping("/clientes")
	public ModelAndView cadastra(@Valid RequisicaoCliente requisicao, BindingResult bindingResult) {
		System.out.println(requisicao);
		
		if(bindingResult.hasErrors()) {
			
			System.out.println("**********TEM ERRO*********");
			return new ModelAndView("clientes/novo");
		}
		ModelAndView mv = new ModelAndView("clientes/lista");
		
		Cliente cliente = new Cliente();
		requisicao.transformaRequisicaoEmCliente(cliente);
		
		this.clienteRepository.save(cliente);
		
		List<Cliente> clientes = this.clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
}
