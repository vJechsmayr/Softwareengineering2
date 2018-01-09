package figures;

/**
 * Vorgabe
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thomas Wuerthinger
 */
public class Controller {

	public static final int FRAMES_PER_SECOND = 25;

	/**
	 * Adds a new animation to the list of animations. Must be called before
	 * {@link #display()}.
	 * 
	 * @param a
	 *            the added animation
	 */
	public static void addAnimation(Animation a) {
		checkState();
		singleton.animations.add(a);
	}

	/**
	 * Adds a new figure to the list of figures. Must be called before
	 * {@link #display()}.
	 * 
	 * @param f
	 *            the added figure
	 */
	public static void addFigure(Figure f) {
		checkState();
		singleton.figures.add(f);
	}

	/**
	 * Starts a new thread that displays the figures and performs the
	 * animations. Must not be called more than once.
	 */
	public static void display() {
		checkState();
		started = true;
		new Thread(singleton.new Animator()).start();
	}

	private static void checkState() {
		if (started) {
			throw new IllegalStateException("This method can only be called before starting the animation!");
		}
	}

	private static boolean started = false;
	private List<Animation> animations = new ArrayList<Animation>();
	private List<Figure> figures = new ArrayList<Figure>();

	private Controller() {
	}

	private static Controller singleton = new Controller();

	private class Animator implements Runnable {

		int frame = 0;

		long start = System.currentTimeMillis();

		public void run() {

			while (true) {

				Window.clear();

				for (Figure f : figures) {
					f.draw(0, 0);
				}

				for (Animation a : animations) {
					a.animate(frame);
				}

				frame++;

				double millisecondsPerFrame = 1000.0d / (double) FRAMES_PER_SECOND;
				long destinationTime = (long) (frame * millisecondsPerFrame);
				long milliseconds = System.currentTimeMillis() - start;
				long offset = destinationTime - milliseconds;

				// Are we behind in time?
				if (offset < 0) {

					offset = 0;

					// Give some time for warm-up
					if (frame > 10) {
						System.out.println("Warning: Frame rate of " + FRAMES_PER_SECOND + " could not be reached!");
					}
				}

				// Wait to slow down animation to frame rate.
				try {
					Thread.sleep(offset);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void displaySorted(Comparator<Figure> comparator) {
		checkState();
		started = true;
		List<Figure> sorted = singleton.figures.stream().sorted(comparator).collect(Collectors.toList());
		for (int i = 0; i < sorted.size(); i++) {
			sorted.get(i).draw(i * 150, 100);
			 System.out.println("Area: " + sorted.get(i).getArea());
		}
	}

}
