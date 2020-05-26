package rjzx.spboot.hzu.project.util.ResPonseUtil;

public class BaseResponse<T> {
    private Integer code;//状态码

    private String status;//状态

    private String msg;//描述信息

    private T data;//响应数据

    public BaseResponse(Integer code,String status,String msg){
        this.code=code;
        this.status=status;
        this.msg=msg;
    }

    public BaseResponse(Integer code,String status,String msg,T data){
        this.code=code;
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public BaseResponse(StatusCode statusCode){
        this.code=statusCode.getCode();
        this.status=statusCode.getStatus();
        this.msg=statusCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
