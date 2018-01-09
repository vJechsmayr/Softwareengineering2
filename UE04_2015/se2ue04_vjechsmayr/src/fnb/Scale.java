package fnb;

import java.util.function.*;

public class Scale implements Function<Float, Float>{

	@Override
	public Float apply(Float arg0) {
		return arg0*10;
	}
}
