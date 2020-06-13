package rjzx.spboot.hzu.project.controller;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.service.UserService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;
import rjzx.spboot.hzu.project.util.ResPonseUtil.StatusCode;
import rjzx.spboot.hzu.project.util.UUIDUtils;
import rjzx.spboot.hzu.project.util.VerificationCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2020-05-24 11:45:04
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * @param request
     * @param user
     * @param requestCode
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    public BaseResponse login(HttpServletRequest request,HttpServletResponse response,User user,String requestCode){
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        response.setHeader("Access-Control-Allow-Credentials","true");
        Cookie[] cookies=request.getCookies();
        System.out.println("id:"+user.getUserid()+"\nmm:"+user.getUserpwd()+"\nyzm:"+requestCode);
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("token")&&!cookie.getName().equals("JSESSIONID")) {
                    continue;
                }
                String cookieValue=cookie.getValue();
                if (cookieValue!=null&&!cookieValue.equals("")){
                    System.out.println(cookieValue);
                    String code=null;
                    if (request.getSession().getAttribute(cookieValue)!=null){
                        code=request.getSession().getAttribute(cookieValue).toString();
                        System.out.println(cookieValue+":"+code);
                    }
                    //判断验证码是否正确
                    if (requestCode.toUpperCase().equals(code)){
                        //验证码正确
                        //通过提交的登录信息从数据库查询用户
                        User loginUser=userService.queryById(user.getUserid());
                        //判断用户是否存在
                        if (loginUser!=null){
                            //用户存在，匹配密码
                            if (loginUser.getUserpwd().equals(user.getUserpwd())){
                                //密码匹配成功
                                request.getSession().setAttribute("user",loginUser);
                                BaseResponse baseResponse=new BaseResponse(StatusCode.Success);
                                baseResponse.setData(loginUser);
                                return baseResponse;
                            }else {
                                //密码匹配失败
                                return new BaseResponse(StatusCode.Fail);
                            }
                        }else {
                            //用户不存在
                            return new BaseResponse(StatusCode.UserNoExit);

                        }
                    }else {
                        //验证码错误
                        return new BaseResponse(StatusCode.VeriCodeErr);
                    }
                }
            }

        }
        return null;

    }


    /**
     * 获取验证码
     * @param response
     * @param request
     */
    @RequestMapping("/getVeriCode")
    @ResponseBody
    public void getVeriCode(HttpServletResponse response, HttpServletRequest request,Integer t){
        response.setHeader("Access-Control-Allow-Credentials","true");
        Cookie[] cookies=request.getCookies();
        BufferedImage codeImage=new BufferedImage(150,50,BufferedImage.TYPE_INT_RGB);
        String code= VerificationCodeUtil.drawVerificationCodeImg(150,50,codeImage);
        System.out.println(code);
        if (cookies==null||cookies.length==0){
            System.out.println("cookie空，设置cookie");
            Cookie cookie=new Cookie("token",request.getSession().getId());
            cookie.setPath("/");
            //cookie.setMaxAge(120);
            response.addCookie(cookie);
            request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
        }else {
            Boolean setCook=false;
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("token")){
                    if (cookie.getValue()!=null&&!cookie.getValue().equals("")){
                        request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
                        System.out.println(cookie.getValue());
                        setCook=true;
                        break;
                    }

                }else if (cookie.getName().equals("JSESSIONID")){
                    request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
                    System.out.println(cookie.getValue());
                    setCook=true;
                    break;
                }

            }
            if (!setCook){
                Cookie cookie=new Cookie("token",request.getSession().getId());
                cookie.setPath("/");
                //cookie.setMaxAge(120);
                response.addCookie(cookie);
                request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
            }

        }
        response.setContentType("image/png");
        try {
            OutputStream os=response.getOutputStream();
            ImageIO.write(codeImage,"png",os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse register(@RequestBody JSONObject jsonObject){
        User user = new User();
        user.setUserid(jsonObject.get("userid").toString());
        user.setUserpwd(jsonObject.get("userpwd").toString());
        user.setEmail(jsonObject.get("email").toString());
        user.setUsername(user.getUserid());

        if (userService.selectUser(user) != null){
            return new BaseResponse(StatusCode.RegisterFail);
        }

        user.setRole(0);
        String permission = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setPermission(permission);
        userService.register(user);
        return new BaseResponse(StatusCode.RegisterSuccess);



    }

    @RequestMapping(value = "/checkCode")
    public String checkCode(String code){
        User user = userService.checkCode(code);

        //如果用户不等于null，把用户状态修改status=1
        if (user != null){
            user.setRole(1);
            //把code验证码清空，已经不需要了
            user.setPermission("");
            userService.updateUserRole(user);
        }
        return "active";
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public BaseResponse<User> getUser(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,String userId){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            if (userId!=null &&!user.equals("")){
                User userRes=userService.queryById(userId);
                if (userRes!=null){
                    BaseResponse<User> baseResponse=new BaseResponse<>(1,"success","查询成功");
                    baseResponse.setData(userRes);
                    return baseResponse;
                }else {
                    return new BaseResponse<>(-1,"fail","该用户不存在");
                }
            }else {
                return new BaseResponse<>(-2,"fail","用户id不能为空");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }
    }

}