package fnb;

import java.util.function.Function;
import fnb.cmd.Circle;

public class CreateCircle implements Function<String, Circle>{

	@Override
	public Circle apply(String t) {
		String[] arr = t.split(" ");
		
		if(arr.length == 4)
		{
			try{
				Circle c = new Circle(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
				return c;
			}catch(Exception ex)
			{
				return null;
			}
		}
		return null;
	}
}