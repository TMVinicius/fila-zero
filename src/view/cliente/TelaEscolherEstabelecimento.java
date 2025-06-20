package view.cliente;

import model.Estabelecimento;
import model.Cliente;
import repo.EstabelecimentoRepo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEscolherEstabelecimento extends JFrame {

    private Cliente cliente;

    public TelaEscolherEstabelecimento(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Escolha o Estabelecimento");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EstabelecimentoRepo repo = new EstabelecimentoRepo();
        List<Estabelecimento> estabelecimentos = repo.listarTodos();

        JPanel painel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Estabelecimento est : estabelecimentos) {
            listModel.addElement(est.getId() + " - " + est.getNome());
        }

        JList<String> lista = new JList<>(listModel);
        JScrollPane scroll = new JScrollPane(lista);

        JButton botaoSelecionar = new JButton("Selecionar");

        botaoSelecionar.addActionListener(e -> {
            int selectedIndex = lista.getSelectedIndex();
            if (selectedIndex != -1) {
                Estabelecimento escolhido = estabelecimentos.get(selectedIndex);
                JOptionPane.showMessageDialog(null, "Estabelecimento selecionado: " + escolhido.getNome());
                
                new TelaBuscarProduto(cliente, escolhido).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um estabelecimento.");
            }
        });

        painel.add(scroll, BorderLayout.CENTER);
        painel.add(botaoSelecionar, BorderLayout.SOUTH);

        add(painel);
    }
}
