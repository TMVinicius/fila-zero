package main;

import javax.swing.SwingUtilities;
import model.Cliente;
import repo.ClienteRepo;
import view.TelaInicialLogin;

public class App {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new TelaInicialLogin().setVisible(true);
		});

		// Cliente novoCliente = new Cliente("Joao da Silva", "joao@email.com",
		// "senha123", "123.456.789-00","(31) 99999-8888");

		ClienteRepo repo = new ClienteRepo();
		// repo.salvar(novoCliente);

		System.out.println("Cliente cadastrado com sucesso!");

		System.out.println(repo.buscarCpfSenha("123.456.789-00", "senha123"));

	}
}