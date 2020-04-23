package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.controller.LocacaoController;

public class TesteLocacao {
	
	public void salvar() {
		Locacao locacao = new Locacao();
		locacao.setClienteCodigo(new Cliente(1));
		locacao.setFuncionarioCodigo(new Funcionario(1));
		locacao.setDataLocacao("16/04/2020");
		locacao.setDataDevolucao("20/04/2020");
		locacao.setFormaPagamentoCodigo(new FormaPagamento(2));
		locacao.setValor(10.30);
		new LocacaoController().salvar(locacao);
		System.out.println("ok");
		System.exit(0);
	}

	public void buscarTodos() {
		for (Locacao locacao : new LocacaoController().buscarTodos()) {
			System.out.println("Código: " + locacao.getCodigo());
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		new TesteLocacao().salvar();
	}

}
