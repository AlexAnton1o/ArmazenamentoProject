package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexaoDB.Conexao;

public class ClienteDAO {
	public static boolean criarTabelaCliente() {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "CREATE TABLE cliente("
					+ "codCliente INT PRIMARY KEY AUTO_INCREMENT, "
					+ "nome VARCHAR(50) NOT NULL, "
					+ "email VARCHAR(50) NOT NULL, "
					+ "endereco VARCHAR(100), "
					+ "cidade VARCHAR(30), "
					+ "estado CHAR(2), "
					+ "fone VARCHAR(15));";
			conn = Conexao.criarConexao();
			stat = conn.prepareStatement(sql);
			stat.execute();
			return true;
		}
		catch(Exception e) {
			System.out.println("ERRO: A tabela cliente n�o foi criada. - " + e.getMessage());
			return false;
		} finally {
			Conexao.fecharConexao(conn, stat);
		}
	}
	
	public static boolean excluirTabelaCliente() {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "DROP TABLE cliente";
			conn = Conexao.criarConexao();
			stat = conn.prepareStatement(sql);
			stat.execute();
			return true;
		} catch(Exception e) {
			System.out.println("ERRO: A tabela cliente n�o foi excluida. -"+ e.getMessage());
			return false;
		} finally {
			Conexao.fecharConexao(conn, stat);
		}
	}
	
	public static boolean inserirCliente(Cliente cli) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "INSERT INTO" + "cliente( nome, email, endereco, cidade, estado, fone) " + "VALUES (?, ?, ?, ?, ?);";
			
			conn = Conexao.criarConexao();
			stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, cli.getNome());
			stat.setString(2, cli.getEmail());
			stat.setString(3, cli.getEndereco());
			stat.setString(4, cli.getCidade());
			stat.setString(5, cli.getEstado());
			stat.setString(6, cli.getFone());
			stat.execute();		
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				cli.setCodCliente(rs.getInt(1));
			}
			
			return true;
			
		}
		
		catch (Exception e) {
			System.out.println("ERRO: Cliente n�o inserido. " + e.getMessage());
			return false;
		}
		
		finally {
			Conexao.fecharConexao(conn, stat);
		}
	}
	
	public static boolean alterarcliente(Cliente cli) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "UPADDTE Cliente SET "
					+ "nome=?, "
					+ "email=?, "
					+ "endereco=?, "
					+ "cidade=?, "
					+ "estado=?, "
					+ "fone=?, "
					+ "WHERE CodCliente=?";
			
			conn = Conexao.criarConexao();
			stat = conn.prepareStatement(sql);
			stat.setString(1, cli.getNome());
			stat.setString(2, cli.getEmail());
			stat.setString(3, cli.getEndereco());
			stat.setString(4, cli.getCidade());
			stat.setString(5, cli.getEstado());
			stat.setString(6, cli.getFone());
			stat.setInt(7, cli.getCodCliente());
			stat.execute();	
			return true;
		} catch (Exception e) {
			System.out.println("ERRO: Cliente n�o alterado. " + e.getMessage());
			return false;
		} finally {
			Conexao.fecharConexao(conn, stat);
		}
	}
	

}
