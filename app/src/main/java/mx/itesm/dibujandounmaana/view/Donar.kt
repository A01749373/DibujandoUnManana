package mx.itesm.dibujandounmaana.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import mx.itesm.dibujandounmaana.viewmodel.DonarVM
import mx.itesm.dibujandounmaana.R
import mx.itesm.dibujandounmaana.databinding.DonarFragmentBinding
import mx.itesm.dibujandounmaana.databinding.NavContactanosFragmentBinding
import mx.itesm.dibujandounmaana.viewmodel.ContactanosVM
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
import mx.itesm.dibujandounmaana.model.Donar



class Donar : Fragment() {

    private val viewModel: DonarVM by viewModels()
    private lateinit var binding: DonarFragmentBinding

    companion object {
        fun newInstance() = Donar()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val  payPalButton = findViewById<PayPalButton>(R.id.payPalButton)
        val preferencias = this.requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val usuarioCorreo = preferencias.getString("Correo", "-1")

        binding.payPalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(
                        userAction = UserAction.PAY_NOW
                    ),

                    purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.USD,
                                value = binding.etCantidad.text.toString()
                            )
                        )
                    )
                )

                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                    println("CaptureOrderResult: $captureOrderResult")
                    Toast.makeText(context, "Pago exitoso", Toast.LENGTH_LONG).show()

                    //configurarObservadores()
                    //configurarEventos()

                    //Enviar Donacion
                    val nuevaDonacion = Donar(
                        binding.spinner2.selectedItem.toString(),
                        binding.etCantidad.text.toString().toInt(),
                        usuarioCorreo.toString(),
                        676
                    )
                    viewModel.enviarDonacion(nuevaDonacion)
                    viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
                        binding.tvEst.text = respuesta
                    }
                }
            },
            onCancel = OnCancel {
                Log.d("OnCancel", "Buyer canceled the PayPal experience.")
                println("Buyer canceled the PayPal experience.")
                Toast.makeText(context, "Donación cancelada", Toast.LENGTH_LONG).show()
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
                println("Error: $errorInfo")
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        )

    }
    /*
    private fun configurarObservadores() {
        viewModel.respuesta.observe(viewLifecycleOwner) { respuesta ->
            binding.tvEst.text = respuesta
        }
    }

    private fun configurarEventos() {
        binding.payPalButton.setOnClickListener {
            val nuevaDonacion= Donar(binding.spinner2.selectedItem.toString(),
                binding.etCantidad.text.toString())

            viewModel.enviarDonacion(nuevaDonacion)
        }
    }*/

/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonarVM::class.java)
        // TODO: Use the ViewModel
    }
*/
}