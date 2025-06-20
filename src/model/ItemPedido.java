package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedido {

	private Produto produto;
	private int quantidade;
	private double precoUnitario;
	private String nomeProduto;

	public ItemPedido() {
		
	}
	
	
	public ItemPedido(Produto produto, int quantidade) {
		if (produto == null) {
			throw new IllegalArgumentException("Produto não pode ser nulo em ItemPedido");
		}
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPrecoUnitario();
		this.nomeProduto = produto.getNomeProduto();
	}

	public ItemPedido(Produto produto, int quantidade, double precoUnitario) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.nomeProduto = produto != null ? produto.getNomeProduto() : null;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		if (produto != null) {
			this.precoUnitario = produto.getPrecoUnitario();
			this.nomeProduto = produto.getNomeProduto();
		}
	}

	public double getSubtotal() {
		return this.precoUnitario * this.quantidade;
	}

}
