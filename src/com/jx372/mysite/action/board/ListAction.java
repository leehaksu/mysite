package com.jx372.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.Vo.BoardVo;
import com.jx372.mysite.dao.BoardDao;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("page");
		String b = request.getParameter("b");
		int firstNum = 1;
		int endNum = 0;
		int beforeNum = 0;
		int total_page;
		int limitNum;
		int Number = 0;
		if (pageNum == null) {
			beforeNum = 0;
			Number = 0;
			endNum = 5;
			limitNum = 0;
		} else {
			Number = Integer.parseInt(pageNum);
			System.out.println("firstNumber : " + firstNum);

			int subNum = 5 - firstNum;
			if (firstNum - subNum < 0) {
				beforeNum = 0;
			} else {
				beforeNum = firstNum - subNum;

			}
			endNum = firstNum + subNum;
		}
		/*
		 * if("next".equals(b)) { System.out.println("들어왔니?"); beforeNum =
		 * firstNum - 5; System.out.println("beforNum :: "+beforeNum); firstNum
		 * = firstNum + 5; System.out.println("firstNum :: "+firstNum);
		 * endNum=endNum+5; System.out.println("endNum :: "+endNum); }
		 */ if (Number == 0) {
			limitNum = 0;
		} else {
			limitNum = (Number - 1) * 5;
		}
		System.out.println("limitNum :: " + limitNum);
		System.out.println("beforeNumber : " + beforeNum);
		System.out.println("endNumber : " + endNum);
		total_page = new BoardDao().getSize();
		List<BoardVo> list = new BoardDao().getList(limitNum);
		request.setAttribute("list", list);
		request.setAttribute("firstNumber", firstNum);
		request.setAttribute("beforeNumber", beforeNum);
		request.setAttribute("endNumber", endNum);
		WebUtils.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
