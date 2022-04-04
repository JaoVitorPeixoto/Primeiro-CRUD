package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Conexoes.Conexao_mysql;
import cadastro.com.Pessoa;


public class Pessoa_DAO {
	
	/*
	 * c: CREATE - INSERT
	 * r: READ - SELECT
	 * u: UPDATE - UPDATE
	 * d: DELETE - DELETE
	 */
	
	Connection con = null;
	PreparedStatement pstm = null;
	
	// Classe que recupera dados do banco ~~~SELECT~~~
	ResultSet rst = null;
	
	public void InserirPessoa(Pessoa pessoa) {
		
		String comando = "INSERT INTO Pessoa(nome, idade, nacionalidade) VALUES (?, ?, ?)";
		
		
		try {
			// Cria uma conexão com o banco de dados
			con = Conexao_mysql.conexaoBD();
			
			// Criamos uma PreparedStatement para executar uma query
			pstm = con.prepareStatement(comando);
			
			// Adicionando os valores
			pstm.setString(1, pessoa.getNome());
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(3, pessoa.getNacionalidade());
			
			// Executando a query
			pstm.execute();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
	}
	
	public void AtualizarPessoa(Pessoa pessoa) {
		
		String comando = "UPDATE pessoa SET nome = ?, idade = ?, nacionalidade = ? WHERE id = ?";
		
		
		try {
			con = Conexao_mysql.conexaoBD();
			
			pstm = con.prepareStatement(comando);
			
			pstm.setString(1, pessoa.getNome());
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(3, pessoa.getNacionalidade());
			pstm.setInt(4, pessoa.getId());
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
			
			
		
	}
	
	public void DeletarPessoa_porID(int id) {
		
		String comando = "DELETE FROM Pessoa WHERE id = ?";
		
		
		try {
			con = Conexao_mysql.conexaoBD();
			
			pstm = con.prepareStatement(comando);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
	}
	
	public List<Pessoa> MostrarPessoas() {
		
		String comando = "SELECT * FROM Pessoa";
		List<Pessoa> Pessoas = new ArrayList<Pessoa>();
		
		try {
			con = Conexao_mysql.conexaoBD();
			
			pstm = con.prepareStatement(comando);
			
			rst = pstm.executeQuery();
			
			while(rst.next()) {
				Pessoa p = new Pessoa();
				
				p.setId(rst.getInt("id"));
				p.setIdade(rst.getInt("idade"));
				p.setNome(rst.getString("nome"));
				p.setNacionalidade(rst.getString("nacionalidade"));
				
				Pessoas.add(p);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
		return Pessoas;
		
	}
	
	public void fecharConexao() {
		try {
			if(con!=null) {
				con.close();
			}
			
			if(pstm!=null) {
				pstm.close();
			}
			
			if (rst != null) {
				rst.close();
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
