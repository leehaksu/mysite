package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.web.action.Action;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String no = request.getParameter("no");
		String depth= request.getParameter("depth");
		int No = Integer.parseInt(no);
		int Depth = Integer.parseInt(depth);
		
		

	}

}
