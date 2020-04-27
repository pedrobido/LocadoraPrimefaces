package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.dao.LocacaoDAO;

public class LocacaoController {

	public void salvar(Locacao locacao) {
		LocacaoDAO dao = new LocacaoDAO();
		try {
			dao.salvar(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Locacao locacao) {
		LocacaoDAO dao = new LocacaoDAO();
		try {
			dao.excluir(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Locacao> buscarTodos() {
		ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
		LocacaoDAO dao = new LocacaoDAO();

		try {
			listaRetorno = dao.buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public ArrayList<Locacao> buscarNaoDevolvidos() {
		ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
		LocacaoDAO dao = new LocacaoDAO();

		try {
			listaRetorno = dao.buscarNaoDevolvidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public ArrayList<Locacao> buscarNome(String nome) {
		ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
		LocacaoDAO dao = new LocacaoDAO();

		try {
			listaRetorno = dao.buscarNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public ArrayList<Locacao> carregarListaLocacoes(String cliente) {
		ArrayList<Locacao> listaLocacoes = new ArrayList<Locacao>();
		for (Locacao locacao : new LocacaoController().buscarTodos()) {
			if (locacao.getClienteCodigo().getCodigo() == Integer.parseInt(cliente)) {
				if (locacao.getDevolvido().equals("NAO")) {
					listaLocacoes.add(locacao);
				}
			}

		}
		return listaLocacoes;
	}

}
