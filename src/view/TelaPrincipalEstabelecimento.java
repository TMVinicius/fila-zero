package view;

import javax.swing.*;
import model.Estabelecimento;
import view.cadastro.TelaCadastroProduto;

import java.awt.*;

public class TelaPrincipalEstabelecimento extends JFrame {

    private Estabelecimento estabelecimento;

    public TelaPrincipalEstabelecimento(Estabelecimento est) {
        this.estabelecimento = est;

        setTitle("Painel do Estabelecimento");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel labelBoasVindas = new JLabel("Bem-vindo, " + est.getNome(), SwingConstants.CENTER);
        painel.add(labelBoasVindas);

        JButton botaoCadastrarProduto = new JButton("Cadastrar Produto");
        JButton botaoRelatorioPedidos = new JButton("Ver Relatório de Pedidos");

        painel.add(botaoCadastrarProduto);
        painel.add(botaoRelatorioPedidos);

        add(painel);

        botaoCadastrarProduto.addActionListener(e -> {
            new TelaCadastroProduto(estabelecimento).setVisible(true);
            dispose(); 
        });

      
        botaoRelatorioPedidos.addActionListener(e -> {
            new TelaRelatorioPedidos(estabelecimento).setVisible(true);
            dispose(); 
        });
    }
}
