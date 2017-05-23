package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.Vo.BoardVo;
import com.jx372.mysite.dao.BoardDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String no = request.getParameter("no");
		String depth = request.getParameter("depth");
		System.out.println(depth);
		Long boardNo= Long.parseLong(no);
		BoardDao dao = new BoardDao();
		dao.update(boardNo);
		BoardVo vo = dao.select(boardNo);
		request.setAttribute("title", vo.getTitle());
		request.setAttribute("content", vo.getContent());
		request.setAttribute("no", no);
		request.setAttribute("depth", depth);
		WebUtils.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
