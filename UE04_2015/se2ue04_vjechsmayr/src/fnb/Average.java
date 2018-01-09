package fnb;

import java.util.function.Function;
import java.util.ArrayList;

public class Average implements Function<Float, Float>{

	private ArrayList<Float> array = new ArrayList<Float>();
	
	@Override
	public Float apply(Float arg0) {
		float sum = 0;
		
		array.add(arg0);
		if(array.size()>5)
		{
			array.remove(0);
		}
		
		for(int i=0; i<array.size();i++)
		{
			sum += array.get(i);
		}
		return sum/array.size();
	}
}