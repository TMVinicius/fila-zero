package model;

public class Estabelecimento extends Usuario{
		
	private String cnpj;
	
	public Estabelecimento(Integer idUser, String nome, String email, String senha, String cnpj) {
		super(idUser, nome, email, senha);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
