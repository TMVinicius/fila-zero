package repo;

import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepo {

	public void salvar(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome, email, senha, cpf, telefone) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getSenha());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getTelefone());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cliente buscarCpfSenha(String cpf, String senha) {
		String sql = "SELECT * FROM cliente WHERE cpf = ? AND senha = ?";
		Cliente cliente = null;

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cpf);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"), rs.getString("cpf"), rs.getString("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}
}
