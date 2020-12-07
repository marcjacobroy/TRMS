package Tuition.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import Tuition.pojos.Grade;
import Tuition.pojos.Request;
import Tuition.util.ConnectionUtil;

public class GradeDaoPostgres implements GradeDao {
	
private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createGrade(Grade grade) {
		
		String sql = "insert into grade (request_id, grade) values(?, ?)";
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, grade.getRequestId());
			stmt.setString(2,  grade.getGrade());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Grade readGrade(int gradeId) {
		String sql = "select * from grade where grade_id = ?";
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, gradeId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Grade grade = new Grade(rs.getInt("request_id"), rs.getString("grade"));
			grade.setGradeId(gradeId);
			return grade;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateGrade(Grade grade, int gradeId) {
		
		String sql = "update grade set request_id = ?, grade = ? where grade_id = ?";
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, grade.getRequestId());
			stmt.setString(2,  grade.getGrade());
			stmt.setInt(3,  gradeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteGrade(int gradeId) {
		String sql = "delete from grade where grade_id = ?";
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  gradeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Grade> readGradesFromEmployee(int employeeId) {
		
		String sql = "select g.* from grade g, request r, employee e where g.request_id = r.request_id and r.employee_id = e.employee_id and e.employee_id = ?";
		List<Grade> gradeList = new ArrayList<>();
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Grade g = makeGrade(rs);
				g.setGradeId(rs.getInt("grade_id"));
				gradeList.add(g);
			}
			return gradeList;
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Grade> readGradesFromDs(int dsId) {
		String sql = "select g.* from grade g, request r, employee e1, employee e2 where g.request_id = r.request_id and r.employee_id = e1.employee_id and e1.reports_to = e2.employee_id and e2.employee_id = ?";
		List<Grade> gradeList = new ArrayList<>();
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, dsId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Grade g = makeGrade(rs);
				g.setGradeId(rs.getInt("grade_id"));
				gradeList.add(g);
			}
			return gradeList;
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Grade> readGradesFromBenCo(int benCo) {
		String sql = "select g.* from grade g, request r, employee e1, employee e2 where g.request_id = r.request_id and r.employee_id = e1.employee_id and e1.ben_co = e2.employee_id and e2.employee_id = ?";
		List<Grade> gradeList = new ArrayList<>();
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, benCo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Grade g = makeGrade(rs);
				g.setGradeId(rs.getInt("grade_id"));
				gradeList.add(g);
			}
			return gradeList;
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}
	
	public Grade makeGrade(ResultSet rs) throws SQLException {
		
		int requestId = rs.getInt("request_id");
		String grade = rs.getString("grade");
		
		return new Grade(requestId, grade);
	}

}
