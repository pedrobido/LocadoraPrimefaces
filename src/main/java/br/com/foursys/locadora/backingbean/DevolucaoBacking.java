package br.com.foursys.locadora.backingbean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Rotulo;

@ManagedBean(name = "devolucaoBacking")
@SessionScoped
public class DevolucaoBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cliente, locacao;
	private ArrayList<Locacao> listaLocacoes;
	private ArrayList<Filme> listaFilmesLocacao = new ArrayList<Filme>();
	private ArrayList<Cliente> listaClientes;
	private ArrayList<LocacaoFilme> listaLocacaoFilme;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}

	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}

	public String getLocacao() {
		return locacao;
	}

	public void setLocacao(String locacao) {
		this.locacao = locacao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<LocacaoFilme> getListaLocacaoFilme() {
		return listaLocacaoFilme;
	}

	public void setListaLocacaoFilme(ArrayList<LocacaoFilme> listaLocacaoFilme) {
		this.listaLocacaoFilme = listaLocacaoFilme;
	}

	public void carregarListaLocacoes() {
		listaLocacoes = new LocacaoController().carregarListaLocacoes(cliente);
	}

	public ArrayList<Cliente> carregarListaClientes() {
		return new ClienteController().buscarTodos();
	}

	public void carregarFilmesLocacao() {
		listaFilmesLocacao = new ArrayList<Filme>();
		listaLocacaoFilme = new LocacaoFilmeController().buscarTodos();
		for (LocacaoFilme locacaoFilme : listaLocacaoFilme) {
			if (locacao.equals(locacaoFilme.getLocacaoCodigo().getCodigo() + "")) {
				listaFilmesLocacao.add(locacaoFilme.getFilmeCodigo());
			}
		}
	}

	public void limparCampos() {
		cliente = null;
		locacao = null;
		listaFilmesLocacao = new ArrayList<Filme>();
	}

	public String sair() {
		return "index.xhtml";
	}

	public void salvar() {
		if (validarDados()) {
			try {
				for (Locacao locacao : listaLocacoes) {
					if (this.locacao.equals(locacao.getCodigo() + "")) {
						locacao.setDataDevolucao(dtf.format(LocalDateTime.now()).toString());
						locacao.setDevolvido("SIM");
						for (Filme filme : listaFilmesLocacao) {
							filme.setDisponivel("SIM");
							new FilmeController().salvar(filme);
						}
						new LocacaoController().salvar(locacao);
					}
				}
				JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.devolucaoSalva);
				limparCampos();
			} catch (Exception e) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroSalvarDevolucao);
			}
		}
	}

	private boolean validarDados() {
		return true;
	}

	@PostConstruct
	public void init() {
		listaClientes = carregarListaClientes();
	}

}
