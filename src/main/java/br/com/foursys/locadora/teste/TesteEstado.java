package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.controller.EstadoController;

public class TesteEstado {
	
	public void salvar() {
		System.out.println("Ok");
		System.exit(0);
	}
	
	public void buscarTodos() {
		for (Estado estado : new EstadoController().buscarTodos()) {
			System.out.println("Código: " + estado.getCodigo());
			System.out.println("Nome: " + estado.getNome());
			System.out.println("UF: " + estado.getUf());
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		new TesteEstado().buscarTodos();
	}

}
