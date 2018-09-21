package com.grab.base;
public class test {
    public static void main(String[] args) throws Exception {
        String url = "http://d2.sku117.info/pw/htm_data/16/1809/1290554.html";
        ZhiHuBean myZhihu = new ZhiHuBean(url);
        System.out.println("标题：" + myZhihu.getQuestion());
        System.out.println("");
        //定义下载路径
        String addr = "J:/PIC/";
        System.out.println("即将开始下载图片到"+addr+myZhihu.getQuestion());
        System.out.println("");
        System.out.println("开始下载................");
        System.out.println("");
        // 把图片下载到本地文件夹
        DownLoad.downLoadPics(myZhihu, addr);
        System.out.println("");
        System.out.println("图片下载完毕，请到"+addr+myZhihu.getQuestion()+"里去看看吧！！！");
    }
}
