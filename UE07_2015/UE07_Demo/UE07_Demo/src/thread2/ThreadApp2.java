package thread2;

public class ThreadApp2 {
	
	static class Producer extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("MyThread " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class ProducerRunner implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("MyRunnable " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new Producer();
		thread1.setPriority(10);
		Thread thread2 = new Thread(new ProducerRunner());
		thread2.setPriority(10);
		thread1.start();
		thread2.start();
		System.out.println("End of Application");

	}
}
