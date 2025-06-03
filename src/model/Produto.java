package model;

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

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(Integer codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
