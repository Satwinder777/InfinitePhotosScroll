package com.example.infinitescrollexample.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescrollexample.R
import com.example.infinitescrollexample.adapter.MyAdapter
import com.example.infinitescrollexample.adapter.MyLoaderAdapter
import com.example.infinitescrollexample.databinding.ActivityMainBinding
import com.example.infinitescrollexample.viewmodel.MyViewModel
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PayloadHelper
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PaymentResultWithDataListener, ExternalWalletListener {

    lateinit var binding: ActivityMainBinding
    val viewmodel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val co = Checkout()
        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")
        Checkout.sdkCheckIntegration(this)
        binding.razopay.setOnClickListener {

//            makePayment()
//            startPayment() //tested and commented due to does not have credentials
        }
        lifecycleScope.launch {
            viewmodel.getPhotos(2)
        }
        viewmodel.livePhotoslist.observe(this, Observer {

            if(it!=null){
                it.forEach{
                    Log.e("satta112345", "onCreate: ${it.id}", )
                }
            }

        })
        binding.nextbtn.setOnClickListener {
            startActivity(Intent(this,RoomActivity::class.java))
        }
        var rc = findViewById<RecyclerView>(R.id.MyRC)
        var adapter = MyAdapter ()
//        adapter.

//        val concatAdapter = ConcatAdapter(
//            adapter,
////            ItemLoadStateAdapter(::retry) // Pass a retry function
//            MyLoaderAdapter()
//
//        )
//        adapter.withLoadStateFooter(MyLoaderAdapter())
//        var loader = findViewById<ProgressBar>(R.id.progg)
        rc.adapter = adapter.withLoadStateHeaderAndFooter(MyLoaderAdapter(),MyLoaderAdapter())
//        adapter.addLoadStateListener { loadState ->
//            when(loadState as LoadState){
//                LoadState.Loading->{
//                    Handler().postDelayed({
//                        loader.visibility = View.VISIBLE
//                    },1000)
//                }
////                  LoadState.NotLoading ->{}
////                is LoadState.Error->{}
//                else->{  Handler().postDelayed({
//                    loader.visibility = View.GONE
//                },1000)}
//            }
//            // Handle other states as needed
//        }
        viewmodel.data.asLiveData().observe(this) { pagingData ->

            adapter.submitData(lifecycle, pagingData)

        }
    }

    private fun startPayment() {
        /*
        *  You need to pass the current activity to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","100")//pass amount in currency subunits
//
//            val retryObj =  JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","satwinder88721@gmail.com")
            prefill.put("contact","9877995250")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
    private fun makePayment() {
        val payloadHelper = PayloadHelper("INR", 100, "order_XXXXXXXXX")
        payloadHelper.name = "Gaurav Kumae"
        payloadHelper.description = "Description"
        payloadHelper.prefillEmail = "gaurav.kumar@example.com"
        payloadHelper.prefillContact = "9000090000"
        payloadHelper.prefillCardNum = "4111111111111111"
        payloadHelper.prefillCardCvv = "111"
        payloadHelper.prefillCardExp = "11/24"
        payloadHelper.prefillMethod = "card"
        payloadHelper.prefillName = "MerchantName"
        payloadHelper.sendSmsHash = true
        payloadHelper.retryMaxCount = 4
        payloadHelper.retryEnabled = true
        payloadHelper.color = "#000000"
        payloadHelper.allowRotation = true
        payloadHelper.rememberCustomer = true
        payloadHelper.timeout = 10
        payloadHelper.redirect = true
        payloadHelper.recurring = "1"
        payloadHelper.subscriptionCardChange = true
        payloadHelper.customerId = "cust_XXXXXXXXXX"
        payloadHelper.callbackUrl = "https://accepts-posts.request"
        payloadHelper.subscriptionId = "sub_XXXXXXXXXX"
        payloadHelper.modalConfirmClose = true
        payloadHelper.backDropColor = "#ffffff"
        payloadHelper.hideTopBar = true
        payloadHelper.notes = JSONObject("{\"remarks\":\"Discount to cusomter\"}")
        payloadHelper.readOnlyEmail = true
        payloadHelper.readOnlyContact = true
        payloadHelper.readOnlyName = true
        payloadHelper.image = "https://www.razorpay.com"
        // these values are set mandatorily during object initialization. Those values can be overridden like this
        payloadHelper.amount=100
        payloadHelper.currency="INR"
        payloadHelper.orderId = "order_XXXXXXXXXXXXXX"
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "payment success !!", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "payment error !!", Toast.LENGTH_SHORT).show()

    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "onExternalWalletSelected success !!", Toast.LENGTH_SHORT).show()

    }
}