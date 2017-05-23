package com.jx372.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.Vo.GuestBookVo;
import com.jx372.mysite.dao.GuestBookDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestBookVo> list = new GuestBookDao().getList();
		request.setAttribute( "list", list );
		WebUtils.forward( "/WEB-INF/views/guestbook/list.jsp", request, response);
	}

}
