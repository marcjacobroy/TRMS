package Tuition.dao;

import Tuition.pojos.Award;

public interface AwardDao {
	
	public void createAward(Award award);
	
	public Award readAward(int awardId);
	
	public void updateAward(int awardId, Award award);
	
	public void deleteAward(int awardId);

}
