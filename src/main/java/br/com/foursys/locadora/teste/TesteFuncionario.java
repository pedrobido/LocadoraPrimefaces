package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.FuncionarioController;

public class TesteFuncionario {

	public void salvar() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Func");
		funcionario.setCpf("424.654.212-54");
		funcionario.setDataNascimento("04/06/2000");
		funcionario.setIdade(20);
		funcionario.setRg("25.545.784-6");
		funcionario.setSexo("M");
		funcionario.setLogin("spkdskpedro");
		funcionario.setSenha("abcde");
		funcionario.setEnderecoCodigo(new Endereco(1));
		funcionario.setContatoCodigo(new Contato(1));
		new FuncionarioController().salvar(funcionario);
		System.out.println("ok");
		System.exit(0);
	}

	public void buscarTodos() {
		for (Funcionario c : new FuncionarioController().buscarTodos()) {
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
			System.out.println("Login: " + c.getLogin());
			System.out.println("Senha: " + c.getSenha());
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new TesteFuncionario().buscarTodos();
	}

}
