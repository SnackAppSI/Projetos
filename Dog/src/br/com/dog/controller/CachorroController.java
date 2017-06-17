package br.com.dog.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dog.controller.util.NavegacaoUtil;
import br.com.dog.model.dao.CachorroDAO;
import br.com.dog.model.dao.UsuarioDAO;
import br.com.dog.model.dao.impl.jdbc.CachorroDao;
import br.com.dog.model.dao.impl.jdbc.UsuarioDao;
import br.com.dog.model.entity.Cachorro;
import br.com.dog.model.entity.Usuario;

/**
 * Servlet implementation class CachorroController
 */
@WebServlet("/CachorroController.do")
public class CachorroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CachorroController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cachorro dog = null;
		Usuario user = null;
		CachorroDAO dogDAO = new CachorroDao();
		UsuarioDAO userDAO = new UsuarioDao();
		String action = request.getParameter("action");
		RequestDispatcher view = null;
		user = new Usuario();
		user.setIdUsuario(Long.parseLong(request.getParameter("idUsuario")));
		try {
			user = userDAO.read(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (action.equalsIgnoreCase("remover")) {
			dog = new Cachorro();
			dog.setIdCachorro(Long.parseLong(request.getParameter("idDog")));

			try {
				dogDAO.delete(dog);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				request.setAttribute("myDogs", dogDAO.buscaPorUsuario(user));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view = request.getRequestDispatcher(NavegacaoUtil.HOME);
			request.setAttribute("usuarioLogado", user);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		Cachorro dog = null;
		Usuario user = null;
		CachorroDAO dogDAO = new CachorroDao();
		UsuarioDAO userDAO = new UsuarioDao();
		String action = request.getParameter("action");
		RequestDispatcher view = null;
		user = new Usuario();
		user.setIdUsuario(Long.parseLong(request.getParameter("idUsuario")));
		try {
			user = userDAO.read(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Cadastrar Novo Dog
		if (action.equalsIgnoreCase("cadastrar")) {
			dog = new Cachorro();

			dog.setNome(request.getParameter("nome"));
			dog.setSexo(request.getParameter("optradio").toUpperCase());
			dog.setIdade(Integer.parseInt(request.getParameter("idade")));
			dog.setIdUsuario(user.getIdUsuario());
			dog.setRaca(request.getParameter("raca"));

			try {
				dogDAO.create(dog);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view = request.getRequestDispatcher(NavegacaoUtil.HOME);

		} else if (action.equalsIgnoreCase("editar")) {

			dog = new Cachorro();
			
			dog.setIdCachorro(Long.parseLong(request.getParameter("idatualizar")));
			dog.setNome(request.getParameter("nomeedit"));
			dog.setSexo(request.getParameter("optradiofm").toUpperCase());
			dog.setIdade(Integer.parseInt(request.getParameter("idadeedit")));
			dog.setIdUsuario(user.getIdUsuario());
			dog.setRaca(request.getParameter("racaedit"));
			try {
				dogDAO.update(dog);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view = request.getRequestDispatcher(NavegacaoUtil.HOME);
		}
		try {
			request.setAttribute("myDogs", dogDAO.buscaPorUsuario(user));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("usuarioLogado", user);
		view.forward(request, response);

	}

}
