package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ���ݲ���id��ò������ݿ�����
 */
@WebServlet("/GetDeptServlet")
public class GetDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��ò���id
		String deptId = request.getParameter("deptId");
		
		//����ҵ�����
		DeptService service = new DeptService();
		
		try {			
			Dept dept = service.queryDeptById(Integer.parseInt(deptId));
			
			//��request��Χ�д洢����
			request.setAttribute("dept", dept);
			//ת����JSP��ͼ
			request.getRequestDispatcher("/UpdateDept.jsp").forward(request, response);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}