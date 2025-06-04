package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

	private Integer idProduto;
	private String nomeProduto;
	private String codigoDeBarras;
	private Double precoUnitario;
	private Integer Quantidade;
	private Estabelecimento estabelecimento;
	
	public Produto() {
	}

	public Produto(Integer idProduto, String nomeProduto, String codigoDeBarras, Double precoUnitario) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.codigoDeBarras = codigoDeBarras;
		this.precoUnitario = precoUnitario;
	}

	public Produto(String nomeProduto, String codigoDeBarras, Double precoUnitario, Estabelecimento estabelecimento) {
		
		this.nomeProduto = nomeProduto;
		this.codigoDeBarras = codigoDeBarras;
		this.precoUnitario = precoUnitario;
		this.estabelecimento = estabelecimento;
	}

	public Produto(String nomeProduto, String codigoDeBarras, Double precoUnitario, Integer quantidade,
			Estabelecimento estabelecimento) {
		this.nomeProduto = nomeProduto;
		this.codigoDeBarras = codigoDeBarras;
		this.precoUnitario = precoUnitario;
		Quantidade = quantidade;
		this.estabelecimento = estabelecimento;
	}
	
	
	
	
	

}
