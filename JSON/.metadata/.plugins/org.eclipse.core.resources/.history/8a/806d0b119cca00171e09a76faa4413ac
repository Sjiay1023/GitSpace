package jdbc.json;

import java.util.List;

import bean.validate.Deserializer;
import bean.validate.ErrorInfo;
import bean.validate.exception.IllegalArgumentException;
import bean.validate.util.Utils;
import apl.json.RetMsg_save;
import apl.json.RetMsg_strlist;

public class Wsi {
	private Serv srv = null;
	public  Wsi() {
		srv = new Serv();
	}
	public String save_city(String data1){
	Object[] retObjs = null;
	String appcode = "";
	String strResult = "";
	try{
		List<City_enty> entys= Deserializer.parse(data1, City_enty.class);
		for(int i=0;i < entys.size();i++){
			retObjs = srv.USP_S_JSON(entys.get(i));
			System.out.println("entys:"+entys.get(i).toString());
			strResult = (new RetMsg_save(retObjs)).toString();
			appcode = retObjs[1].toString();
			if(appcode.equals("-1")){
				System.out.println("Ê§°Ü");
				return new RetMsg_strlist("-1", "½»Ò×Ê§°Ü!",strResult).toString();
			}
		}
		return strResult;
	}catch (IllegalArgumentException e) {
		return Utils.constructRetMsg(e.getErrorInfo()).toString();
	}	
}
}