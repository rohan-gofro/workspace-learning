package thread1;

public class Thread2 extends Thread {

	String name;
	int time;

	public Thread2(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public void run() {
		System.out.println("Thread 2 parent "+ Thread.currentThread().getThreadGroup());
		for (int i = 0; i < 3; i++) {
			System.out.println(this.name);
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
