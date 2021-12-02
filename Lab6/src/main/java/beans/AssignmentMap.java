package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;


@Singleton
@Startup
public class AssignmentMap {
	
	private Map<String,List<String>> multiMap;
	
	@Resource
	private TimerService timerService;
	
	@PostConstruct
    public void init() {
		multiMap = new HashMap<>();
		System.out.println("works");
//	    timerService.createCalendarTimer(new ScheduleExpression().second("*/1")
//	            .minute("*").hour("*"));
	}
	
	public synchronized void add(String key, List<String> values) {
		if(multiMap.get(key) != null) {
			for(int i = 0; i < values.size(); i++)
			multiMap.get(key).add(values.get(i));
		}else {
			multiMap.put(key, new ArrayList<String>());
		}
	}
	
	@Schedule(minute="*", second = "*/10") 
	public synchronized void show() {
		for(int i = 0; i < multiMap.size(); i++) {
				for(int j = 0; j < multiMap.get(i).size(); j++) {
					System.out.println( multiMap.get(i).get(j));
				}
		}
		System.out.println("It works");
	}
}
