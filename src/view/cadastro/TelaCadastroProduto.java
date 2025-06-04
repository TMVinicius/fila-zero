package view.cadastro;

import javax.swing.*;
import java.awt.*;
import model.Produto;
import model.Estabelecimento;
import repo.ProdutoRepo;
import view.TelaPrincipalEstabelecimento;

public class TelaCadastroProduto extends JFrame {

    private JTextField campoNome, campoCodigoBarras, campoPreco, campoQuantidade;
    private Estabelecimento estabelecimento;

    public TelaCadastroProduto(Estabelecimento est) {
        this.estabelecimento = est;

        setTitle("Cadastro de Produto");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        painel.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painel.add(campoNome);

        painel.add(new JLabel("Código de Barras:"));
        campoCodigoBarras = new JTextField();
        painel.add(campoCodigoBarras);

        painel.add(new JLabel("Preço:"));
        campoPreco = new JTextField();
        painel.add(campoPreco);
        
        painel.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        painel.add(campoQuantidade);

        JButton botaoSalvar = new JButton("Salvar Produto");
        painel.add(new JLabel()); 
        painel.add(botaoSalvar);

        add(painel);

        botaoSalvar.addActionListener(e -> {
            try {
                String nome = campoNome.getText();
                String codigo = campoCodigoBarras.getText();
                Double preco = Double.parseDouble(campoPreco.getText());
                Integer quantidade = Integer.parseInt(campoQuantidade.getText());
                
                Produto produto = new Produto(nome, codigo, preco,quantidade, estabelecimento);
                ProdutoRepo repo = new ProdutoRepo();
                repo.salvar(produto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                dispose(); 
                new TelaPrincipalEstabelecimento(estabelecimento).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
            }
        });
    }
}
