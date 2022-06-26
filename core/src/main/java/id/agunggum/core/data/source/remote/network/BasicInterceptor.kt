package id.agunggum.core.data.source.remote.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class BasicInterceptor (private var token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val originalHttpUrl: HttpUrl = req.url
        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("key", token)
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = req.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}