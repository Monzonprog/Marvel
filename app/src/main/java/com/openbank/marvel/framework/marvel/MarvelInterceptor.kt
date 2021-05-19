package com.openbank.marvel.framework.marvel

import com.openbank.marvel.framework.extension.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class MarvelInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis()
        val request = chain.request()
        return chain.proceed(
            request.newBuilder().url(
                request.url.newBuilder()
                    .addQueryParameter(MarvelAPI.API_KEY_QUERY_PARAM, MarvelAPI.PUBLIC_KEY)
                    .addQueryParameter(MarvelAPI.HASH_QUERY_PARAM, "${ts}${MarvelAPI.PRIVATE_KEY}${MarvelAPI.PUBLIC_KEY}".md5())
                    .addQueryParameter(MarvelAPI.TS_QUERY_PARAM, ts.toString())
                    .build()
            ).build()
        )
    }
}