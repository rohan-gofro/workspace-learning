package job.scheduling;


import java.util.concurrent.ScheduledFuture;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;


public abstract class ScheduleJob implements Runnable
{
	public Config config;
	@SuppressWarnings("rawtypes")
	private ScheduledFuture future;
	private TaskScheduler scheduler;

	public ScheduleJob(Config config)
	{
		this.config = config;
	}
	
	final public void doJob()
	{
		
		if(scheduler == null)
		{
			this.scheduler = new ConcurrentTaskScheduler();
		}
		if(future!=null)
		{
			future.cancel(true);
		}
		future = this.scheduler.schedule(this, new CronTrigger(config.getCron()));
	}

}