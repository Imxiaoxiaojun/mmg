package com.mmg.common;

import com.mmg.util.StringUtil;

public class Test {
    public static void main(String[] args) throws Exception {
//        System.out.println(CacheManager.getSimpleFlag("alksd"));
//        CacheManager.putCache("abc", new Cache());
//        CacheManager.putCache("def", new Cache());
//        CacheManager.putCache("ccc", new Cache());
//        CacheManager.clearOnly("");
//        Admin c = new Admin();
//        for (int i = 0; i < 10; i++) {
//            CacheManager.putCache("menu" + i, c);
//        }
//        for (int i = 0; i < 10; i++) {
//            CacheManager.putCache("quickMenu" + i, c);
//        }
//        CacheManager.putCache("aaaaaaaa", c);
//        CacheManager.putCache("abchcy;alskd", c);
//        CacheManager.putCache("cccccccc", c);
//        CacheManager.putCache("abcoqiwhcy", c);
//        System.out.println("删除前的大小：" + CacheManager.getCacheSize());
//        CacheManager.getCacheAllkey();
//        CacheManager.clearAll("aaaa");
//        System.out.println("删除后的大小：" + CacheManager.getCacheSize());
//          List list = CacheManager.getCacheListkey("Menu");
//
//        Iterator<String> it = list.iterator();
//        while (it.hasNext()){
//            System.out.println(CacheManager.getCacheInfo(it.next()).toString());
//        }
        System.out.println(StringUtil.getMd5Stri("123456"));
}
}
