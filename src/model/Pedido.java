package model;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {

	private Integer idPedido;
	private Double valorTotal;
	private LocalDateTime dataCompra;
	private Cliente cliente;
	private List<Produto> itens = new ArrayList<Produto>();

	public Pedido(){
		
	}
	
	public Pedido(Integer idPedido, Double valorTotal, LocalDateTime dataCompra, Cliente cliente, List<Produto> itens) {
		super();
		this.idPedido = idPedido;
		this.valorTotal = valorTotal;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
		this.itens = itens;
	}

	public Pedido(Integer idPedido, Double valorTotal, LocalDateTime dataCompra, Cliente cliente) {
		super();
		this.idPedido = idPedido;
		this.valorTotal = valorTotal;
		this.dataCompra = dataCompra;
		this.cliente = cliente;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
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

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

}
