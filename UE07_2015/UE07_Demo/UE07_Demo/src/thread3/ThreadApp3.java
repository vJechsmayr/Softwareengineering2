package thread3;

public class ThreadApp3 {
	
	static class Producer extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 4; i++) {
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
			for (int i = 0; i < 6; i++) {
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
		thread1.start();
		Thread thread2 = new Thread(new ProducerRunner());
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
		}
		System.out.println("End of Application");

	}
}
