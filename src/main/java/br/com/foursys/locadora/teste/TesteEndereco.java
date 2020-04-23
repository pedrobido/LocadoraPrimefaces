package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;

public class TesteEndereco {

	public void salvar() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua de teste");
		endereco.setNumero(14);
		endereco.setBairro("Jd. do Teste");
		endereco.setCep("06.532-023");
		endereco.setCidadeCodigo(new Cidade(1));
		new EnderecoController().salvar(endereco);
		System.out.println("ok");
		System.exit(0);
	}
	
	public void buscarTodos() {
		for (Endereco e : new EnderecoController().buscarTodos()) {
			System.out.println("Logradouro: " + e.getLogradouro());
			System.out.println("Número: " + e.getNumero());
			System.out.println("Complemento: " + e.getComplemento());
			System.out.println("Bairro: " + e.getBairro());
			System.out.println("CEP: " + e.getCep());
			System.out.println("Cidade: " + e.getCidadeCodigo().getNome());
			System.out.println("Estado: " + e.getCidadeCodigo().getEstadoCodigo().getUf());
		}
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new TesteEndereco().buscarTodos();
	}

}
