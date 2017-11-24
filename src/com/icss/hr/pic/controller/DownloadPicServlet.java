package com.icss.hr.pic.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * ���ؿ�����
 */
@WebServlet("/DownloadPicServlet")
public class DownloadPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ͼƬid
		String picId = request.getParameter("picId");

		// ����ҵ�����
		PicService service = new PicService();

		try {
			Pic pic = service.queryPicById(Integer.parseInt(picId));
			
			//�ļ���������ת��
			String fileName = pic.getPicName();
			fileName = new String(fileName.getBytes(),"iso-8859-1");
			
			// ������Ӧ��ͷ��֪ͨ������Ը�����ʽ�������ݣ����أ�
			response.setHeader("content-disposition", "attachment;filename=" + fileName);

			//������
			InputStream fis = pic.getPicData();

			// �����
			OutputStream out = response.getOutputStream();

			byte[] b = new byte[1024 * 8];

			int len = fis.read(b);

			while (len != -1) {
				out.write(b, 0, len);
				len = fis.read(b);
			}

			out.close();
			fis.close();
			
		} catch (NumberFormatException | SQLException e) {
			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}