package mx.itesm.dibujandounmaana

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction

class AppPago : Application()
{
    private val YOUR_CLIENT_ID: String = "AQLhj9PPvhk5aKi0qVt8-ieABhNwq4cS3xGOsppM5XUqanDT9ZexxKBF6ckysdvzcQXvcBJGN-NWPMFp"

    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = YOUR_CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "${BuildConfig.APPLICATION_ID}://paypalpay",
            currencyCode = CurrencyCode.USD,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )

        PayPalCheckout.setConfig(config)
    }
}
