package Tuition.controller;

import Tuition.pojos.Grade;
import Tuition.service.GradeService;
import Tuition.service.GradeServiceFullStack;
import io.javalin.http.Context;

public class GradeController {
	
	GradeService gradeService = new GradeServiceFullStack();
	
	
	public void createGrade(Context ctx) {
		
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		String grade = ctx.formParam("grade");
		
		try {
			gradeService.createGrade(new Grade(requestId, grade));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readGrade(Context ctx) {
		
		int gradeId = Integer.parseInt(ctx.formParam("gradeId"));
		
		try {
			ctx.json(gradeService.readGrade(gradeId));
		} catch(Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
	
	public void updateGrade(Context ctx) {
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		String grade = ctx.formParam("grade");
		int gradeId = Integer.parseInt(ctx.formParam("gradeId"));
		
		try {
			gradeService.updateGrade(new Grade(requestId, grade), gradeId);
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
		
	public void deleteGrade(Context ctx) {
		int gradeId = Integer.parseInt(ctx.formParam("gradeId"));
		try {
			gradeService.deleteGrade(gradeId);
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readGradesFromEmployee(Context ctx) {
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		try {
			ctx.json(gradeService.readGradesFromEmployee(employeeId));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readGradesFromDs(Context ctx) {
		int dsId = Integer.parseInt(ctx.formParam("employeeId"));
		try {
			ctx.json(gradeService.readGradesFromDs(dsId));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readGradesFromBenCo(Context ctx) {
		int benCo = Integer.parseInt(ctx.formParam("employeeId"));
		try {
			ctx.json(gradeService.readGradesFromBenCo(benCo));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
		}
	}

}
