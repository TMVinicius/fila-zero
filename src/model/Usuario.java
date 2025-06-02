package model;

public class Usuario {

	private Integer idUser;
	private String nome;
	private String email;
	private String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id,String nome, String email, String senha) {
		this.idUser = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		
	}
	
	public Integer getId() {
		return idUser;
	}
	
	public void setId(int id) {
		this.idUser = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
