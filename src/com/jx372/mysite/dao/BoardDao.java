package com.jx372.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.mysite.Vo.BoardVo;

public class BoardDao extends ConnectionDao {

	public int getSize() {
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select count(*) from board;";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
				if (count % 5 == 0) {
					return count / 5;
				} else {
					return (count / 5) + 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;

	}

	public List<BoardVo> getList(int page) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select a.no,a.title,a.hit, b.name, date_format(a.regDate,'%Y-%m-%d %T'),a.user_no,a.depth "
					+ "from board as a ,user as b " + "where a.user_no=b.no " + "order by a.no desc " + "limit ? ,5; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setHit(rs.getInt(3));
				vo.setWriter(rs.getString(4));
				vo.setRegDate(rs.getString(5));
				vo.setUser_no(rs.getLong(6));
				vo.setDepth(rs.getInt(7));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(list);

		return list;

	}

	public List<BoardVo> get(int no) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select group_no,order_no depth from board where no=?;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setGroup_no(rs.getInt(1));
				vo.setOrder_no(rs.getInt(2));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(list);

		return list;
	}

	public boolean insert(BoardVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			System.out.println("depth 값 체크 : " + vo.getDepth());
			if (vo.getDepth()!=-1) {
				String sql = "insert into board values(null,?,?,now(),0,?,?,?,?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getGroup_no());
				pstmt.setInt(4, vo.getOrder_no());
				pstmt.setInt(5, vo.getDepth()+1);
				pstmt.setLong(6, vo.getUser_no());

			} else {
				String sql = "insert into board values(null,?,?,now(),0,(select ifnull(max(group_no),0)+1 from board a),1,0,?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getUser_no());
			}
			System.out.println(pstmt);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count == 1;

	}
	public boolean update(BoardVo vo) {
		// TODO Auto-generated method stub

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set order_no = order_no + 1 "
					+ "where group_no = ? and order_no > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getGroup_no());
			pstmt.setInt(1, vo.getOrder_no());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count == 1;
	}

	public boolean update(Long boardNo) {
		// TODO Auto-generated method stub

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set hit=hit+1 where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count == 1;
	}

	public boolean update(String title, String content, Long boardNo) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set title=?, content=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, boardNo);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count == 1;
	}

	public BoardVo select(Long boardNo) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select title,content,group_no,order_no from board where no=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				vo.setGroup_no(rs.getInt(3));
				vo.setOrder_no(rs.getInt(4));
				return vo;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
		// TODO Auto-generated method stub

	}

	public boolean delete(Long no) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "delete from board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count == 1;

	}

}
