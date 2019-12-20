package by.future.common.utils;


import by.future.common.constant.Const;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * url请求工具类
 *
 * @Author: by@Deng
 * @Date: 2019-07-06 19:11
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static RequestConfig requestConfig = null;

    static {
        //设置请求和传输时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }

    public static void main(String[] args) {
        System.out.println(httpGet("https://www.kaops.com/", null));
    }


    /**
     * httpGet请求
     *
     * @param: params是k=v形式
     * @Author: by@Deng
     * @Date: 2019-07-07 09:26
     */
    public static String httpGet(String url, Map<String, String> paramMap) {

        if (StringUtils.isEmpty(url)) return null;

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {

            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (paramMap != null) {
                for (String key : paramMap.keySet()) {
                    builder.addParameter(key, paramMap.get(key));
                }
            }
            URI uri = builder.build();

            //todo 编码问题
            //todo 代理问题
            //todo 重试机制问题

            //发送get请求
            HttpGet request = new HttpGet(uri);
            request.setConfig(requestConfig);

            response = client.execute(request);

            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();

                return EntityUtils.toString(entity, Const.CHARSET_UTF8);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        }catch (SocketException e){
            logger.warn("请求超时");
        }catch (Exception e) {
            logger.error("get请求提交失败:" + url, e);
        } finally {

            try {
                if (response != null) response.close();
                client.close();

            } catch (IOException e) {
                logger.error("关闭error");
            }
        }
        return null;
    }


    /**
     * 返回json对象
     *
     * @Author: by@Deng
     * @Date: 2019-07-07 09:36
     */
    public static <T> T httpGet(String url, Map<String, String> paramMap, Class<T> c) {

        String retUrl = httpGet(url, paramMap);
        if (StringUtils.isNotEmpty(retUrl)) return JSONObject.parseObject(retUrl, c);

        return null;
    }


    /**
     * post请求
     *
     * @Author: by@Deng
     * @Date: 2019-07-07 10:06
     */
    public static String doPost(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), Const.CHARSET_UTF8);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) response.close();
                httpClient.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return resultString;
    }


    /**
     * GET请求，请求参数形式 k1=v1&k2=v2
     *
     * @Author：by@Deng
     * @Date：2018/5/4 18:19
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();

            /* 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            //遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/

            //定义 BufferedReader输入流来读取URL的响应
            if (connection.getInputStream() != null) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
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


    /**
     * POST请求,请求参数形式：k1=v1&k2=v2
     *
     * @Author：by@Deng
     * @Date：2018/5/4 18:21
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送post请求
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static JSONObject sendPostJson(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return jsonObject;
    }

}
