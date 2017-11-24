package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ���Ӳ��ſ�����
 */
@WebServlet("/AddDeptServlet")
public class AddDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		//��Ӧ�����
		PrintWriter out = response.getWriter();
		
		//����������
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//��װΪpojo����
		Dept dept = new Dept(deptName,deptLoc);
		
		//����ҵ�����Ĺ���
		DeptService service = new DeptService();
		
		try {
			service.addDept(dept);
			
			//�ض��򵽲�ѯ
			response.sendRedirect("QueryDeptServlet");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}