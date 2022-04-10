package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Address;
import com.simplilearn.entity.Courses;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("add-student.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Student student = new Student();

		// Populate Student object
		populateStudent(request, student);

		// Step 2: Initialize hibernate to psersist object
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		session.save(student);
		tx.commit();

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Data successfully inserted in DB");
		out.println("<p> <a href='read-student'> Click Here </a> to show all student</p>");
		out.println("</body></html>");
	}

	private void populateStudent(HttpServletRequest request, Student student) {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		populatePhoneNumbers(request, student);
		populateCourseDetail(request, student);
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		
		Address addr = new Address();
		addr.setState(state);
		addr.setCity(city);
		addr.setStreet(street);
		
		student.setAddress(addr);
		
		student.setFname(fname);
		student.setLname(lname);
	}

	private void populateCourseDetail(HttpServletRequest request, Student student) {
		List<Student> students = new ArrayList<>();
		students.add(student);

		String courseName1 = request.getParameter("courses_1");
		String courseType1 = request.getParameter("courseType_1");
		Courses course1 = new Courses();
		course1.setCourseName(courseName1);
		course1.setCourseType(courseType1);
		course1.setStudents(students);

		String courseName2 = request.getParameter("courses_2");
		String courseType2 = request.getParameter("courseType_2");
		Courses course2 = new Courses();
		course2.setCourseName(courseName2);
		course2.setCourseType(courseType2);
		course2.setStudents(students);

		String courseName3 = request.getParameter("courses_3");
		String courseType3 = request.getParameter("courseType_3");
		Courses course3 = new Courses();
		course3.setCourseName(courseName3);
		course3.setCourseType(courseType3);
		course3.setStudents(students);

		List<Courses> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		student.setCourses(courses);
	}

	private void populatePhoneNumbers(HttpServletRequest request, Student student) {
		String phone1 = request.getParameter("phone_1");
		String type1 = request.getParameter("type1");

		String phone2 = request.getParameter("phone_2");
		String type2 = request.getParameter("type2");

		String phone3 = request.getParameter("phone_3");
		String type3 = request.getParameter("type3");

		PhoneNumber p1 = new PhoneNumber();
		p1.setPhoneNumber(phone1);
		p1.setPhoneType(type1);
		p1.setStudent(student);

		PhoneNumber p2 = new PhoneNumber();
		p2.setPhoneNumber(phone2);
		p2.setPhoneType(type2);
		p2.setStudent(student);

		PhoneNumber p3 = new PhoneNumber();
		p3.setPhoneNumber(phone3);
		p3.setPhoneType(type3);
		p3.setStudent(student);

		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
		phones.add(p1);
		phones.add(p2);
		phones.add(p3);
		student.setPhones(phones);
	}
}