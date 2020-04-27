package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Genero;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.GeneroController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Rotulo;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "filmeBacking")
@SessionScoped
public class FilmeBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Filme> listaFilmes;
	private String nome, valor, valorPromocao, promocao, disponivel;
	private String pesquisaNome = "";
	private String genero;
	private List<Genero> listaGenero = new ArrayList<Genero>();
	private boolean alterar = false;
	private Filme filmeUpdate;
//	private NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();

	// Getters e Setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Genero> getListaGenero() {
		return listaGenero;
	}

	public void setListaGenero(List<Genero> listaGenero) {
		this.listaGenero = listaGenero;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getPesquisaNome() {
		return pesquisaNome;
	}

	public void setPesquisaNome(String pesquisaNome) {
		this.pesquisaNome = pesquisaNome;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public String getValorPromocao() {
		return valorPromocao;
	}

	public void setValorPromocao(String valorPromocao) {
		this.valorPromocao = valorPromocao;
	}

	// Fim dos Getters e Setters

	public Filme getFilmeUpdate() {
		return filmeUpdate;
	}

	public void setFilmeUpdate(Filme filmeUpdate) {
		this.filmeUpdate = filmeUpdate;
	}

	public void init() {
		listaGenero = carregarListaGenero();
	}

	public ArrayList<Filme> preencherTabela() {
		return new FilmeController().buscarTodos();
	}

	public void pesquisar() {
		listaFilmes = pesquisarFilme();
	}

	public ArrayList<Filme> pesquisarFilme() {
		return new FilmeController().buscarNome(pesquisaNome);
	}

	public ArrayList<Genero> carregarListaGenero() {
		ArrayList<Genero> lista = new GeneroController().buscarTodos();
		return lista;
	}

	public String salvar() {
		if (!alterar) {
			if (validarIncluir()) {
				Filme filme = new Filme();
				filme.setNome(nome);
				filme.setGeneroCodigo(new Genero(Integer.parseInt(genero)));
				filme.setValor(Double.parseDouble(valor));
				filme.setDisponivel(disponivel);
				filme.setPromocao(promocao);
				filme.setValorPromocao(Double.parseDouble(valorPromocao));
				try {
					new FilmeController().salvar(filme);
					limparCampos();
					JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.filmeSalvo);
				} catch (Exception e) {
					JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroSalvarFilme);
				}
			}
		} else {
			if (validarAlterar()) {
				alterar = false;
				try {
					new FilmeController().salvar(filmeUpdate);
					JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.filmeAlterado);
					filmeUpdate = null;
				} catch (Exception e) {
					JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroAlterarFilme);
				}
				return "consultarFilme";
			}
		}
		return "";
	}

	public void limparCampos() {
		nome = null;
		genero = null;
		valor = null;
		disponivel = null;
		promocao = null;
		valorPromocao = null;
	}

	public String sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			return "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public boolean validarIncluir() {
		if (Valida.verificaVazio(nome)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeNome);
			return false;
		} else if (Valida.verificaVazio(genero)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeGenero);
			return false;
		} else if (Valida.verificaVazio(valor)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeValor);
			return false;
		} else if (Valida.verificaVazio(disponivel)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeDisponivel);
			return false;
		} else if (Valida.verificaVazio(promocao)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informePromocao);
			return false;
		} else if (promocao.equals("SIM")) {
			if (Valida.verificaVazio(valorPromocao)) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio,
						Mensagem.informeValorPromocao);
				return false;
			}
		} else {
			valorPromocao = "0.0";
		}
		return true;
	}

	public boolean validarAlterar() {
		if (Valida.verificaVazio(filmeUpdate.getValor() + "")) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeValor);
			return false;
		} else if (Valida.verificaVazio(filmeUpdate.getDisponivel())) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeDisponivel);
			return false;
		} else if (Valida.verificaVazio(filmeUpdate.getPromocao())) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informePromocao);
			return false;
		} else if (filmeUpdate.getPromocao().equals("SIM")) {
			if (Valida.verificaVazio(filmeUpdate.getValorPromocao() + "")) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio,
						Mensagem.informeValorPromocao);
				return false;
			}
		} else {
			valorPromocao = "0.0";
		}
		return true;
	}

	public String alterar() {
		alterar = true;
		return "alterarFilme";
	}

	public String excluir() {
		listaFilmes.remove(filmeUpdate);
		new FilmeController().excluir(filmeUpdate);
		listaFilmes = new FilmeController().buscarTodos();
		for (Filme filme : listaFilmes) {
			if (filme.getCodigo().equals(filmeUpdate.getCodigo())) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroExcluirFilme);
				return "";
			}
		}
		JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.filmeExcluidoSucesso);
		filmeUpdate = null;
		return "";
	}

}
