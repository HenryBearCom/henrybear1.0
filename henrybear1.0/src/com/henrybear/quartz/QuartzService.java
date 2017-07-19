package com.henrybear.quartz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzService {

	private Logger log = Logger.getLogger("quartz");
	private boolean startOnload = true;
	private Map<String,Scheduler> scheduerMap ;
	
	public void init(){
		log.info("×¼±¸Æô¶¯quartz");
		scheduerMap = new HashMap<String,Scheduler>();
		String path = QuartzService.class.getResource("/").getPath();
		String resource = "E:/GitHub/201709B/henrybear1.0/WebRoot/config/quartz.properties";
		
		Scheduler scheduler = null;
		String schedulerName = null;
		FileInputStream ins = null;
		try {
			StdSchedulerFactory factory = new StdSchedulerFactory();
			ins = new FileInputStream(resource);
			factory.initialize(ins);
			scheduler = factory.getScheduler();
			if(startOnload){
				scheduler.start();
			}
			schedulerName = scheduler.getSchedulerName();
			scheduerMap.put(schedulerName, scheduler);
			log.info("Scheduler \""+schedulerName +"\" started successfully.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Initializing scheduler \"" + schedulerName +"\" failed.",e);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error("Initializing scheduler \"" + schedulerName +"\" failed.",e);
		}finally{
			if(ins != null){
				try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("QuartzService",e);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuartzService service = new QuartzService();
		service.init();
	}

}
