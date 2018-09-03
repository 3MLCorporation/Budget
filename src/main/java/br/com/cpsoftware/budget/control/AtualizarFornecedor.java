package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.UnidadeFederativaDAO;
import br.com.cpsoftware.budget.model.Fornecedor;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class AtualizarFornecedor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	Fornecedor fornecedor = new FornecedorDAO().read(Long.parseLong(req.getParameter("fornecedorId")));
    	req.setAttribute("fornecedor", fornecedor);
    	req.setAttribute("ufs", new UnidadeFederativaDAO().getUnidadesFederativas());
        req.setAttribute("page", "atualizarFornecedor");          
        req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
        
    }
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
    	Long fornecedorId = Long.parseLong(req.getParameter("fornecedorId"));
		String nomeFantasia = req.getParameter("nomeFantasia");
		String razaoSocial = req.getParameter("razaoSocial");
		String cnpj = req.getParameter("cnpj");
		String uf = req.getParameter("uf");
		
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.read(fornecedorId);

		fornecedor.setNomeFantasia(nomeFantasia);
		fornecedor.setRazaoSocial(razaoSocial);
		fornecedor.setCnpj(cnpj);
		fornecedor.setUf(uf);
		
		fornecedorDAO.update(fornecedor);

		resp.sendRedirect("/principal");
    }
}
