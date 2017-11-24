package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * ���ݵ�ǰ��¼��������ͷ��base64����
 */
@WebServlet("/GetEmpPicServlet")
public class GetEmpPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		// ��õ�ǰ�û��ĵ�¼��
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		//����ҵ�����
		EmpService service = new EmpService();
		
		try {
			Emp emp = service.queryEmpByLoginName(empLoginName);
			
			System.out.println(emp.getEmpPic());
			
			//���ͷ������
			out.print(emp.getEmpPic());
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}