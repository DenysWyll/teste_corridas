package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() {

		Connection con = null;

		try { 
		
		//referencia a classe que deve ser utilizada para conexao
		Class.forName("com.mysql.jdbc.Driver");
		
		//endereco da base de dados
		String url = "jdbc:mysql://localhost:3306/Teste";
		
		//configuracoes de acesso ao bd
		con = DriverManager.getConnection( url, "root", "1234");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return con;

	}
}
