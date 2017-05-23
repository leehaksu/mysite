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

public class WriteAction implements Action {

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
			return;
		}
		BoardVo vo = new BoardVo();
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setUser_no(authUser.getNo());
		String depth=(request.getParameter("depth"));
		String no =(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		if(depth!=null)
		{
			Long No= Long.parseLong(no);
			vo.setDepth(Integer.parseInt(depth));
			System.out.println("depth 체크 : "+ depth);
			BoardVo vo1=dao.select(No);
			dao.update(vo1);
		}
		else
		{
			vo.setDepth(-1);
		}
		dao.insert(vo);	
		WebUtils.redirect("/mysite/board", request, response);
	}

}
