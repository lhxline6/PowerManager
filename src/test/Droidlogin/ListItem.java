package test.Droidlogin;

import java.util.HashMap;
import java.util.Map;

/**
 * ListView中的列表项
 * @author PiaoHong
 *
 */
public class ListItem 
{
	/**类型*/
	public int mType;
	/**键值对应Map*/
	public Map<Integer, ?> mMap ;
	
	public ListItem(int type, HashMap<Integer, ?> map)
	{
		mType = type;
		mMap = map;
	}
	
	public int getType(){
		return mType;
	}
}
