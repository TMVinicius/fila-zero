package main;

import javax.swing.SwingUtilities;
import model.Cliente;
import model.Estabelecimento;
import repo.ClienteRepo;
import repo.EstabelecimentoRepo;
import view.login.TelaInicialLogin;
import view.login.TelaLoginCliente;

public class App {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new TelaInicialLogin().setVisible(true);
		});
		
		

	}
}