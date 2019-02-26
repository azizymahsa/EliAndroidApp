package com.eligasht.reservation.api.retro;


import com.eligasht.reservation.conf.APIConf;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elham.bonyani on 2/28/2017.
 */

public class ServiceGenerator {

    private static OkHttpClient httpClient = new OkHttpClient();
    public static String apiBaseUrl = APIConf.CORE_REST_API_URI;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(SingletonService.getInstance().getOkHttpClient());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(getUnsafeOkHttpClient()).build();
        return retrofit.create(serviceClass);
    }

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .addNetworkInterceptor(new AddHeaderInterceptor())
                    .sslSocketFactory(sslSocketFactory)
                    .readTimeout(60, TimeUnit.MINUTES)
                    .writeTimeout(60, TimeUnit.MINUTES)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response
        intercept(Interceptor.Chain chain) throws IOException {

            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Authorization", "bearer rpgB0g3q01GR7Hk7ua5MB9nn_WDFpzv6NHPj14HB00T-ceACFqbXgg7vYYIBOl70r3Vl-mK95q0Qno1T-Gei_LLHDkSwMdIcHmQ1kTWAbGp0k27cl1I_hzZ7clRlPHemXewXNd31mxmORFr-clnP6oqfyV8uF8wm53xkRkvZEUmckqHXjEEdpvPxHiEvE2YTip6Rv_eHbYdMpU5wRTSZ0gZcIvzC_5pfzaRiOH-zGoEGBbcDUvAoKj5_TIlLRf_e");

            return chain.proceed(builder.build());
        }
    }
}
