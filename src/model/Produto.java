package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

	private Integer idProduto;
	private String nomeProduto;
	private Integer codigoDeBarras;
	private Double precoUnitario;

	public Produto() {
	}

	public Produto(Integer idProduto, String nomeProduto, Integer codigoDeBarras, Double precoUnitario) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.codigoDeBarras = codigoDeBarras;
		this.precoUnitario = precoUnitario;
	}

}
