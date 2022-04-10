package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class ReadStudentServlet
 */
@WebServlet("/read-student")
public class ReadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadStudentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<html><body><h1>Student list:-</h1>");
		out.println("<style> table,td,th { border:2px solid green; padding:10px; }</style>");
		
		out.println("<table>");
		
		out.println("<tr>");
		out.println("<th> Student Id </th>");
		out.println("<th> Student First Name </th>");
		out.println("<th> Student Last Name </th>");
		out.println("<th> Student PhoneNumbers </th>");
		out.println("<th> Student Courses </th>");
		out.println("<th> Student Address </th>");
		out.println("</tr>");
		
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		
		List<Student> students = session.createQuery("from Student").list();
		if(students !=null && students.size()>0) {
			for(Student student: students) {
				out.println("<tr>");
				out.println("<td>"+student.getStudent_id()+"</td>");
				out.println("<td>"+student.getFname()+"</td>");
				out.println("<td>"+student.getLname()+"</td>");
				out.println("<td>"+student.getPhones()+"</td>");
				out.println("<td>"+student.getCourses()+"</td>");
				out.println("<td>"+student.getAddress()+"</td>");
				out.println("</tr>");	
				
			}
		}
		out.println("</table></body></html>");
		out.close();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
