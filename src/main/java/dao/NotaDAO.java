package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import config.DBConfig;
import entidade.Nota;

public class NotaDAO {
	
	public List<Nota> listarNotas() throws Exception {
		List <Nota> lista = new ArrayList<>();
		
		//Connection conexao = DBConfig.getInstance().getConnection();
		Connection conexao = DBConfig.getConnection();
		
		String sql = "SELECT * FROM tb_notas";
		
		PreparedStatement statement = (PreparedStatement) conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			Nota nota = new Nota();
			nota.setId(rs.getInt("id_notas"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));
			
			lista.add(nota);
			
		}
		
		return lista;
		
	}
	
	public Nota buscarNotaPorId(int idNotas) throws Exception {
		Nota nota = null;
		
		Connection conexao = DBConfig.getConnection();
		String sql = "SELECT * FROM tb_notas WHERE id_notas = ?";
		
		PreparedStatement statement = (PreparedStatement) conexao.prepareStatement(sql);
		statement.setInt(1, idNotas);
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("id_notas"));
			nota.setTitulo("titulo");
			nota.setDescricao("descricao");
		}
		return nota;
	}
	
	public void addNota(Nota nota) throws Exception {
		Connection conexao = DBConfig.getConnection();
		
		String sql = "INSERT INTO tb_notas(titulo, descricao) VALUES(?, ?)";
		
		PreparedStatement statement = (PreparedStatement) conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();
	}
	public void editarNota(Nota nota) throws Exception {
		Connection conexao = DBConfig.getConnection();
		
		String sql = "UPDATE tb_notas SET titulo = ?, descricao = ? where id_notas = ?";
		
		PreparedStatement statement = (PreparedStatement) conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, nota.getId());
		statement.execute();
		
	}
	
	public void removerNota(int idNota) throws Exception {
		Connection conexao = DBConfig.getConnection();
		
		String sql = "DELETE FROM tb_notas WHERE id_notas = ?";
		
		PreparedStatement statement = (PreparedStatement) conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		statement.execute();
	}
		
}
