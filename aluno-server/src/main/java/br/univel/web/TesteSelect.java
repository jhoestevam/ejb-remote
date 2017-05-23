package br.univel.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.dao.AlunoDao;
import br.univel.model.Aluno;

@WebServlet("/TesteSelect")
public class TesteSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AlunoDao dao;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter writer = response.getWriter();) {
    		
    		writer.println("Listando todos os registros");
			
    		
    		List<Aluno> lista = dao.getTodosAlunos();
    		lista.forEach(e -> {
    			writer.println(e.toJson());
    			writer.println(',');
    		});
    		
    		writer.flush();
		}
    		
	}

}
