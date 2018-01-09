package fnb;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Branch<T> implements Receiver<T>{

	private final Consumer<T> onReceive;
	private final Consumer<T> onSend;
	private final Predicate<T> branch;
	private Receiver<T> nextFalse;
	private Receiver<T> nextTrue;
	
	public Branch(Predicate<T> branch, Consumer<T> onReceive, Consumer<T> onSend)
	{
		this.branch = branch;
		this.onReceive = onReceive;
		this.onSend = onSend;
	}
	
	public void setNextTrue(Receiver<T> next) {
		this.nextTrue = next;
	}
	
	public void setNextFalse(Receiver<T> next) {
		this.nextFalse = next;
	}
	
	@Override
	public void receive(T a) {
		if(nextTrue != null && nextFalse != null)
		{
			onReceive.accept(a);
			if(branch.test(a))
			{
				onSend.accept(a);
				nextTrue.receive(a);
			}else
			{
				onSend.accept(a);
				nextFalse.receive(a);
			}
		}
	}
}