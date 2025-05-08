package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vemprafam.pojo.Aluno;

@Repository
public class DaoAluno {

	private Connection connection;

	public DaoAluno(DataSource dataSource) {
		if (connection == null) {
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(Aluno aluno) {
		String sql = "INSERT INTO ALUNOS VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, aluno.getRa());
			pstmt.setString(2, aluno.getNome());
			pstmt.setDate(3,
				new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(4, aluno.getRenda());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Aluno> getLista() {
		List<Aluno> lista = new ArrayList<Aluno>();
		String sql = "SELECT * FROM ALUNOS";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				lista.add(new Aluno(ra, nome, dataNascimento, renda));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Aluno buscarPeloRa(int ra) {
		String sql = "SELECT * FROM ALUNOS WHERE RA = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ra);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				return new Aluno(ra, nome,dataNascimento, renda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(Aluno aluno) {
		String sql =
			"UPDATE ALUNOS SET NOME=?,DATANASCIMENTO=?,RENDA=? WHERE RA=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, aluno.getNome());
			pstmt.setDate(2,
				new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(3, aluno.getRenda());
			pstmt.setInt(4, aluno.getRa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(Aluno aluno) {
		String sql = "DELETE FROM ALUNOS WHERE RA=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, aluno.getRa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		DaoAluno dao = new DaoAluno();
//		dao.insert(new Aluno(1,"aaa",new java.util.Date(),1000.0));
//		dao.insert(new Aluno(2,"bbb",new java.util.Date(),2000.0));
//		dao.insert(new Aluno(3,"ccc",new java.util.Date(),3000.0));
//		dao.insert(new Aluno(4,"ddd",new java.util.Date(),4000.0));
//		System.out.println(dao.buscarPeloRa(3));
//		dao.update(new Aluno(3,"ccc123",new java.util.Date(),3333.3));
//		System.out.println(dao.getLista());
//		dao.delete(new Aluno(4,null,null,null));
//		System.out.println(dao.getLista());
	}
}
