package model;

public class Cliente extends Usuario {

	private String cpf;
	private String telefone;

	public Cliente() {

	}

	public Cliente(String nome, String email, String senha, String cpf, String telefone) {
		super(nome, email, senha);
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Cliente(Integer idUser, String nome, String email, String senha, String cpf, String telefone) {
		super(idUser, nome, email, senha);
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void consultaProduto(Produto p) {
		System.out.println(p.getPrecoUnitario());

	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", telefone=" + telefone + "]";
	}
	
	

}
