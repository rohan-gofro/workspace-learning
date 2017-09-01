package job.scheduling;

import data.ComparisonReportGenerator;

public class ScheduleMyJob extends ScheduleJob
{

	public ScheduleMyJob(Config config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public void run() 
	{
			try {
				ComparisonReportGenerator.docomparison();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong");
				e.printStackTrace();
				System.exit(1);
			}
	}
}