package mw.visitsbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mw.visitsbooking.dao.LogDAO;
import mw.visitsbooking.entity.Log;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDAO logDAO;
	
	@Override
	@Transactional
	public List<Log> getLogs() {
		
		return logDAO.getLogs();
	}

	@Override
	@Transactional
	public Log getLog(int id) {
		
		return logDAO.getLog(id);
	}

	@Override
	@Transactional
	public void saveLog(Log log) {
		
		logDAO.saveLog(log);
	}

}
