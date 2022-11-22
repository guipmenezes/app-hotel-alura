package Modelo;

import java.sql.Date;

public class Hospedes {
	
	private Integer id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String nacionalidade;
	private String telefone;
	
	public Hospedes(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
	}
	
	public Hospedes(Integer id,String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public String getTelefone() {
		return telefone;
	}
}
