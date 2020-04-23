package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Rotulo;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String cpf;
	private Date dataNascimento;
	private String idade;
	private String nome;
	private String rg;
	private String sexo;
	private String contato;
	private String endereco;
	private String celular;
	private String email;
	private String telefone;
	private String bairro;
	private String cep;
	private String complemento;
	private String logradouro;
	private String numero;
	private String cidade;
	private String estado;
	private String login;
	private String senha;
	private String pesquisaNome = "";
	private ArrayList<Funcionario> listaFuncionarios;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<Estado> listaEstados;
	private boolean alterar;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getPesquisaNome() {
		return pesquisaNome;
	}

	public void setPesquisaNome(String pesquisaNome) {
		this.pesquisaNome = pesquisaNome;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public ArrayList<Funcionario> pesquisarFuncionario() {
		return new FuncionarioController().buscarNome(pesquisaNome);
	}

	@PostConstruct
	public void init() {
		listaEstados = carregarListaEstados();
	}

	public ArrayList<Filme> preencherTabela() {
		return new FilmeController().buscarTodos();
	}

	public void pesquisar() {
		listaFuncionarios = pesquisarFuncionarios();
	}

	public ArrayList<Funcionario> pesquisarFuncionarios() {
		return new FuncionarioController().buscarNome(pesquisaNome);
	}

	public void carregarListaCidades() {
		listaCidades = new CidadeController().carregarListaCidades(estado);
	}

	public ArrayList<Estado> carregarListaEstados() {
		return new EstadoController().buscarTodos();
	}

	public String salvar() {
		if (validarCampos()) {
			if (!alterar) {
				Funcionario funcionario = new Funcionario();
				Contato contato = new Contato();
				Endereco endereco = new Endereco();
				funcionario.setCpf(cpf);
				funcionario.setDataNascimento(formatarData(dataNascimento));
				funcionario.setIdade(Integer.parseInt(idade));
				funcionario.setNome(nome);
				funcionario.setRg(rg);
				funcionario.setSexo(sexo);
				funcionario.setLogin(login);
				funcionario.setSenha(senha);
				contato.setTelefone(telefone);
				contato.setCelular(celular);
				contato.setEmail(email);
				endereco.setLogradouro(logradouro);
				endereco.setNumero(Integer.parseInt(numero));
				endereco.setComplemento(complemento);
				endereco.setBairro(bairro);
				endereco.setCep(cep);
				endereco.setCidadeCodigo(new Cidade(Integer.parseInt(cidade)));
				funcionario.setContatoCodigo(contato);
				funcionario.setEnderecoCodigo(endereco);
				try {
					new ContatoController().salvar(contato);
					new EnderecoController().salvar(endereco);
					new FuncionarioController().salvar(funcionario);
					limparCampos();
					JSFUtil.addInfoMessage(Rotulo.INFO.getDescricao(), Mensagem.sucesso, Mensagem.funcionarioSalvo);
				} catch (Exception e) {
					JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.erro, Mensagem.erroSalvarFuncionario);
				}
			} else {
				System.out.println("Alterar ainda não foi implementado.");
			}
		}
		return "";
	}

	public void limparCampos() {
		codigo = null;
		cpf = null;
		dataNascimento = null;
		idade = null;
		nome = null;
		rg = null;
		sexo = null;
		contato = null;
		endereco = null;
		celular = null;
		email = null;
		telefone = null;
		bairro = null;
		cep = null;
		complemento = null;
		logradouro = null;
		numero = null;
		cidade = null;
		estado = null;
		login = null;
		senha = null;
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

	public void calculaIdade() {
		try {
			String[] datas = formatarData(dataNascimento).split("/");
			idade = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(datas[2])) + "";
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public String formatarData(Date data) {
		if (data == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(data);
		return dataFormatada;
	}

	public boolean validarCampos() {
		if (Valida.verificaVazio(nome)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeNome);
			return false;
		} else if (Valida.verificaVazio(cpf)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeCpf);
			return false;
		} else if (!Valida.validarCpf(cpf)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.dadosInvalidos, Mensagem.cpfInvalido);
			return false;
		} else if (Valida.verificaVazio(rg)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeRg);
			return false;
		} else if (!Valida.validarRg(rg)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.dadosInvalidos, Mensagem.rgInvalido);
			return false;
		} else if (Valida.verificaVazio(formatarData(dataNascimento))) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio,
					Mensagem.informeDataNascimento);
			return false;
		} else if (!Valida.validarData(formatarData(dataNascimento))) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.dadosInvalidos,
					Mensagem.dataNascimentoInvalida);
			return false;
		} else if (Valida.verificaVazio(idade)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeIdade);
			return false;
		} else if (Valida.verificaVazio(sexo)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeSexo);
			return false;
		} else if (Valida.verificaVazio(logradouro)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeLogradouro);
			return false;
		} else if (Valida.verificaVazio(numero)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeNumero);
			return false;
		} else if (Valida.verificaVazio(bairro)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeBairro);
			return false;
		} else if (Valida.verificaVazio(cep)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeCep);
			return false;
		} else if (Valida.verificaVazio(estado)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeEstado);
			return false;
		} else if (Valida.verificaVazio(cidade)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeCidade);
			return false;
		} else if (Valida.verificaVazio(login)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeLogin);
			return false;
		} else if (Valida.verificaVazio(senha)) {
			JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.campoObrigatorio, Mensagem.informeSenha);
			return false;
		} else if (!Valida.verificaVazio(email)) {
			if (!Valida.validarEmail(email)) {
				JSFUtil.addInfoMessage(Rotulo.ERROR.getDescricao(), Mensagem.dadosInvalidos, Mensagem.emailInvalido);
				return false;
			}
		}
		return true;
	}

}
