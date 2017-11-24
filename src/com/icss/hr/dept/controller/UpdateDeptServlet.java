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
 * �޸Ĳ��ſ�����
 */
@WebServlet("/UpdateDeptServlet")
public class UpdateDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�����������
		request.setCharacterEncoding("utf-8");
		//������Ӧ����
//		response.setCharacterEncoding("utf-8"); //��Ҫ������ֶ��л�����
		//������Ӧ��MIME���ͺͱ���
		response.setContentType("text/html;charset=utf-8");
		
		//��Ӧ�����
		PrintWriter out = response.getWriter();
		
		//����������
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//��װΪpojo����
		Dept dept = new Dept(Integer.parseInt(deptId),deptName,deptLoc);
		
		//����ҵ�����Ĺ���
		DeptService service = new DeptService();
		
		try {
			service.updateDept(dept);
			
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