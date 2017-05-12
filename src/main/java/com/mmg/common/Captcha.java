package com.mmg.common;

import java.awt.Color;
import java.awt.Font;
import java.io.OutputStream;
import static com.mmg.common.Randoms.*;

public abstract class Captcha {
	protected Font font = new Font("Verdana", Font.ITALIC|Font.BOLD, 28);   // 字体  
    protected int len = 5;  // 验证码随机字符长度  
    protected int width = 100;  // 验证码显示跨度  
    protected int height = 30;  // 验证码显示高度  
    private String chars = null;  // 随机字符串  
   
    public static final char ALPHA[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };
    /** 
     * 生成随机字符数组 
     * @return 字符数组 
     */  
    protected char[] alphas()  
    {  
        char[] cs = new char[len];  
        for(int i = 0;i<len;i++)  
        {  
            cs[i] = ALPHA[num(0, ALPHA.length)];
        }  
        chars = new String(cs);  
        return cs;  
    }  
    public Font getFont()  
    {  
        return font;  
    }  
   
    public void setFont(Font font)  
    {  
        this.font = font;  
    }  
   
    public int getLen()  
    {  
        return len;  
    }  
   
    public void setLen(int len)  
    {  
        this.len = len;  
    }  
   
    public int getWidth()  
    {  
        return width;  
    }  
   
    public void setWidth(int width)  
    {  
        this.width = width;  
    }  
   
    public int getHeight()  
    {  
        return height;  
    }  
   
    public void setHeight(int height)  
    {  
        this.height = height;  
    }  
   
    /** 
     * 给定范围获得随机颜色 
     * @return Color 随机颜色 
     */  
    protected Color color(int fc, int bc)  
    {  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + num(bc - fc);  
        int g = fc + num(bc - fc);  
        int b = fc + num(bc - fc);  
        return new Color(r, g, b);  
    }  
   
    /** 
     * 验证码输出,抽象方法，由子类实现 
     * @param os 输出流 
     */  
    public abstract void out(OutputStream os);  
   
    /** 
     * 获取随机字符串 
     * @return string 
     */  
    public String text()  
    {  
        return chars;  
    }
}