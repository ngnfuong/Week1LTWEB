package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.IUserService;
import services.impl.UserServiceImpl;

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORGOT_PASSWORD = "/views/forgotPassword.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(FORGOT_PASSWORD).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String alertMsg = "";
		IUserService service = new UserServiceImpl();
		if (password != null && email != null) {
			service.updatePassword(email, password);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		if (service.checkExistEmail(email)) {
			request.setAttribute("email", email);
			request.getRequestDispatcher(FORGOT_PASSWORD).forward(request, response);
		} else {
			alertMsg = "Email không tồn tại!";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher(FORGOT_PASSWORD).forward(request, response);
		}
	}

}
