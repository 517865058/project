package rjzx.spboot.hzu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.service.UserService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;
import rjzx.spboot.hzu.project.util.ResPonseUtil.StatusCode;
import rjzx.spboot.hzu.project.util.VerificationCodeUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

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
        response.setHeader("Access-Control-Allow-Origin","file://");
        response.setHeader("Access-Control-Allow-Credentials","true");
        Cookie[] cookies=request.getCookies();
        System.out.println("id:"+user.getUserid()+"\nmm:"+user.getUserpwd()+"\nyzm:"+requestCode);
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("token")&&!cookie.getName().equals("JSESSIONID"))continue;
                String cookieValue=cookie.getValue();
                if (cookieValue!=null&&!cookieValue.equals("")){
                    System.out.println(cookieValue);
                    String code=request.getSession().getAttribute(cookieValue).toString();
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
        Cookie[] cookies=request.getCookies();
        BufferedImage codeImage=new BufferedImage(150,50,BufferedImage.TYPE_INT_RGB);
        String code= VerificationCodeUtil.drawVerificationCodeImg(150,50,codeImage);
        System.out.println(code);
        if (cookies==null||cookies.length==0){
            System.out.println("cookie空，设置cookie");
            Cookie cookie=new Cookie("token",request.getSession().getId());
            cookie.setPath("/");
            cookie.setMaxAge(120);
            response.addCookie(cookie);
            request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
        }else {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("token")){
                    if (cookie.getValue()!=null&&!cookie.getValue().equals("")){
                        request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
                        System.out.println(cookie.getValue());
                        break;
                    }

                }else {
                    request.getSession().setAttribute(cookie.getValue(),code.toUpperCase());
                    System.out.println(cookie.getValue());
                    break;
                }

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

}