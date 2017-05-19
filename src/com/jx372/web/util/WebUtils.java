package com.jx372.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	public static void redirect(String url,HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		
		response.sendRedirect(url);
		
		
	}
	public static void forward(String path,HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
