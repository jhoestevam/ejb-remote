package br.univel.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univel.dao.AlunoDao;
import br.univel.model.Aluno;

@WebServlet("/TesteInsert")
public class TesteInsert extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AlunoDao dao;
       
    public TesteInsert() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	
    	try(PrintWriter writer = response.getWriter();) {
    		writer.println("Inserindo 1000 registros");
    		for (int i = 0; i < 1000; i++) {
				Aluno aluno = new Aluno();
				
				aluno.setNome("aluno" + i);
				aluno.setTelefone("45-99906-0895" + i);
				
//				writer.println(aluno.toString());
				
				Aluno alunoRet = dao.salvar(aluno);
				writer.println("Inserido id:" + alunoRet.getId());
    		}
    		
    		writer.flush();
		} 
    	
    }
    
}
