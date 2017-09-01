package thread1;

public class Thread1 extends Thread {

	String name;
	String time;

	public Thread1(String name, String time) {
		this.name = name;
		this.time = time;
	}

	public void run() {
		String[] names = name.split(",");
		String[] times = time.split(",");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread 1 parent " + Thread.currentThread().getThreadGroup());

		new Thread2(names[0], Integer.parseInt(times[0])).start();
		new Thread2(names[1], Integer.parseInt(times[1])).start();
	}

	public static void main(String[] args) {

		Thread1 obj1 = new Thread1("Rohan,Karan", "1000,5000");
		System.out.println("Thread 0 parent " + Thread.currentThread().getThreadGroup());
		obj1.start();
		System.out.println("done");
		try {
			 obj1.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exit");
	}

}
