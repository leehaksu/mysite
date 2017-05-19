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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email =request.getParameter("email");
		String password=request.getParameter("password");
		
		System.out.println("email : " +email + "password : " + password);
		UserVo vo = new UserDao().get(email,password);
		if(vo ==null)
		{
			String fail="fail";
			request.setAttribute("result", fail);
			WebUtils.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}else
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("authUser", vo);
			System.out.println("인증 완료");	
			WebUtils.redirect("/mysite/main", request, response);
		}
		

	}

}
