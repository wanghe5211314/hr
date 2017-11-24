package com.icss.hr.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.analysis.service.AnaService;

/**
 * ͳ�Ʋ����������ݽӿ�
 */
@WebServlet("/QueryEmpCountServlet")
public class QueryEmpCountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//����ҵ�����
		AnaService service = new AnaService();
		
		try {
			List<HashMap<String, Object>> list = service.queryEmpCount();
			
			//ת��Ϊjson������Ӧ���ͻ���
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