/**
 * 
 */
package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;

/**
 * @author PROJETOS
 *
 */
@SuppressWarnings("serial")
public class MostrarGraficos extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long projetoId = Long.parseLong((String) req.getSession().getAttribute("projetoEditavel"));
		req.setAttribute("projeto", new ProjetoDAO().read(projetoId));
		
		req.setAttribute("page", "mostrarGraficos");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
}
