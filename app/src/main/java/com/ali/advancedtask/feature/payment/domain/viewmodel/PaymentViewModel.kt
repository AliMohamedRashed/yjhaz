package com.ali.advancedtask.feature.payment.domain.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ali.advancedtask.feature.payment.domain.paymentusecase.PaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel  @Inject constructor(
    private val paymentUseCase: PaymentUseCase
) : ViewModel(){
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
        _toastMessage.value = "Text copied to clipboard"
    }
    fun openWhatsApp(context: Context, phoneNumber: String) {
        val uri = Uri.parse("https://wa.me/+20$phoneNumber")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            _toastMessage.value = "WhatsApp not installed"
        }
    }

    fun openFawrySdk(activity: FragmentActivity) {
        paymentUseCase.launchFawrySdk(
            activity = activity,
            baseUrl = "https://atfawry.fawrystaging.com/",
            customerName = "testName",
            customerEmail = "test@test.com",
            customerMobile = "",
            customerProfileId = "7117",
            merchantCode = "+/IAAY2notgLsdUB9VeTFg==",
            merchantSecretCode = "69826c87-963d-47b7-8beb-869f7461fd93",
            onSuccess = { msg -> _toastMessage.postValue("Payment Success: $msg") },
            onFailure = { error -> _toastMessage.postValue("Payment Failed: $error") }
        )
    }
    fun onCameraPermissionGranted() {
        _toastMessage.value = "Camera permission granted, start scanning"
    }

    fun onCameraPermissionDenied() {
        _toastMessage.value = "Camera permission is required to scan QR codes."
    }

}