package Tuition.service;

import java.util.List;

import Tuition.pojos.Grade;

public interface GradeService {
	
	public void createGrade(Grade grade);
	
	public Grade readGrade(int gradeId);
	
	public void updateGrade(Grade grade, int gradeId);
	
	public void deleteGrade(int gradeId);
	
	public List<Grade> readGradesFromEmployee(int employeeId);
	
	public List<Grade> readGradesFromDs(int dsId);
	
	public List<Grade> readGradesFromBenCo(int benCo);

}
