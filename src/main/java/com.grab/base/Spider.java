package com.grab.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Spider {
    public static String GetContent(String url) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        HttpURLConnection urlConnection=null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 初始化链接
            urlConnection = (HttpURLConnection)realUrl.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36" +
                    " (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            // BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("GetContent出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
