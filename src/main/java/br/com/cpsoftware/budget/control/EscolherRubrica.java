package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Rubrica;

@SuppressWarnings("serial")
public class EscolherRubrica extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		
		List <Map<Object, Object>> rubricasMaps = new ArrayList<Map<Object, Object>>();
		
		for(Rubrica rubrica : new RubricaDAO().getRubricas(categoriaId)) {
			Map<Object, Object> rubricaMap = new HashMap<>();
			rubricaMap.put("id", rubrica.getId());
			rubricaMap.put("codigo", rubrica.getCodigo());
			rubricaMap.put("nome", rubrica.getNome());
			rubricasMaps.add(rubricaMap);
		}
		
		String json = new Gson().toJson(rubricasMaps);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}
	
}
