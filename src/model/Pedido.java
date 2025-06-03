package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private Integer idPedido;
	private Double valorTotal;
	private LocalDateTime dataCompra;
	private Cliente cliente;
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {}

	public Pedido(Integer idPedido, Double valorTotal, LocalDateTime dataCompra, Cliente cliente, List<ItemPedido> itens) {
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

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
}
