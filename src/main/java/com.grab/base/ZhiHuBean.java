package com.grab.base;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ZhiHuBean {
    public String zhihuUrl;// 网页链接
    public String question;// 问题名;
    public ArrayList<String> zhihuPicUrl;// 图片链接

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getZhihuPicUrl() {
        return zhihuPicUrl;
    }

    public void setZhihuPicUrl(ArrayList<String> zhihuPicUrl) {
        this.zhihuPicUrl = zhihuPicUrl;
    }

    public ZhiHuBean(String url) throws Exception {
        zhihuUrl = url;
        zhihuPicUrl = new ArrayList<String>();
        if (isZhuHuUrl(url)) {
            url=getRealUrl(url);
            System.out.println("正在抓取链接：" + url);
            String content = Spider.GetContent(url);
            //System.out.println("content:"+content);
            Matcher m;
            // 匹配标题
            m = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>").matcher(content);
            if (m.find()) {
                question = m.group(1);
            }
            // 匹配答案图片链接
            m = Pattern.compile("</noscript><img.+?src=\"(https.+?)\".+?").matcher(content);
            boolean isFind;
            while (isFind= m.find()) {
                zhihuPicUrl.add(m.group(1));
            }
        }else {
            throw new Exception("网址有误！请输入网址！");
        }
    }

    // 处理url
    String getRealUrl(String url) {
        Matcher m = Pattern.compile("(\\d*)/answer/(\\d*)").matcher(url);
        if (m.find()) {
            return "https://www.zhihu.com/question/" + m.group(1);
        } else {
            return url;
        }
    }
    //判断知乎网址
    boolean isZhuHuUrl(String url){
        if(url.indexOf("http")!=-1){
            return true;
        }else return false;
    }

}
