package mw.visitsbooking.dao;

import java.util.List;

import mw.visitsbooking.entity.Log;

public interface LogDAO {
	
	public List<Log> getLogs();
	
	public Log getLog(int id);
	
	public void saveLog(Log log);

}
