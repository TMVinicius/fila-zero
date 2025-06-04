package view.cliente;

import model.Cliente;
import model.Estabelecimento;
import model.Produto;
import repo.ProdutoRepo;

import javax.swing.*;
import java.awt.*;

public class TelaBuscarProduto extends JFrame {

    private Cliente cliente;
    private Estabelecimento estabelecimento;

    public TelaBuscarProduto(Cliente cliente, Estabelecimento estabelecimento) {
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;

        setTitle("Buscar Produto");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblCodigo = new JLabel("Digite o código de barras:");
        JTextField txtCodigo = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        JTextArea resultado = new JTextArea(5, 30);
        resultado.setEditable(false);

        btnBuscar.addActionListener(e -> {
            try {
                String codigoDigitado = txtCodigo.getText().replace(" ", "");

                ProdutoRepo repo = new ProdutoRepo();
                Produto produto = repo.buscarCodigoEEstabelecimento(codigoDigitado, estabelecimento.getId());

                if (produto != null) {
                    resultado.setText("Produto encontrado:\n");
                    resultado.append("Nome: " + produto.getNomeProduto() + "\n");
                    resultado.append(String.format("Preço: R$ %.2f", produto.getPrecoUnitario()));

                } else {
                    resultado.setText("Produto não encontrado neste estabelecimento.");
                }
            } catch (NumberFormatException ex) {
                resultado.setText("Por favor, digite um código de barras válido.");
            }
        });


        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(lblCodigo);
        painel.add(txtCodigo);
        painel.add(btnBuscar);
        painel.add(new JScrollPane(resultado));

        add(painel);
    }
}
