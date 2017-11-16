package jdbc.json;

import java.util.List; 
import bean.enums.ENUM_RangeType;
import bean.validate.ErrorInfo;
import bean.validate.Validatable;
import bean.validate.Validator;

public class City_enty extends Validatable{
	private int id;
	private String name;
	private String city;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public List<ErrorInfo> validate() {
		// TODO 自动生成的方法存根
		 return dumpErrorInfo();
	} 
	
}
