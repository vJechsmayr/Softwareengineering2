package fnb;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Source<A> {

	private Receiver<A> next;

	private final Supplier<A> generator;
	private final Consumer<A> onSend;

	public Source(Supplier<A> generator, Consumer<A> onSend) {
		this.generator = generator;
		this.onSend = onSend;
	}

	public void setNext(Receiver<A> next) {
		this.next = next;
	}

	public void generate() {
		A a = generator.get();
		onSend.accept(a);
		if (next != null) {
			next.receive(a);
		}
	}
}
