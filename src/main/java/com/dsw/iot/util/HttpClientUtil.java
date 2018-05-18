package com.dsw.iot.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/* 
 * 利用HttpClient进行post请求的工具类 
 */
public class HttpClientUtil {
    protected static final Logger logger = Logger.getLogger(HttpClientUtil.class);

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static String postBody(String url, Object data) throws Exception {
    	HttpClient httpClient = null;
        String body = null;
        
        httpClient = new SSLClient();
        String json = JSON.toJSONString(data);
        HttpPost httpPost = new HttpPost(url);
        StringEntity se = new StringEntity(json, Charsets.UTF_8.name());
        se.setContentType("application/json; charset=utf-8");
        httpPost.setEntity(se);
        body = invoke(httpClient, httpPost);
            
        return body;
    }
    
    public static String post(String url, Map<String, String> params) {
        HttpClient httpClient = null;
        String body = null;
        try {
            httpClient = new SSLClient();
            String json = JSON.toJSONString(params);
            HttpPost post = postJson(url, json);
            body = invoke(httpClient, post);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return body;
    }

    private static String invoke(HttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);
        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(HttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpost);
            if (response.getStatusLine().getStatusCode() == 200) {
//        	   System.out.println("正常返回!");
                return response;
            } else {
                System.out.println("未能正常返回，放弃该请求!");
                httpost.abort();
                System.out.println("未能正常返回，放弃该请求成功~");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    private static HttpPost postJson(String url, String json) {
        try {
            //String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            StringEntity se = new StringEntity(json);
            se.setContentType(CONTENT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            return httpPost;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }

    /**
     * http post方法
     *
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, Object> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> elem = (Entry<String, Object>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("post方法失败", ex);
        }
        return result;
    }

    /**
     * http get方法
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params, String charset) {
        String result = null;
        try {
            //设置参数
            StringBuilder sb = new StringBuilder(url);
            sb.append("?1=1");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
            HttpClient httpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(sb.toString());
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("get方法失败", ex);
        }
        return result;
    }
    
    /**
     * http get方法
     * @param url
     * @return
     */
    public static String doGet(String url) throws Exception {
        String result = null;
       // try {
            HttpClient httpClient = new SSLClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, HTTP.UTF_8);
                }
            }
/*        } catch (Exception ex) {
            logger.warn("get方法失败", ex);
        }*/
        return result;
    }
    
    public static String get(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);

        httpclient.getConnectionManager().shutdown();

        return body;
    }


    /**
     * 根据url下载文件
     */

    public static boolean downloadByUrl(String ourputFile, String url) throws Exception {
        try {
            URL resourceUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) resourceUrl.openConnection();
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            DataInputStream reader = new DataInputStream(con.getInputStream());

            File f = new File(ourputFile);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            DataOutputStream writer = new DataOutputStream(
                    new FileOutputStream(f));
            int ks = 0;
            while ((ks = reader.read()) != -1) {
                writer.write(ks);
            }
            reader.close();
            writer.close();
            con.disconnect();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 发送body报文
     *
     * @param urlPath
     * @param json
     * @return
     * @throws Exception
     */
    public static String postBody(String urlPath, String json) {
        String response = null;
        try {
            // Configure and open a connection to the site you will send the request
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(3 * 1000);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("content-type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestProperty("contentType", "UTF-8");
            // 得到请求的输出流对象
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            // 把数据写入请求的Body
            out.write(json);
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            String body = IOUtils.toString(inputStream, "UTF-8");
            if (urlConnection.getResponseCode() == 200) {
                response = body;
            }
        } catch (IOException e) {
            logger.error("发送报文失败！", e);
        }
        return response;
    }

}
