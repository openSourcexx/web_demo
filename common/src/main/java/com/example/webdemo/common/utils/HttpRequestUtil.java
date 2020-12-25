package com.example.webdemo.common.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpRequestUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    private static int defaultTimeout = 60000;

    private static HttpClient httpClient = null;

    public static HttpClient getInstance() {
        if (httpClient == null) {
            synchronized (HttpRequestUtil.class) {
                if (httpClient == null) {
                    httpClient = HttpClients.createDefault();
                    return httpClient;
                }
            }
        }
        return httpClient;
    }

    /**
     * 不带超时时间的post请求，默认60s超时
     *
     * @param postUrl
     * @param bodyContent
     * @return
     * @throws Exception
     */
    public static String sendPost(String postUrl, String bodyContent) throws Exception {
        return sendPost(postUrl, bodyContent, defaultTimeout, null, true);
    }

    public static String sendPostWithoutLog(String postUrl, String bodyContent) throws Exception {
        return sendPost(postUrl, bodyContent, defaultTimeout, null, false);
    }

    /**
     * 带超时时间的post请求,单位毫秒
     *
     * @return
     */
    public static String sendPost(String postUrl, String bodyContent, Integer setTimeout, Map<String, String> header,
                                  boolean logInfo)
            throws Exception {
        if (logInfo) {
            logger.info("开始发送Post请求，请求地址：{}，body：{}", postUrl, bodyContent);
        }
        try {
            // 获取时间戳
            long time = System.currentTimeMillis();
            String timestamp = String.valueOf(time / 1000);
            // 根据地址获取请求
            HttpClient httpclient = getInstance();

            HttpPost httppost = new HttpPost(postUrl);
            httppost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            httppost.setHeader("timestamp", timestamp);
            if (!CollectionUtils.isEmpty(header)) {
                if (logInfo) {
                    logger.info("添加请求头header:{}", GsonUtil.obj2Json(header));
                }
                header.forEach(httppost::addHeader);
            }
            //填充请求内容
            StringEntity entity = new StringEntity(bodyContent, "utf-8");
            entity.setContentType("application/json");
            httppost.setEntity(entity);
            RequestConfig config = RequestConfig.DEFAULT;
            httppost.setConfig(setTimeOutConfig(config, setTimeout));
            // 执行post请求
            HttpResponse response = httpclient.execute(httppost);
            String jsonResult = EntityUtils.toString(response.getEntity());
            if (logInfo) {
                logger.info("发送post请求交易成功，返回数据：{}", jsonResult);
            }
            return jsonResult;
        } catch (Exception e) {
            if (logInfo) {
                logger.error("发送post请求异常:{}", e.getMessage());
            }
            throw e;
        }
    }

    /**
     * 不带超时时间的get请求，默认60s超时
     *
     * @param getUrl
     * @return
     * @throws Exception
     */
    public static String sendGet(String getUrl) throws Exception {
        return sendGet(getUrl, defaultTimeout);
    }

    /**
     * 带超时时间的get请求
     *
     * @return
     */
    public static String sendGet(String getUrl, Integer setTimeout) throws Exception {
        logger.info("开始发送Get请求，请求地址：{}", getUrl);
        try {
            /** 获取时间戳 */
            long time = System.currentTimeMillis();
            String timestamp = String.valueOf(time / 1000);
            // 根据地址获取请求
            HttpClient httpclient = getInstance();
            // 设置请求头信息
            HttpGet httpGet = new HttpGet(getUrl);
            httpGet.addHeader(HTTP.CONTENT_TYPE, "application/json");
            httpGet.setHeader("timestamp", timestamp);

            RequestConfig config = RequestConfig.DEFAULT;
            httpGet.setConfig(setTimeOutConfig(config, setTimeout));
            /** 发送请求 */
            HttpResponse response = httpclient.execute(httpGet);
            String jsonResult = EntityUtils.toString(response.getEntity());
            logger.info("发送get请求交易成功，返回数据：{}", jsonResult);
            return jsonResult;
        } catch (Exception e) {
            logger.error("发送get请求异常:{}", e.getMessage());
            throw e;
        }
    }

    public static String sendPostByConn(String url, String accessToken, int timeout, Object requestObj)
            throws IOException {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            // connection
            URL realUrl = new URL(url);
            connection = (HttpURLConnection) realUrl.openConnection();

            // trust-https
            boolean useHttps = url.startsWith("https");
            if (useHttps) {
                HttpsURLConnection https = (HttpsURLConnection) connection;
                trustAllHosts(https);
            }

            // connection setting
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(timeout * 1000);
            connection.setConnectTimeout(3 * 1000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

            if (accessToken != null && accessToken.trim()
                    .length() > 0) {
                connection.setRequestProperty("ACCESS_TOKEN", accessToken);
            }

            // do connection
            connection.connect();

            // write requestBody
            if (requestObj != null) {
                String requestBody = GsonUtil.obj2Json(requestObj);

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(requestBody.getBytes("UTF-8"));
                dataOutputStream.flush();
                dataOutputStream.close();
            }

            // valid StatusCode
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("响应失败");
            }

            // result
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

        } catch (Exception e) {
            logger.error("发送post请求异常:{}", e.getMessage());
            throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e2) {
                logger.error(e2.getMessage(), e2);
            }
        }
    }

    /**
     * 设置默认超时时间
     *
     * @param requestConfig
     * @param setTimeout
     * @return
     */
    private static RequestConfig setTimeOutConfig(RequestConfig requestConfig, Integer setTimeout) {
        //设置请求超时时间，单位毫秒
        if (setTimeout == null) {
            setTimeout = defaultTimeout;
        }
        return RequestConfig.copy(requestConfig)
                .setConnectionRequestTimeout(setTimeout)
                .setConnectTimeout(setTimeout)
                .setSocketTimeout(setTimeout)
                .build();
    }

    // trust-https start
    private static void trustAllHosts(HttpsURLConnection connection) {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();

            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
    }};
}
