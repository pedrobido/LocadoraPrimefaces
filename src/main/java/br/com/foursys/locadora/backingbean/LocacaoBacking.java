package br.com.foursys.locadora.backingbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Rotulo;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "locacaoBacking")
@SessionScoped
public class LocacaoBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cliente, funcionario, dataLocacao, valor, formaPagamento, locacao;
	private Date dataDevolucao;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<String> listaIndexFilmesSelecionados = new ArrayList<String>();
	private ArrayList<FormaPagamento> listaFormaPagamento;
	private ArrayList<Funcionario> listaFuncionarios;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();

	// INICIO DOS GETTERS E SETTERS

	public String getLocacao() {
		return locacao;
	}

	public void setLocacao(String locacao) {
		this.locacao = locacao;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public String getCliente() {
		return cliente;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public ArrayList<String> getListaIndexFilmesSelecionados() {
		return listaIndexFilmesSelecionados;
	}

	public void setListaIndexFilmesSelecionados(ArrayList<String> listaIndexFilmesSelecionados) {
		this.listaIndexFilmesSelecionados = listaIndexFilmesSelecionados;
	}

	// FIM DOS GETTERS E SETTERS

	public void init() {
		listaFilmes = carregarListaFilmes();
		listaClientes = carregarListaClientes();
		listaFormaPagamento = carregarListaFormaPagamento();
		listaFuncionarios = carregarListaFuncionarios();
		dataLocacao = dtf.format(LocalDateTime.now()).toString();
	}

	public ArrayList<Filme> carregarListaFilmes() {
		return new FilmeController().buscarDisponiveis();
	}

	public ArrayList<Cliente> carregarListaClientes() {
		return new ClienteController().buscarTodos();
	}

	public ArrayList<FormaPagamento> carregarListaFormaPagamento() {
		return new FormaPagamentoController().buscarTodos();
	}

	public ArrayList<Funcionario> carregarListaFuncionarios() {
		return new FuncionarioController().buscarTodos();
	}
	
	public ArrayList<Locacao> carregarListaLocacoes() {
		return new LocacaoController().buscarNaoDevolvidos();
	}

	public void salvar() {
		if (validarDados()) {
			Locacao locacao = new Locacao();
			locacao.setClienteCodigo(new Cliente(Integer.parseInt(cliente)));
			locacao.setFuncionarioCodigo(new Funcionario(Integer.parseInt(funcionario)));
			locacao.setDataLocacao(dataLocacao);
			locacao.setDataDevolucao(formatarData(dataDevolucao));
			locacao.setFormaPagamentoCodigo(new FormaPagamento(Integer.parseInt(formaPagamento)));
			locacao.setValor(Double.parseDouble(valor.replace("R$", "").replace(",", ".")));
			locacao.setDevolvido("NAO");
			try {
				new LocacaoController().salvar(locacao);
				for (String string : listaIndexFilmesSelecionados) {
					for (Filme filme : listaFilmes) {
						if (string.equals(filme.getCodigo() + "")) {
							filme.setDisponivel("NAO");
							new FilmeController().salvar(filme);
							LocacaoFilme locacaoFilme = new LocacaoFilme();
							locacaoFilme.setFilmeCodigo(filme);
							locacaoFilme.setLocacaoCodigo(locacao);
							new LocacaoFilmeController().salvar(locacaoFilme);
						}
					}
				}
				JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.locacaoSalva);
				limparCampos();
			} catch (Exception e) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroSalvarLocacao);
			}
		}
	}

	public void limparCampos() {
		cliente = null;
		funcionario = null;
		dataDevolucao = null;
		valor = null;
		formaPagamento = null;
		listaIndexFilmesSelecionados = new ArrayList<String>();
		init();
	}

	public String sair() {
		return "index.xhtml";
	}

	public String formatarData(Date data) {
		if (data == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(data);
		return dataFormatada;
	}

	public void atualizarValorTotal() {
		valor = "0";
		double valorTotal = 0.0;
		for (String string : listaIndexFilmesSelecionados) {
			for (Filme filme : listaFilmes) {
				if (string.equals(filme.getCodigo() + "")) {
					if (filme.getPromocao().equals("SIM")) {
						valorTotal = valorTotal + filme.getValorPromocao();
					} else {
						valorTotal = valorTotal + filme.getValor();
					}
				}
			}
		}
		valor = formatoMoeda.format(valorTotal);
	}

	public boolean validarDados() {

		if (Valida.verificaVazio(funcionario)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeFuncionario);
			return false;
		} else if (Valida.verificaVazio(cliente)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeCliente);
			return false;
		} else if (Valida.validaComboVazia(listaIndexFilmesSelecionados)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeFilme);
			return false;
		} else if (Valida.verificaVazio(formaPagamento)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio,
					Mensagem.informeFormaPagamento);
			return false;
		} else if (Valida.verificaVazio(formatarData(dataDevolucao))) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio,
					Mensagem.informeDataDevolucao);
			return false;
		} else if (Valida.dataMenorQueHoje(dataDevolucao)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.dadosInvalidos,
					Mensagem.dataDevolucaoInvalida);
			return false;
		}
		return true;
	}
}
