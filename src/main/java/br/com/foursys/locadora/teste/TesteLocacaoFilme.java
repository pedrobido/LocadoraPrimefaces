package br.com.foursys.locadora.teste;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.LocacaoFilmeController;

public class TesteLocacaoFilme {
	
	public void salvar() {
		LocacaoFilme locacaoFilme = new LocacaoFilme();
		locacaoFilme.setFilmeCodigo(new Filme(1));
		locacaoFilme.setLocacaoCodigo(new Locacao(1));
		
		LocacaoFilme locacaoFilme2 = new LocacaoFilme();
		locacaoFilme2.setFilmeCodigo(new Filme(2));
		locacaoFilme2.setLocacaoCodigo(new Locacao(1));
		
		LocacaoFilme locacaoFilme3 = new LocacaoFilme();
		locacaoFilme3.setFilmeCodigo(new Filme(3));
		locacaoFilme3.setLocacaoCodigo(new Locacao(1));
		
		new LocacaoFilmeController().salvar(locacaoFilme);
		new LocacaoFilmeController().salvar(locacaoFilme2);
		new LocacaoFilmeController().salvar(locacaoFilme3);
		System.out.println("ok");
		System.exit(0);
	}

	public void buscarTodos() {
		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarTodos()) {
			System.out.println("Código: " + locacaoFilme.getCodigo());
			System.out.println("Nome do filme: " + locacaoFilme.getFilmeCodigo().getNome());
			System.out.println("Gênero: " + locacaoFilme.getFilmeCodigo().getGeneroCodigo().getDescricao());
			System.out.println("Forma de pagamento: " + locacaoFilme.getLocacaoCodigo().getFormaPagamentoCodigo().getDescricao());
			System.out.println("Nome do funcionário: " + locacaoFilme.getLocacaoCodigo().getFuncionarioCodigo().getNome());
			System.out.println("Nome do cliente: " + locacaoFilme.getLocacaoCodigo().getClienteCodigo().getNome());
			System.out.println("Data de locação: " + locacaoFilme.getLocacaoCodigo().getDataLocacao());
			System.out.println("Cidade do cliente: " + locacaoFilme.getLocacaoCodigo().getClienteCodigo().getEnderecoCodigo().getCidadeCodigo().getNome());
			System.out.println("Telefone do cliente: " + locacaoFilme.getLocacaoCodigo().getClienteCodigo().getContatoCodigo().getTelefone());
			System.out.println("Celular do cliente: " + locacaoFilme.getLocacaoCodigo().getClienteCodigo().getContatoCodigo().getCelular());
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		new TesteLocacaoFilme().buscarTodos();
	}

}
