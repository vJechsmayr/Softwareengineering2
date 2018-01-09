package fnb;

import java.util.function.Consumer;
import java.util.function.Function;

public class Transform<T,R>  implements Receiver<T>{
	
	private final Consumer<T> onReceive;
	private final Consumer<R> onSend;
	private final Function<T,R> transform;
	
	private Receiver<R> next;
	
	public Transform(Function<T,R> transform, Consumer<T> onReceive, Consumer<R> onSend)
	{
		this.transform = transform;
		this.onReceive = onReceive;
		this.onSend = onSend;
	}
	
	public void setNext(Receiver<R> next) {
		this.next = next;
	}
	
	@Override
	public void receive(T a)
	{
		if(next != null)
		{
			onReceive.accept(a);
			R result = transform.apply(a);
			onSend.accept(result);
			next.receive(result);
		}
	}
}
