package com.servlets;

import java.io.IOException;
import java.util.Date;

import org.hibernate. *;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.entities.Note;
import com.helper.FactoryProvider;
/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int noteId = Integer.parseInt(request.getParameter("noteId").trim());
		Session s= FactoryProvider.getFactory().openSession();
		Transaction tx=s.beginTransaction();
		Note note = (Note)s.get(Note.class, noteId);
		
		note.setTitle(title);
		note.setContent(content);
		note.setAddedDate(new Date());
		
		tx.commit();
		s.close();
		response.sendRedirect("all_notes.jsp");
		
	}
		catch(Exception e) {
			e.printStackTrace();	}		
	}

}
