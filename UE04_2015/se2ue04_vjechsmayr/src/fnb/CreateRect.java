package fnb;

import java.util.function.Function;
import fnb.cmd.Rect;

public class CreateRect implements Function<String, Rect>{

	@Override
	public Rect apply(String t) {
		String[] arr = t.split(" ");
		
		if(arr.length == 5)
		{
			try{
				Rect r = new Rect(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
				return r;
			}catch(Exception ex)
			{
				return null;
			}
		}
		return null;
	}
}