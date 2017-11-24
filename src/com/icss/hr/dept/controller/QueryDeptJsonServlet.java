package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ��ѯ����json���ݽӿ�
 */
@WebServlet("/QueryDeptJsonServlet")
public class QueryDeptJsonServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�����
		PrintWriter out = response.getWriter();
		
		//����ҵ�����
		DeptService service = new DeptService();
		
		try {
			List<Dept> list = service.queryDept();
			
			//ת��Ϊjson
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}