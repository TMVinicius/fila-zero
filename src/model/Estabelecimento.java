package model;

import lombok.Getter;
import lombok.Setter;

public class Estabelecimento extends Usuario {

	@Getter
	@Setter
	private String cnpj;

	public Estabelecimento() {

	}

	public Estabelecimento(String nome, String email, String senha, String cnpj) {
		super(nome, email, senha);
		this.cnpj = cnpj;
	}

	public Estabelecimento(Integer idUser, String nome, String email, String senha, String cnpj) {
		super(idUser, nome, email, senha);
		this.cnpj = cnpj;
	}

}
