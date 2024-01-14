package com.example.infinitescrollexample.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var newBuilder  = request.newBuilder()
            .addHeader(
                "Authorization","Client-ID T3B6vHOS4CmnADT5E2vfT7h2z8_PtNm_lek_O9enSPI"
            ).build()

        return chain.proceed(newBuilder)
    }
}