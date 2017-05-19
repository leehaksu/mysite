package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.Vo.UserVo;
import com.jx372.mysite.dao.UserDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1. Session uservo가 유효한지 확인
		
		HttpSession session = request.getSession();
		if(session==null)
		{
			WebUtils.redirect("/mysite/user", request, response);
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser==null)
		{
			WebUtils.redirect("/mysite/user", request, response);
		}
		
		Long no = authUser.getNo();
		System.out.println(no);
		UserVo vo=new UserDao().get(no);
		request.setAttribute("no", vo.getNo());
		request.setAttribute("email", vo.getEmail());
		request.setAttribute("gender", vo.getGender());
		WebUtils.forward("/WEB-INF/views/user/modifyform.jsp", request, response);
		

	}

}
