package com.icss.hr.emp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.emp.service.EmpService;

/**
 * ɾ��Ա�����ݿ�����
 */
@WebServlet("/DelEmpServlet")
public class DelEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//���Ա��id
		String empId = request.getParameter("empId");
		
		//����ҵ����
		EmpService service = new EmpService();
		
		try {
			service.deleteEmp(Integer.parseInt(empId));			
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}