package model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pedido {

	private Integer idPedido;
	private Double valorTotal;
	private LocalDateTime dataCompra;
	private Cliente cliente;
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {
	}

	public Pedido(Integer idPedido, Double valorTotal, LocalDateTime dataCompra, Cliente cliente,
			List<ItemPedido> itens) {
		this.idPedido = idPedido;
		this.valorTotal = valorTotal;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
		this.itens = itens;
	}

	public Pedido(Integer idPedido, Double valorTotal, LocalDateTime dataCompra, Cliente cliente) {
		this.idPedido = idPedido;
		this.valorTotal = valorTotal;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
	}

}
