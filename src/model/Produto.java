package model;

public class Produto {

	private Integer idPedido;
	private String nomeProduto;
	private String descricao;
	private Integer codigoDeBarras;
	private Double precoUnitario;
	private Integer quantidade;

	public Produto() {

	}

	public Produto(Integer idPedido, String nomeProduto, String descricao, Integer codigoDeBarras, Double precoUnitario,
			Integer quantidade) {
		this.idPedido = idPedido;
		this.nomeProduto = nomeProduto;
		this.descricao = descricao;
		this.codigoDeBarras = codigoDeBarras;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	

}
