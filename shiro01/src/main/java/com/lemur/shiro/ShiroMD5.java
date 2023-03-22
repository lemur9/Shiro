package com.lemur.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroMD5 {
    public static void main(String[] args) {
        //密码明文
        String password = "z3";
        //使用md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("使用MD5加密 = " + md5Hash);
        //带盐的MD5加密，盐就是在密码明文后拼接新字符串，然后再加密
        Md5Hash md5HashSalt = new Md5Hash(password,"salt");
        System.out.println("带盐的MD5加密 = " + md5HashSalt);
        //为了保证安全，避免被破解还可以多次迭代加密，保证数据安全
        Md5Hash md5HashIterations = new Md5Hash(password,"salt",3);
        System.out.println("MD5带盐的三次加密 = " + md5HashIterations);
        //使用父类加密 Md5Hash extends SimpleHash
        SimpleHash simpleHash = new SimpleHash("MD5", password, "salt", 3);
        System.out.println("simpleHash带盐三次加密 = " + simpleHash);
    }
}
