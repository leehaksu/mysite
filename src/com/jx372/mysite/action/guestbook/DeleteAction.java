package com.jx372.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.Vo.GuestBookVo;
import com.jx372.mysite.dao.GuestBookDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String passwd = WebUtils.checkParameter( request.getParameter( "password" ), "" );
		Long no = WebUtils.checkParameter( request.getParameter( "no" ), 0L );
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo( no );
		vo.setPasswd(passwd);
		new GuestBookDao().delete(vo);
		
		WebUtils.redirect( request.getContextPath() + "/guestbook", request, response);
	}
}
