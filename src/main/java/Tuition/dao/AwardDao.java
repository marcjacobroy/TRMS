package Tuition.dao;

import java.util.List;

import Tuition.pojos.Award;

public interface AwardDao {
	
	public void createAward(Award award);
	
	public Award readAward(int awardId);
	
	public void updateAward(int awardId, Award award);
	
	public void deleteAward(int awardId);

	public List<Award> readAwardsByEmployeeId(int employeeId);

	public Award readAwardOfRequest(int requestId);

}
