package thread1;

public class ThreadApp1 {

	public static void main(String[] args) {

		System.out.println("Current Thread: "
				+ Thread.currentThread().getName());
		System.out.println(System.currentTimeMillis());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println(System.currentTimeMillis());
		System.out.println("End of Application");
	}
}
