package com.icss.hr.emp.controller;

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
import com.google.gson.GsonBuilder;
import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * ��ѯԱ������
 */
@WebServlet("/QueryEmpServlet")
public class QueryEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// �����
		PrintWriter out = response.getWriter();
		
		//���ҳ��
		String pageNumStr = request.getParameter("pageNum");
		//���ÿҳ������
		String pageSizeStr = request.getParameter("pageSize");
		
		int pageNum = 1;//Ĭ�ϵ�һҳ
		int pageSize = 10;//Ĭ��ÿҳ10��
		
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (Exception e) {
			
		}
		
		try {
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e ) {
			
		}
		
		//����ҵ�����
		EmpService service = new EmpService();
				
		try {
			
			Pager pager = new Pager(service.getEmpCount(),pageSize,pageNum);
			List<Emp> list = service.queryEmpByPage(pager);	
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("list", list);
			
			Gson gson = new GsonBuilder()  
					  .setDateFormat("yyyy-MM-dd")  
					  .create(); 
			
			out.write( gson.toJson(map) );			
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}