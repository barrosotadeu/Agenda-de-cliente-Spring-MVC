package br.com.tadeu.agenda.de.clientes.spring.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tadeu.agenda.de.clientes.spring.dto.RequisicaoCliente;
import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;
import br.com.tadeu.agenda.de.clientes.spring.model.Endereco;
import br.com.tadeu.agenda.de.clientes.spring.repository.ClienteRepository;
import br.com.tadeu.agenda.de.clientes.spring.repository.EnderecoRepository;
import jakarta.validation.Valid;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
	public ModelAndView cadastra(@Valid RequisicaoCliente requisicao, BindingResult bindingResult) throws Exception {
		System.out.println(requisicao);
		
		if(bindingResult.hasErrors()) {
			
			System.out.println("**********TEM ERRO*********");
			return new ModelAndView("clientes/novo");
		}
		
		//Consumindo uma api pública externa
		URL url = new URL("https://viacep.com.br/ws/"+ requisicao.getCep() + "/json/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		while((cep = br.readLine())!= null) {
			jsonCep.append(cep);
		}
		
		Endereco endereco = new Gson().fromJson(jsonCep.toString(), Endereco.class); 
		this.enderecoRepository.save(endereco);
		
		System.out.println(jsonCep.toString());
		
		
		ModelAndView mv = new ModelAndView("clientes/lista");
		
		Cliente cliente = new Cliente();
		requisicao.transformaRequisicaoEmCliente(cliente);
		cliente.setEndereco(endereco);
		
		
		this.clienteRepository.save(cliente);
		
		List<Cliente> clientes = this.clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@GetMapping("/clientes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		
		
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		
		if(optional.isPresent()) {
			Cliente cliente = optional.get();
			ModelAndView mv = new ModelAndView("clientes/detalhes");
			mv.addObject("cliente", cliente);
			 return mv;
		}
	
	
		
		return new ModelAndView("redirect:/clientes");
	}
	
	@GetMapping("/clientes/{id}/delete")	
	public ModelAndView delete(@PathVariable Long id) {
		this.clienteRepository.deleteById(id);
		
		return new ModelAndView("redirect:/clientes");
	}
	
	
	@GetMapping("/clientes/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoCliente requisicao) {
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		
		if(optional.isPresent()) {
			Cliente cliente = optional.get();
			requisicao.transformaClienteEmRequisicao(cliente);
			ModelAndView mv = new ModelAndView("clientes/edit");
			mv.addObject("clienteId", cliente.getId());
			return mv;
		}
		else {
			return new ModelAndView("redirect:/clientes");
		}
		
		

	}
	
	@PostMapping("/clientes/{id}")
	public ModelAndView atualiza(@PathVariable Long id, @Valid RequisicaoCliente requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {	
			System.out.println("******erro*******");
			ModelAndView mv = new ModelAndView("clientes/edit");
			mv.addObject("clienteId", id);
			
			return mv;
		}
		else {
			Optional<Cliente> optional = this.clienteRepository.findById(id);
			
			if(optional.isPresent()) {
				Cliente cliente = optional.get();
				requisicao.transformaRequisicaoEmCliente(cliente);
				this.clienteRepository.save(cliente);
				return new ModelAndView("redirect:/clientes/" + cliente.getId());
			}
			else {
				return new ModelAndView("redirect:/clientes");
			}
		}
	}
}
