package Tuition.service;

import java.util.List;

import Tuition.pojos.Award;

public interface AwardService {
	
	public void createAward(Award award);
	
	public Award readAward(int awardId);
	
	public void updateAward(int awardId, Award award);
	
	public void deleteAward(int awardId);

	public List<Award> readAwardsByEmployeeId(int employeeId);

}
