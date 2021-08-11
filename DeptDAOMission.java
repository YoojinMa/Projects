package step03.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;

public class DeptDAOMission {
	
//	1. 모든 사원의 급여 총합
	public static int salAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int sal = 0;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sal from emp2");
			rset = pstmt.executeQuery();

			if (rset.next()) {
				sal += rset.getInt("sal");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pstmt, rset);
		}

		return sal;
	}
	
	
	
//	2. comm 받는 사원 수
	public static int countComm() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int countComm = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select count(comm) from emp2");
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				countComm = rset.getInt(1);
			}
				
		}finally {
			DBUtil.close(con,  pstmt, rset);
		}
		
		return countComm;
	}
	
	
	
//	3. 부서번호가 10번인 부서의 사원 이름
	public static ArrayList<String> deptnoEname() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<String> ename = new ArrayList<>();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select ename from emp2 where deptno = 10");
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ename.add(rset.getString("ename"));
			}
			
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return ename;
	}
	
	
	
//	4. 상사의 사번이 7,698인 사원 이름
	public static ArrayList<String> mgrEname() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<String> ename = new ArrayList<>();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select ename from emp2 where mgr=7698");
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ename.add(rset.getString("ename"));
			}
			
		}finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return ename;
	}
	

	
	public static void main(String[] args) {
		System.out.println("-----1. 모든 사원의 급여 총합");
		System.out.println(salAll());
		System.out.println("\n-----2. 보너스 받는 사원 수");
		try {
			System.out.println(countComm());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n-----3. 부서번호가 10번인 부서의 사원 이름");
		try {
			System.out.println(deptnoEname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n-----4. 상사의 사번이 7,698인 사원 이름");
		try {
			System.out.println(mgrEname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
