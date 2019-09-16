package mw.visitsbooking.service;

import java.util.List;

import mw.visitsbooking.entity.Log;

public interface LogService {
	
	public List<Log> getLogs();
	
	public Log getLog(int id);
	
	public void saveLog(Log log);

}
