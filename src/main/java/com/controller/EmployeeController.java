package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.bean.Position;
import com.dao.EmployeeModel;


/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeModel empDao;
	
	public void init() {
		empDao = new EmployeeModel();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				displayForm(request, response);
				break;
			case "/insert":
				changeStateAdd(request, response);
				break;
			case "/delete":
				handDelete(request, response);
				break;
			case "/edit":
				changeStateEdit(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employee> listEmp = empDao.getListEmployee();
		request.setAttribute("listEmp", listEmp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void displayForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Position> listPos = empDao.getListPosition();
		request.setAttribute("listPos", listPos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void changeStateAdd(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String code=request.getParameter("code");
		String name = request.getParameter("name");
		String birthdayStr = request.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date birthday = null;
		try {
			birthday = sdf.parse(birthdayStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String position = request.getParameter("position");
		Employee newEmp = new Employee();
		newEmp.setCode(code);
		newEmp.setName(name);
		newEmp.setBirthday(birthday);
		newEmp.setGender(gender);
		newEmp.setAddress(address);
		newEmp.setPosition(position);
		empDao.addEmployee(newEmp);
		response.sendRedirect("list");
	}
	
	private void changeStateEdit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee existingEmp = empDao.selectEmployee(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("emp", existingEmp);
		dispatcher.forward(request, response);

	}
	
	private void handDelete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		empDao.removeEmployee(id);
		response.sendRedirect("list");

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
