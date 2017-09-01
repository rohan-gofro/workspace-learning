package apring.cron;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringScheduleDemo {
	
 public void runit() {
 ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
 }
 
 public static void main(String[] args) {
	SpringScheduleDemo obj = new SpringScheduleDemo();
	obj.runit();
}
}