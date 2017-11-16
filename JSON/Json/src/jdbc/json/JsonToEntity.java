package jdbc.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.JSONToken;

public class JsonToEntity {
	static String json = "{\"id\":\"5\",\"name\":\"python\",\"city\":\"南京\"}";
	//java转换实体类
	public void Json2entity(){
		City_enty city = JSON.parseObject(json,City_enty.class);
		System.out.println(city.toString());
	}
	
}