package br.com.foursys.locadora.teste;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.dao.FilmeDAO;

public class TesteContato {
	
	public void salvar() {
		Contato contato =  new Contato();
		contato.setCelular("(11)98765-7654");
		contato.setTelefone("(11)4261-7827");
		contato.setEmail("contato@gmail.com");
		new ContatoController().salvar(contato);
		System.out.println("Ok");
		System.exit(0);
	}
	
	public void buscarTodos() {
		for (Contato contato : new ContatoController().buscarTodos()) {
			System.out.println("Código: " + contato.getCodigo());
			System.out.println("Celular: " + contato.getCelular());
			System.out.println("Telefone: " + contato.getTelefone());
			System.out.println("E-mail: " + contato.getEmail());
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		new TesteContato().buscarTodos();
	}

}
