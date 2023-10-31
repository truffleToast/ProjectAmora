package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs= null;
	int cnt=0;
	String un="";
	
	//커뮤니티 신고 번호 조회 메서드
	public int getComReport_num(ComReportDTO dto) {
		getConnection();
		cnt = 0;
		String sql = "SELECT Report_NUM FROM COMReport WHERE COM_NUM =? AND ID =? ";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, dto.getComNum());
			psmt.setString(2, dto.getId());
			rs =psmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
		
	}
	
	
	// 커뮤니티 신고 행 삭제 메서드
	public int ComUnReport(int comLikeNum) {
		getConnection();
		String sql = "DELETE FROM COMREPORT WHERE COMREPORT_NUM=?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, comLikeNum);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	// 신고 버튼 누르면  커뮤니티 신고 행 삽입 시켜주는 메서드
	public int ComReport(ComReportDTO cldto) {
		getConnection();
		String sql = "INSERT INTO COMREPORT VALUES(COMREPORT_SEQ.NEXTVAL, ?,?)";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, cldto.getId());
			psmt.setInt(2, cldto.getComNum());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}

	// 공동구매 신고 행 삭제 메서드
	public int GbUnReport(int gbLikeNum) {
		getConnection();
		String sql = "DELETE FROM GBREPORT WHERE GBREPORT_NUM=?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, gbLikeNum);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 공동구매 신고 행 삽입 메서드
	public int GbReport(GbReportDTO gldto) {
		getConnection();
		String sql = "INSERT INTO COMLIKE VALUES(GBLIKE_SEQ.NEXTVAL, ?,?)";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, gldto.getId());
			psmt.setInt(2, gldto.getGbNum());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	
	
	// 중고거래 신고 행 삭제 메서드
	public int ShUnReport(int shLikeNum) {
		getConnection();
		String sql = "INSERT INTO SHREPORT VALUES(GBLIKE_SEQ.NEXTVAL, ?,?)";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, shLikeNum);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	// 중고거래 신고 행 삭제 메서드
	public int ShReport(ShReportDTO sldto) {
		getConnection();
		String sql = "INSERT INTO SHREPORT VALUES(GBLIKE_SEQ.NEXTVAL, ?,?)";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, sldto.getId());
			psmt.setInt(2, sldto.getShNum());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	// 댓글 작성이나 특정 행동후 실시간 업데이트를 위한 신고 개수 가져오기  
	public int getLikes(int comNum) {
		getConnection();
		int comLike = 0;
		String sql = "SELECT COM_REPORT FROM COMMUNITY WHERE COM_NUM = ?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, comNum);
			rs= psmt.executeQuery();
			if(rs.next()) {
				comLike =rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return comLike;
	}
	// 커뮤니티에서 신고 눌럿는지 확인하는 메서드 CHAT GPT피셜
	public boolean isUserReported(String userId, int comNum) {
	    getConnection();
	    boolean isLiked = false;
	    String sql = "SELECT * FROM COMREPORT WHERE COM_NUM = ? AND ID = ?";
	    try {
	        psmt = conn.prepareStatement(sql);
	        psmt.setInt(1, comNum);
	        psmt.setString(2, userId);
	        rs = psmt.executeQuery();
	        if (rs.next()) {
	            isLiked = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    return isLiked;
	}

	
	 // 데이터 베이스 연결
	 public void getConnection() {
			
		    try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		    String db_url = "jdbc:oracle:thin:@project-db-stu3.smhrd.com:1524:xe";
		    String db_id = "Insa4_Spring_hacksim_3";
		    String db_pw = "aishcool3";
		    conn = DriverManager.getConnection(db_url, db_id, db_pw);
		    } catch (ClassNotFoundException e) {	
				e.printStackTrace();
				System.out.println("ojdbc6.jar 또는 경로를 체크하세요!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DB연결에 필요한 정보가 맞는지 체크하세요!");
			}
		}
		//데이터베이스 종료메소드
		public void close() {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(psmt != null){
			         psmt.close();
			      }
			      if(conn != null){
			         conn.close();
			      }
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	 
}
