package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedido {

	private Produto produto;
	private int quantidade;

	public ItemPedido() {
	}

	public ItemPedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

}
