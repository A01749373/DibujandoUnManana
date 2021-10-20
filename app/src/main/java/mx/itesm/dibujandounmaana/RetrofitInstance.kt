package mx.itesm.dibujandounmaana

import com.squareup.okhttp.ResponseBody
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            // Localhost Liam: 172.17.80.1
            // Localhost Jorge: 192.168.3.18
            // Localhost Amy: 192.168.39.163
            // Localhost Andrea:  192.168.100.28
            // Localhost Ari: 192.168.100.22
            .baseUrl("https://192.168.100.150:443")  // Localhost de la máquina donde corre el simulador
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())      // String. Int, etc
            .addConverterFactory(GsonConverterFactory.create())         // Json
            .build()
    }

    val servicioUsuarioApi: ServicioUsuarioApi by lazy {
        retrofit.create(ServicioUsuarioApi::class.java)
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {

                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {

                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val trustManagerFactory: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers: Array<TrustManager> =
                trustManagerFactory.trustManagers
            check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                "Unexpected default trust managers:" + trustManagers.contentToString()
            }

            val trustManager = trustManagers[0] as X509TrustManager

            val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustManager)
                builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
                builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}