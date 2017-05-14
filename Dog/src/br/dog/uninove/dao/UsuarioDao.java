package br.dog.uninove.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import ConnectionFactory.ConnectionFactory;
import br.dog.uninove.model.Usuario;

public class UsuarioDao implements DAO {

	@Override
	public void adicionar(Object object) throws SQLException {

		Connection con = ConnectionFactory.getConnectionJDBC();
		Usuario user = (Usuario) object;
		String sql = "insert into Usuario (nome,email,logradouro,numero,cep,bairro,data_nascimento,sexo,senha) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);

		try {

			// preenche os valores
			pst.setString(1, user.getNome());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getLogradouro());
			pst.setInt(4, user.getNumero());
			pst.setInt(5, user.getCep());
			pst.setString(6, user.getBairo());
			pst.setDate(7, new Date(user.getdataNascimento().getTimeInMillis()));
			pst.setString(8, user.getSexo());
			pst.setString(9, user.getSenha());
			// executa
			pst.execute();
			System.out.println("Usuario gravado!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pst.close();
		}

	}

	@Override
	public List<Object> buscarTodos() throws SQLException {
		List<Object> listaDeUsuario = null;

		Connection con = new ConnectionFactory().getConnectionJDBC();
		PreparedStatement stmt = con.prepareStatement("select * from usuario");

		// executa um select
		ResultSet rs = stmt.executeQuery();
		try {
			// itera no ResultSet
			while (rs.next()) {
				if (listaDeUsuario == null) {
					listaDeUsuario = new ArrayList<Object>();
				}

				Usuario novoUsuario = new Usuario();

				novoUsuario.setNome(rs.getString("nome"));
				novoUsuario.setEmail(rs.getString("email"));
				novoUsuario.setIdUsuario(rs.getLong("id_usuario"));

				// montando a data atrav�s do Calendar
				Calendar data = new GregorianCalendar();
				data.setTime(rs.getDate("data_nascimento"));
				novoUsuario.setdataNascimento(data);
				novoUsuario.setLogradouro(rs.getString("Logradouro"));
				novoUsuario.setBairo(rs.getString("bairro"));
				novoUsuario.setSenha(rs.getString("senha"));
				novoUsuario.setSexo(rs.getString("sexo"));
				novoUsuario.setNumero(rs.getInt("numero"));
				novoUsuario.setCep(rs.getInt("cep"));
				listaDeUsuario.add(novoUsuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();

		}
		return listaDeUsuario;
	}

	@Override
	public void removerTodos() throws SQLException {
		Connection con = new ConnectionFactory().getConnectionJDBC();
		PreparedStatement stmt = con.prepareStatement("delete from usuario");
		try {
			stmt.execute();
			System.out.println("Usuarios deletados");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			stmt.close();
		}

	}

	@Override
	public void atualizar(Object object, int a) throws SQLException {
		Connection con = new ConnectionFactory().getConnectionJDBC();
		Usuario user = (Usuario) object;
		PreparedStatement stmt = null;
		Scanner teclado = new Scanner(System.in);
		try {
			switch (a) {
			case 1:
				stmt = con.prepareStatement("update usuario set nome = ? where id_usuario = ?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setString(1, user.getNome());
				stmt.execute();
				break;
			case 2:
				stmt = con.prepareStatement("update usuario set email = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setString(1, user.getEmail());
				stmt.execute();
				break;
			case 3:
				stmt = con.prepareStatement("update usuario set logradouro = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setString(1, user.getLogradouro());
				stmt.execute();
				break;
			case 4:
				stmt = con.prepareStatement("update usuario set data_nascimento = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setDate(1, new java.sql.Date(user.getdataNascimento().getTimeInMillis()));
				stmt.execute();
				break;
			case 5:
				stmt = con.prepareStatement("update usuario set numero = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setInt(1, user.getNumero());
				stmt.execute();
				break;
			case 6:
				stmt = con.prepareStatement("update usuario set cep = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setInt(1, user.getCep());
				stmt.execute();
				break;
			case 7:
				stmt = con.prepareStatement("update usuario set bairro = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());stmt.setString(1, user.getBairo());
				stmt.execute();
				break;
			case 8:
				stmt = con.prepareStatement("update usuario set sexo = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());stmt.setString(1, user.getSexo());
				stmt.execute();
				break;
			case 9:
				stmt = con.prepareStatement("update usuario set senha = ? where id_usuario=?");
				stmt.setLong(2, user.getIdUsuario());
				stmt.setString(1, user.getSenha());
				stmt.execute();
				break;

			}
			System.out.println("Usuario Alterado");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	@Override
	public Object buscarPorId(Object object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Object object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object buscarPorNome(Object object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}