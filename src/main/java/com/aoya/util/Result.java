package com.aoya.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 沈晨辉
 * @date 2016年11月23日
 * <p> 操作结果集
 */
public class Result {
	private final static Logger logger = LoggerFactory.getLogger(Result.class);
	public static final String RESULT_OP_SUCCESS = "操作成功！";
	public static final String RESULT_OP_FAIL = "操作失败！";
	public static final String RESULT_OP_NOTONLINE = "未登录！";
	public static final String RESULT_OP_INVALIDPARA = "参数非法！";
	public static final String RESULT_OP_UNAUTHORITY = "未授权！";
	public static final String RESULT_OP_NOAUTHORITY = "无权限操作！";
//	重定向登录页面
	public static final String RETURN_JSP_LOGIN 	= "/idaqi/index";
//	重定向参数异常页面
	public static final String RETURN_JSP_PARAMERR 	= "/front/login";
//	重定向异常页面
	public static final String RETURN_JSP_ERROR 	= "/front/login";

	public static final String RETURN_JSP_EXPT_LOGIN = "/idaqi/index";
	//错误页面
	public static final String RETURN_ERROR_JSP 	= "/idqi/error";
	//无权限操作页面
	public static final String RETURN_NOAUTHORITY_JSP 	= "/idaqi/noAuthority";

	/**
	 * 操作是否成功
	 */
	private int 	success;
	/**
	 * 反馈信息
	 */
	private String 	msg;
	/**
	 * 附加数据
	 */
	private Map	body = new HashMap();

	public Result(int success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Result noLine() {
		this.success = SUCCESS.NOTONLINE.getValue();
		this.msg = Result.RESULT_OP_NOTONLINE;
		return this;
	}
	public Result noAuthority() {
		this.success = Result.SUCCESS.NO.getValue();
		this.msg = Result.RESULT_OP_NOAUTHORITY;
		return this;
	}

	public Result() {
	    this.success = Result.SUCCESS.NO.getValue();
        this.msg = Result.RESULT_OP_FAIL;
    }

    public Result changeSuccess(){
		this.success = SUCCESS.YES.getValue();
		this.msg = Result.RESULT_OP_SUCCESS;
		return this;
	}

	public Result changeFail(){
		this.success = SUCCESS.NO.getValue();
		this.msg = Result.RESULT_OP_FAIL;
		return this;
	}

	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.disableHtmlEscaping().create();

		JsonElement jsonobject = gson.toJsonTree(this);

		String json = jsonobject.toString();

		logger.debug(json);
		return json;
	}

	/**
	 * 请求状况代码 0、失败 1、成功 2、不在线 3、错误 4 已经完成
	 */
	public static enum SUCCESS {
		NO(0), YES(1), NOTONLINE(2), ERR(3), FIN(4);

		private final int value;

		SUCCESS(int v) {
			value = v;
		}

		public int getValue() {
			return value;
		}
	}


	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map getBody() {
		return body;
	}
	public void setBody(Map body) {
		this.body = body;
	}

	public void addElement(String key, Object value) {
		body.put(key, value);
	}

	public Object getElement(String key) {
		return body.get(key);
	}
}
