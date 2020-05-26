package rjzx.spboot.hzu.project.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 画验证码工具类
 */
public class VerificationCodeUtil {
    public static String drawVerificationCodeImg(int width, int height, BufferedImage img){
        Graphics2D graphics2D=(Graphics2D)img.getGraphics();

        //设置画笔颜色为白色，验证码背景色
        graphics2D.setColor(Color.WHITE);

        //填充背景
        graphics2D.fillRect(0,0,width,height);

        //设置验证码字体
        graphics2D.setFont(new Font("微软雅黑",Font.BOLD,40));

        //验证码由此基础字符串获得
        String base="123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        //验证码字符
        StringBuffer code=new StringBuffer();

        Random random=new Random();
        //旋转原点及字符的x坐标
        int x=random.nextInt(img.getWidth()/2-35);
        //画出验证码
        for (int i=0;i<4;i++){
            int charLocation=random.nextInt(base.length());

            String codechar=base.charAt(charLocation)+"";

            code.append(codechar);

            //旋转角度，小于30度
            int angle=random.nextInt()%30;
            //旋转原点及字符的y坐标
            int y=img.getHeight()/2+10;
            //正向旋转
            graphics2D.rotate(angle*Math.PI/180,x,y);
            //先取随机颜色，然后画字符
            graphics2D.setColor(getRandomColor());
            graphics2D.drawString(codechar,x,y);
            //反向旋转,即将正向旋转后的图案摆正
            graphics2D.rotate(-angle*Math.PI/180,x,y);
            //递加x坐标
            x+=30;
        }

        //画干扰线
        for (int i=0;i<6;i++){
            //设置随机颜色
            graphics2D.setColor(getRandomColor());

            //随机画线
            graphics2D.drawLine(random.nextInt(width),random.nextInt(height),random.nextInt(width),random.nextInt(height));
        }

        //画干扰噪点
        for (int i=0;i<30;i++){
            //设置随机颜色
            graphics2D.setColor(getRandomColor());

            //随机画噪点
            graphics2D.fillRect(random.nextInt(width),random.nextInt(height),2,2);
        }
        return code.toString();
    }


    private static Color getRandomColor(){
        Random random=new Random();
        Color color=new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        return color;
    }
}
