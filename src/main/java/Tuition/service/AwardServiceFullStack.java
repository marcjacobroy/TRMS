package Tuition.service;

import org.apache.log4j.Logger;

import Tuition.dao.AwardDao;
import Tuition.dao.AwardDaoPostgres;
import Tuition.pojos.Award;

public class AwardServiceFullStack implements AwardService {

	AwardDao awardDao = new AwardDaoPostgres();
	private static Logger log = Logger.getRootLogger();

	
	@Override
	public void createAward(Award award) {
		log.trace("Calling createAward in AwardServiceFullStack on " + award);
		awardDao.createAward(award);

	}

	@Override
	public Award readAward(int awardId) {
		log.trace("Calling readAward in AwardServiceFullStack on " + awardId);
		return awardDao.readAward(awardId);
	}

	@Override
	public void updateAward(int awardId, Award award) {
		log.trace("Calling updateAward in AwardServiceFullStack on " + award + " and " + awardId);
		awardDao.updateAward(awardId, award);

	}

	@Override
	public void deleteAward(int awardId) {
		log.trace("Calling deleteAward in AwardServiceFullStack on " + awardId);
		awardDao.deleteAward(awardId);

	}

}
