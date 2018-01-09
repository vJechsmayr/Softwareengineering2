package fnb;

import java.util.function.Consumer;

public class Sink<T> implements Receiver<T>{
	
	private final Consumer<T> consumer;

	public Sink (Consumer<T> consumer)
	{
		this.consumer = consumer;
	}

	@Override
	public void receive(T a) {
		consumer.accept(a);
	}
}
