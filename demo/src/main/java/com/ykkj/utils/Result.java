package com.ykkj.utils;

public class Result<T> {

    //    error_code 状态值：0 极为成功，其他数值代表失败
    private Integer status;
    private Boolean result;
    //    error_msg 错误信息，若status为0时，为success
    private String msg;

    //    content 返回体报文的出参，使用泛型兼容不同的类型
    private T data;
    private long useTime;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData(Object object) {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }



	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	@Override
    public String toString() {
    	StringBuilder sb=new StringBuilder("Result{result=")
    	.append(result)
    	.append( ",status=")
    	.append(status)
    	.append(  ", msg=\'")
    	.append(msg)
    	.append( "\',data=")
    	.append(data)
    	.append("}");
        
         return sb.toString();
    }
}
