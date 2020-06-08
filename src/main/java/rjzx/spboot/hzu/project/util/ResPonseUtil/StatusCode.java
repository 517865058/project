package rjzx.spboot.hzu.project.util.ResPonseUtil;

public enum StatusCode {

    Success(1,"success","登录成功"),
    RegisterSuccess(2,"success","注册成功"),
    ProjectApplicationSuccess(3,"success","项目申请成功"),
    ProjectApplicationFail(4,"fail","项目申请失败, 项目已存在"),
    ProjectUpdateSuccess(5,"success","项目修改成功"),
    Fail(-1,"fail","密码错误"),
    UserNoExit(-2,"fail","用户不存在"),
    VeriCodeErr(-3,"error","验证码错误"),
    RegisterFail(-4,"fail","注册失败,用户已存在");

    private Integer code;//状态码
    private String status;//状态
    private String msg;//描述信息

    StatusCode(Integer code,String status,String msg){
        this.code=code;
        this.status=status;
        this.msg=msg;
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
}
