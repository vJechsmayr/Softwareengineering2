package fnb;

import java.util.function.Function;

public class Round implements Function<Float, Integer>{

	@Override
	public Integer apply(Float arg0) {
		return arg0.intValue();
	}
}
