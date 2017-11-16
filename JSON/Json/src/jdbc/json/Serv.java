package jdbc.json;

import dal.db.DbOperate;
import dal.enums.ENUM_ParamOutModes;

public class Serv {
	public Object[] USP_S_JSON(Object enty)
	{
		String spname = "PKGJSON.USP_S_JSON";
		ENUM_ParamOutModes opmode = ENUM_ParamOutModes.POM_AD_ZHUJIANZ;
		DbOperate dbo = new DbOperate();
		return dbo.executeProcdure(spname, enty, opmode);
	}

}
