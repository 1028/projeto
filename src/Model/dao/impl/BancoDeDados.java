package Model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
	// Carrega driver JDBC
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	
	public Connection obtemConexao() throws SQLException {
		// Realiza Conexão no PC dos Desenvolvedores.
		 //return
		// DriverManager.getConnection("jdbc:mysql://localhost/dbprojeto1", "root", "490779");

		 // Realiza Conexão no PC do Universidade.
		 return
		 DriverManager.getConnection("jdbc:mysql://localhost/dbprojeto1", "root", ""); 
	}
}
