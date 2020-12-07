package Tuition.service;

import java.util.List;

import Tuition.dao.GradeDao;
import Tuition.dao.GradeDaoPostgres;
import Tuition.pojos.Grade;

public class GradeServiceFullStack implements GradeService {

	GradeDao gradeDao = new GradeDaoPostgres();
	
	@Override
	public void createGrade(Grade grade) {
		gradeDao.createGrade(grade);

	}

	@Override
	public Grade readGrade(int gradeId) {
		return gradeDao.readGrade(gradeId);
	}

	@Override
	public void updateGrade(Grade grade, int gradeId) {
		gradeDao.updateGrade(grade, gradeId);
	}

	@Override
	public void deleteGrade(int gradeId) {
		gradeDao.deleteGrade(gradeId);

	}

	@Override
	public List<Grade> readGradesFromEmployee(int employeeId) {
		return gradeDao.readGradesFromEmployee(employeeId);
	}

	@Override
	public List<Grade> readGradesFromDs(int dsId) {
		return gradeDao.readGradesFromDs(dsId);
	}

	@Override
	public List<Grade> readGradesFromBenCo(int benCo) {
		return gradeDao.readGradesFromBenCo(benCo);
	}

}
