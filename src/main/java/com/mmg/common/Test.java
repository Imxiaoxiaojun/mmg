package com.mmg.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test {
	public static void main(String[] args) throws FileNotFoundException
    {
        Captcha captcha = new GifCaptcha(150,40,5);//   gif格式动画验证码
        captcha.out(new FileOutputStream("D:/1.gif"));
        System.out.println(captcha.text());
        
    }
}
