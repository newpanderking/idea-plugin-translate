package com.jason.plugin.util;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @version v 0, HttpUtils.java
 * @since 2019年11月27日 6:24 下午
 */
public class HttpUtils {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * thread factory name
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("httpUtil-pool-%d").build();

    /**
     * thread pool
     */
    private static ExecutorService threadPool = new ThreadPoolExecutor(3, 5,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 异步请求
     *
     * @param url
     * @param callBack
     */
    public static void doGetAsyn(String url, CallBack callBack) {
        threadPool.submit(() -> {
                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpGet get = new HttpGet(url);
                    HttpResponse response = client.execute(get);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                        String data = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
                        callBack.onRequestComplete(data);
                        logger.warn("data from you dao fan yi -> " + data);
                    }else {
                        logger.warn("http result is empty.");
                    }
                } catch (IOException e) {
                    logger.error("http get error.",e);
                }
            }
        );
    }

    /**
     * 内部接口 call back
     */
    public interface CallBack {
        void onRequestComplete(String result);
    }

}