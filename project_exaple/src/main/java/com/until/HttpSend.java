package com.until;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HTTP请求
 */
public class HttpSend {

    /**
     * GET请求，超时时间必须设置
     *
     * @param url
     * @param params Map<String, String>数据格式传参
     * @return
     */
    public static JSONObject get(String url, Map<String, String> params) {
        String result = null;
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        try {
            List<NameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            URI uri = new URI(url + "?" + URLEncodedUtils.format(pairs, Consts.UTF_8));
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(uri);
            // 连接超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            // 读取超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = JSON.toJSONString(e.getMessage());
        } finally {
            httpGet.abort();
            httpGet.releaseConnection();
            httpClient.getConnectionManager().shutdown();
        }
        return JSONObject.parseObject(result);
    }

    /**
     * POST请求，超时时间必须设置
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject post(String url, Map<String, String> params) {
        String result = null;
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        try {
            List<NameValuePair> pairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            // 连接超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            // 读取超时设置
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
            StringEntity stringEntity = new UrlEncodedFormEntity(pairs, Consts.UTF_8);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpPost.setHeader("Accept", "text/plain;charset=utf-8");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            result = JSON.toJSONString(Result.error(e.getMessage()));
            result = JSON.toJSONString(e.getMessage());
        } finally {
            // 终止本次请求
            httpPost.abort();
            // 释放连接
            httpPost.releaseConnection();
            httpClient.getConnectionManager().shutdown();
        }
        return JSONObject.parseObject(result);
    }

}
