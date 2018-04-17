package br.com.cpsoftware.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarOrcamento extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("action", "Add");          // Part of the Header in form.jsp
	    req.setAttribute("destination", "create");  // The urlPattern to invoke (this Servlet)
	    req.setAttribute("page", "criarOrcamento");           // Tells base.jsp to include form.jsp
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		
	}
}
