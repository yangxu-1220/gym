package com.gym.imgresult;

/**
 * @author 姬西南 2019.9.26  16:30
  * 提交结果类（此处为子类，继承BaseResult父类）
 */
public class ObjectOkResult extends BaseResult {

	    public String message = "success";
	    public Object data;

	    public String msg;

	    public String img;

	    public ObjectOkResult() {
	        super();
	    }

	    public ObjectOkResult(Object data) {
	        super();
	        this.data = data;
	    }
	    public ObjectOkResult(String img) {
	        super();
	        this.img = img;
	    }
	    public ObjectOkResult(String msg, int code){
	        this.code=code;
	        this.msg=msg;
	    }

	    public static ObjectOkResult fail(String msg, int code) {
	        return new ObjectOkResult(msg,code);
	    }
}
