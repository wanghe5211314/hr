package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ��ѯ���ſ�����
 */
@WebServlet("/QueryDeptServlet")
public class QueryDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//����ҵ�����
		DeptService service = new DeptService();
		
		try {
			List<Dept> list = service.queryDept();
			
			//�����ݴ洢��request��Χ
			request.setAttribute("list", list);
			
			//����ת����JSP��ͼ
			request.getRequestDispatcher("/QueryDept.jsp").forward(request, response);
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
			//�ֶ�ת����������ʾҳ
			request.getRequestDispatcher("/error.html").forward(request, response);
		}		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}