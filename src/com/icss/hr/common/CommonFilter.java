package com.icss.hr.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

/**
 * ����������
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		// ת������
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		// ����������������ʵ�ǰ��Ŀ
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�������url
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//webӦ������
		String app = request.getContextPath();
		
		//�ж��û���¼״̬
		if ( !uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/logout.jsp")
				&& !uri.equals(app + "/LoginServlet")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/images") ) {
			
			HttpSession session = request.getSession();
			String empLoginName = (String) session.getAttribute("empLoginName");
						
			if (empLoginName == null) {
				
				//�жϵ�ǰ����������������ajax����
				String ajaxHeader = request.getHeader("x-requested-with");
				
				//�����ajax��������Ӧ�ı�ͷ������һ������ı�־���Ա���ǰ�����ж�
				if (ajaxHeader != null && ajaxHeader.equalsIgnoreCase("XMLHttpRequest")) {
					response.setHeader("sessionStatus", "timeout");
				} else {
					response.sendRedirect(app + "/logout.jsp");
				}	
				
				//ͳһ�ر�����
				DbUtil.close();
				
				return;
			}			
			
		}	

		//��������ִ��
		chain.doFilter(request, response);
		
		//ͳһ�ر�����
		DbUtil.close();
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}