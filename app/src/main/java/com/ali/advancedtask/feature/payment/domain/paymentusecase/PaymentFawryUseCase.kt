package com.ali.advancedtask.feature.payment.domain.paymentusecase

import androidx.fragment.app.FragmentActivity
import com.fawry.fawrypay.FawrySdk
import com.fawry.fawrypay.interfaces.FawryPreLaunch
import com.fawry.fawrypay.interfaces.FawrySdkCallbacks
import com.fawry.fawrypay.models.FawryLaunchModel
import com.fawry.fawrypay.models.LaunchCustomerModel
import com.fawry.fawrypay.models.LaunchMerchantModel
import javax.inject.Inject

class PaymentUseCase @Inject constructor(){

    fun launchFawrySdk(
        activity: FragmentActivity,
        baseUrl: String,
        customerName: String,
        customerEmail: String,
        customerMobile: String,
        customerProfileId: String,
        merchantCode: String,
        merchantSecretCode: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            FawrySdk.launchAnonymousSDK(
                // Use application context instead of activity context if possible
                activity,
                FawrySdk.Languages.ENGLISH,
                baseUrl,
                FawryLaunchModel(
                    launchCustomerModel = LaunchCustomerModel(
                        customerName = customerName,
                        customerEmail = customerEmail,
                        customerMobile = customerMobile,
                        customerProfileId = customerProfileId
                    ),
                    launchMerchantModel = LaunchMerchantModel(
                        merchantCode = merchantCode,
                        secretCode = merchantSecretCode,
                        merchantRefNum = "${System.currentTimeMillis()}"
                    ),
                    allow3DPayment = true,
                    chargeItems = arrayListOf(),
                    skipReceipt = false,
                    skipLogin = true,
                    payWithCardToken = true,
                    authCaptureMode = false,
                    allowVoucher = false,
                    signature = null,
                    paymentMethods = FawrySdk.PaymentMethods.ALL
                ),
                object : FawrySdkCallbacks {
                    override fun onPreLaunch(onPreLaunch: FawryPreLaunch) {
                        onPreLaunch.onContinue()
                    }

                    override fun onInit() {}

                    override fun onSuccess(msg: String, data: Any?) {
                        onSuccess(msg)
                    }

                    override fun onPaymentCompleted(msg: String, data: Any?) {
                        onSuccess(msg)
                    }

                    override fun onFailure(error: String) {
                        onFailure(error)
                    }
                }
            )
        } catch (e: Exception) {
            onFailure(e.message ?: "Unknown error")
        }
    }
}
