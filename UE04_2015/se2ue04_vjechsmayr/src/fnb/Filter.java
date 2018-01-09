package fnb;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Filter<T> implements Receiver<T>{

	private final Consumer<T> onReceive;
	private final Consumer<T> onSend;
	private final Predicate<T> filter;
	
	private Receiver<T> next;
	
	public Filter(Predicate<T> filter, Consumer<T> onReceive, Consumer<T> onSend)
	{
		this.filter = filter;
		this.onReceive = onReceive;
		this.onSend = onSend;
	}
	
	public void setNext(Receiver<T> next) {
		this.next = next;
	}

	@Override
	public void receive(T a) {
		if(next != null)
		{
			onReceive.accept(a);
			if(filter.test(a))
			{
				onSend.accept(a);
				next.receive(a);
			}
		}
	}
}