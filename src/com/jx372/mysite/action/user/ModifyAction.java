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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
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
		
		UserVo vo = new UserVo();
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPassword(request.getParameter("password"));
		vo.setGender(request.getParameter("gender"));
		vo.setNo(authUser.getNo());
		
		new UserDao().update(vo);
		
		WebUtils.redirect("/mysite/main?a=loginform", request, response);

		

	}

}
