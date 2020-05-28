package com.longbei.lwebview.intercepter;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 */
public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originResponse = chain.proceed(request);
        return originResponse.newBuilder().removeHeader("pragma").removeHeader("Cache-Control")
                .header("Cache-Control","max-age=3153600000").build();
    }
}
