package com.grab.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class testDownload {
    //抓取H网
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        //要爬虫网页的url
        String url="#";
        //返回存储有效url的文件夹名称
        String urlfilename = GetUrl(url);
        //下载有效url所对应的照片
        downPic(urlfilename);
        System.out.println("开始将抓取的图片存入本机中");
    }

    //从已经下载下来的有效url中
    public static void downPic(String urlfilename) throws Exception {
        // TODO Auto-generated method stub
//      URL url = new URL(null);
        BufferedReader  bfr= new BufferedReader(new FileReader(urlfilename));
        String url1 = null;
        int num=0;
        while((url1=bfr.readLine())!=null){
            num++;
            downPic(bfr, url1,num);
        }
        bfr.close();
    }
    /**
     * 通过获取的url下载图片*/
    public static void downPic(BufferedReader bfr, String url1 , int num)throws IOException {
        URL url = new URL(url1);
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
        BufferedInputStream  bis= new BufferedInputStream(urlConnection.getInputStream());
        BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream(new File("J:/PIC/"+num+".jpg")));
        int ch=0;
        byte[] buf = new byte[1024];
        //写入到downPic
        while((ch=bis.read(buf))!=-1){
            bos.write(buf,0,ch);
        }
        bos.close();
    }
    //获取网页有效UrL
    public static String GetUrl(String url) throws Exception {
        URL u=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection)u.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
        String urlFileName=new String();
        if(urlConnection.getResponseCode()==200){
            BufferedReader  bus= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            PrintWriter pw = new PrintWriter(new FileWriter("F:/zhihu_pic_url.txt"),true);
            PrintWriter pw1 = new PrintWriter(new FileWriter("F:/zhihu_content.txt"),true);
            String regex= "[https|http]+://+[pic]+[A-Za-z0-9.//_%=-]*";
            Pattern p = Pattern.compile(regex);
            String line;
            while((line=bus.readLine())!=null){
                Matcher m = p.matcher(line);
                pw1.println(line);
                while(m.find()){
                    pw.println(m.group());
                }
            }
            bus.close();
            pw.close();
            pw1.close();
            urlFileName="F:/zhihu_pic_url.txt";
        }
        return urlFileName;
    }
}


