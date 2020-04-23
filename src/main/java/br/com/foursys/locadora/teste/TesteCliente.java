package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.EnderecoController;

public class TesteCliente {

	public void salvar() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste");
		cliente.setCpf("457.854.754-74");
		cliente.setDataNascimento("04/06/2000");
		cliente.setIdade(21);
		cliente.setRg("25.545.784-6");
		cliente.setSexo("M");
		cliente.setEnderecoCodigo(new Endereco(1));
		cliente.setContatoCodigo(new Contato(1));
		new ClienteController().salvar(cliente);
		System.out.println("ok");
		System.exit(0);
	}

	public void buscarTodos() {
		for (Cliente c : new ClienteController().buscarTodos()) {
			System.out.println("Código: " + c.getCodigo());
			System.out.println("Nome: " + c.getNome());
			System.out.println("CPF: " + c.getCpf());
			System.out.println("RG: " + c.getRg());
			System.out.println("Data de Nascimento: " + c.getDataNascimento());
			System.out.println("Idade: " + c.getIdade());
			System.out.println("Sexo: " + c.getSexo());
			System.out.println(
					"Endereço: " + c.getEnderecoCodigo().getLogradouro() + ", " + c.getEnderecoCodigo().getNumero());
			System.out.println("Cidade: " + c.getEnderecoCodigo().getCidadeCodigo().getNome() + " - "
					+ c.getEnderecoCodigo().getCidadeCodigo().getEstadoCodigo().getUf());
			System.out.println("Celular: " + c.getContatoCodigo().getCelular());
			System.out.println("Telefone: " + c.getContatoCodigo().getTelefone());
			System.out.println("Email: " + c.getContatoCodigo().getEmail());
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new TesteCliente().buscarTodos();
	}

}
