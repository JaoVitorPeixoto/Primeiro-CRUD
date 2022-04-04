package Conexoes;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao_mysql {
	
	// Nome do usuário no MySQL
	private static final String USUARIO = "root";
	
	// Senha do usuário no MySQL
	private static final String SENHA = "jvrfp15";
	
	// Caminho do banco, porta, banco de dados
	private static final String URL_BANCO = "jdbc:mysql://localhost:3306/Cadastro1";
	
	/*
	 * Conexão com o MySQL
	 */
	
	public static Connection conexaoBD() throws Exception{
		
		// Faz com que o Driver seja carregado pelo java
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Cria conexao com o banco de dados
		Connection conexao = DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
		
		return conexao;
		
	}
	
	
}
