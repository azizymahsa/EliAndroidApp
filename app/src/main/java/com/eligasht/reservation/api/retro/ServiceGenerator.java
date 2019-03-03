package com.eligasht.reservation.api.retro;


import com.eligasht.reservation.conf.APIConf;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;

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
            builder.addHeader("Authorization", Const.TOKEN);//"bearer mpdjYhla4nncC-1L2z_CFx5TpzC-VEcJ_dLoEgL28svnEpVGe7xlUyLVVV2aa23cXKiZWYQYLrBS0VRQHb3KPU2GGvs8pE2ZB0dqHwPEAp7nvpGDQudOhySmiZTwgMNXpx1SRNwn-1AYgw1rxfF58nPzSyhwtc7v5MVmNyiRrk_7e6gojEsuG_iSGViww_fELBJC6zRy6ob2Pd-yhA-dr1m6U7d9CX_y7Cf7PZq72uQ8u_dVPNBYh5eFnKjsLS6m");

            return chain.proceed(builder.build());
        }
    }
}
