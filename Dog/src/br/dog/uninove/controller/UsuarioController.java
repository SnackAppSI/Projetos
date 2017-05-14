package br.dog.uninove.controller;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import br.dog.uninove.dao.DAO;
import br.dog.uninove.dao.UsuarioDao;
import br.dog.uninove.model.Usuario;

public class UsuarioController {

	public static void processoAddUser() {
		Usuario user = new Usuario();
		Scanner teclado = new Scanner(System.in);
		Scanner teclado1 = new Scanner(System.in);
		System.out.print("Nome:");
		user.setNome(teclado.nextLine());
		System.out.print("Email:");
		user.setEmail(teclado.nextLine());
		System.out.print("Logradouro:");
		user.setLogradouro(teclado.nextLine());
		System.out.print("Numero:");
		user.setNumero(teclado1.nextInt());
		System.out.println("CEP:");
		user.setCep(teclado1.nextInt());
		System.out.println("Bairro:");
		user.setBairo(teclado.nextLine());
		// Capturar data digitada
		String stdata = null;
		do {
			try {
				System.out.print("Data de Nascimento(dd/MM/yyyy):");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Scanner teclado2 = new Scanner(System.in);
				stdata = teclado2.nextLine();
				Calendar caldata = new GregorianCalendar();
				caldata.setTime(sdf.parse(stdata));
				user.setdataNascimento(caldata);
			} catch (ParseException e) {
				e.printStackTrace();
				stdata = null;
				System.out.println("Formato de data invalido, digite 'dd/mm/yyyy'");

			}
		} while (user.getdataNascimento() == null);
		System.out.println("F para Feminino\nM para Masculino");
		user.setSexo(teclado.nextLine().toUpperCase());
		System.out.println("Senha:");
		user.setSenha(teclado.nextLine());
		DAO userdao = new UsuarioDao();
		try {
			userdao.adicionar(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void processoBuscaUsuarios() {

		DAO userDAO = new UsuarioDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			if (userDAO.buscarTodos() != null)
				try {
					System.out.println("\t\t\t\t\t\t|Usuarios|");
					for (Object usuario : userDAO.buscarTodos()) {
						Usuario user = (Usuario) usuario;
						System.out.println("\t\t\tID:" + user.getIdUsuario() + "\tNome:" + user.getNome()
								+ "\tDataNascimento:" + sdf.format(user.getdataNascimento().getTime()) + "\tSexo:"
								+ user.getSexo().toUpperCase() + "\n\t\tLogradouro:" + user.getLogradouro()
								+ "\tNumero:" + user.getNumero() + "\tCep:" + user.getCep() + "\tBairro:"
								+ user.getBairo() + "\n\t\t\t\t\tLogin:" + user.getEmail() + "\tSenha:"
								+ user.getSenha());
					}
					System.out.println("\n\n\n");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else {
				System.out.println("Nenhum usuario gravado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void processoRemoverTodos() {
		DAO userdao = new UsuarioDao();
		try {
			userdao.removerTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void processoAtualizar() {
		DAO userdao = new UsuarioDao();
		processoBuscaUsuarios();
		Usuario user = new Usuario();
		Scanner teclado = new Scanner(System.in);
		Scanner teclado1 = new Scanner(System.in);
		System.out.print("Digite id do usuario:");
		user.setIdUsuario(teclado1.nextLong());
		int a = 0;
		System.out.println(
				"Digite numero correspondente ao campo que deseja alterar:\n1-Nome\n2-Email\n3-Logradouro\n4-Data de Nascimento\n5-Numero\n6-CEP\n7-Bairro\n8-Sexo\n9-Senha");
		a = teclado1.nextInt();
		try {
			switch (a) {
			case 1:
				System.out.print("Digite o novo nome:");
				user.setNome(teclado.nextLine());
				break;
			case 2:
				System.out.print("Digite o novo e-mail:");
				user.setEmail(teclado.nextLine());
				break;
			case 3:
				System.out.print("Digite o novo Logradouro:");
				user.setLogradouro(teclado.nextLine());
				break;
			case 4:
				do {
					String stdata = null;
					try {
						System.out.print("Digite a nova data:");
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Scanner teclado2 = new Scanner(System.in);
						stdata = teclado2.nextLine();
						Calendar caldata = new GregorianCalendar();
						caldata.setTime(sdf.parse(stdata));
						user.setdataNascimento(caldata);
					} catch (ParseException e) {
						e.printStackTrace();
						stdata = null;
						System.out.println("Formato de data invalido, digite 'dd/mm/yyyy'");

					}
				} while (user.getdataNascimento() == null);

				break;
			case 5:
				System.out.print("Digite o novo Numero:");
				user.setLogradouro(teclado.nextLine());
				break;
			case 6:
				System.out.print("Digite o novo CEP:");
				user.setLogradouro(teclado.nextLine());
				break;
			case 7:
				System.out.print("Digite o novo Bairro:");
				user.setLogradouro(teclado.nextLine());
				break;
			case 8:
				System.out.print("Digite o novo Sexo:");
				user.setLogradouro(teclado.nextLine());
				break;
			case 9:
				System.out.print("Digite a nova Senha:");
				user.setLogradouro(teclado.nextLine());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			userdao.atualizar(user, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}