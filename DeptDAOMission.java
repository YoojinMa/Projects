package step03.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;

public class DeptDAOMission {
	
//	1. ��� ����� �޿� ����
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
	
	
	
//	2. comm �޴� ��� ��
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
	
	
	
//	3. �μ���ȣ�� 10���� �μ��� ��� �̸�
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
	
	
	
//	4. ����� ����� 7,698�� ��� �̸�
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
		System.out.println("-----1. ��� ����� �޿� ����");
		System.out.println(salAll());
		System.out.println("\n-----2. ���ʽ� �޴� ��� ��");
		try {
			System.out.println(countComm());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n-----3. �μ���ȣ�� 10���� �μ��� ��� �̸�");
		try {
			System.out.println(deptnoEname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n-----4. ����� ����� 7,698�� ��� �̸�");
		try {
			System.out.println(mgrEname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
