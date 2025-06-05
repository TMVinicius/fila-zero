package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedido {

	private Produto produto;
	private int quantidade;
	private double precoUnitario;

	public ItemPedido() {
	}

	public ItemPedido(Produto produto, int quantidade, double precoUnitario) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public double getSubtotal() {
		return precoUnitario * quantidade;
	}

	public ItemPedido(Produto produto, int quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}
}
