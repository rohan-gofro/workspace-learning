package scheduling.jobs;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class Schedule implements Runnable{

	@SuppressWarnings("rawtypes")
	private static ScheduledFuture future;
	TaskScheduler scheduler;
	String cronexp;
	public void reSchedule(String cron)
	{
		this.cronexp = cron;
		if(scheduler == null)
		{
			this.scheduler = new ConcurrentTaskScheduler();
		}
		if(future!=null)
		{
			future.cancel(true);
		}
		future = this.scheduler.schedule(this, new CronTrigger(cron));
	}
	
	public void run() {
		
		System.out.println("Task run at: " + new Date().toString());
		
	}
	
	/*
	public void initializeScheduler()
	{
		System.out.println("Reschudle");
		this.reSchedule(cronexp);
	}
	*/
	public static void main(String[] args) {
		Schedule obj = new Schedule();
		obj.reSchedule("0/10 * * * * ?");
		new Schedule().reSchedule("0/5 * * * * ?");
		System.out.println("All jobs scheduled");
	}
}
