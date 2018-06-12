package net.feyin.openapi.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * apache httpclient 4.3.5 utils
 * Created by Think on 2017/12/5.
 */
public class HttpClientUtil {

    /**
     * 发送GET请求
     */
    public static String sendGetRequest(String url) {
        return request(new HttpGet(url));
    }

    /**
     * 发送POST请求
     */
    public static String sendPostRequest(String url, List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        return request(httpPost);
    }

    /**
     * 发送DELETE请求
     */
    public static String sendDeleteRequest(String url) {
        return request(new HttpDelete(url));
    }

    /**
     * 发送json请求
     */
    public static String sendJsonRequest(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity se = new StringEntity(json, Consts.UTF_8);
        httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        httpPost.setEntity(se);
        return request(httpPost);
    }

    private static String request(HttpRequestBase request) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000).build();
            request.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
