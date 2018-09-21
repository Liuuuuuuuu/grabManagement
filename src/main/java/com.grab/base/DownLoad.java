package com.grab.base;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
public class DownLoad {

 public static boolean downLoadPics(ZhiHuBean zhiHuBean, String filePath) throws Exception {
             // 文件路径+标题
             String dir = filePath + zhiHuBean.getQuestion();
             // 创建
             File fileDir = new File(dir);
             fileDir.mkdirs();
             // 获取所有图片路径集合
             ArrayList<String> zhiHuPics = zhiHuBean.getZhihuPicUrl();
             // 初始化一个变量，用来显示图片编号
             int i = 1;
             // 循环下载图片
             for (String zhiHuPic : zhiHuPics) {
                     URL url = new URL(zhiHuPic);
                     DataInputStream dis = new DataInputStream(url.openStream());
                     String newImageName = dir + "/" + "图片" + i + ".jpg";
                     // 建立一个新的文件
                     FileOutputStream fos = new FileOutputStream(new File(newImageName));
                     byte[] buffer = new byte[1024];
                     int length;
                     System.out.println("正在下载......第 " + i + "张图片......请稍后");
                     // 开始写入
                     while ((length = dis.read(buffer)) > 0) {
                             fos.write(buffer, 0, length);
                         }
                     dis.close();
                     fos.close();
                     System.out.println("第 " + i + "张图片下载完毕......");
                     i++;
                 }
             return true;
         }
}
