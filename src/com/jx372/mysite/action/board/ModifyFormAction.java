package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.Vo.BoardVo;
import com.jx372.mysite.Vo.UserVo;
import com.jx372.mysite.dao.BoardDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session==null)
		{
			WebUtils.redirect("/mysite/main", request, response);
			return;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser==null)
		{
			WebUtils.redirect("/mysite/main", request, response);
		}
		String no = request.getParameter("no");
		Long boardNo= Long.parseLong(no);
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.select(boardNo);
		request.setAttribute("title", vo.getTitle());
		request.setAttribute("content", vo.getContent());
		request.setAttribute("no", no);
		WebUtils.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}
