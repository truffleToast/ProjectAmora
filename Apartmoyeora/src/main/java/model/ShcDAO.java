package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class ShcDAO {
	
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs= null;
	int cnt=0;
	String un="";
	
	
	
	
	
	
	// 대댓글 처리를 위한 공백개수 정하기
	public String makeGongback(int dadat) {
	    String gongback = "   "; // 3칸 짜리 공백
	    if (dadat > 0) {
	        gongback = gongback.repeat(dadat - 1);
	    } else {
	        gongback = "";
	    }
	    return gongback;
	}

	
	//데이터베이스 연결메소드
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
