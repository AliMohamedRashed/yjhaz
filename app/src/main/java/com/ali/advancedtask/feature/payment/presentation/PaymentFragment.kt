package com.ali.advancedtask.feature.payment.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.ali.advancedtask.R
import com.ali.advancedtask.databinding.FragmentPaymentBinding
import com.ali.advancedtask.feature.activities.MainActivity
import com.ali.advancedtask.feature.payment.domain.viewmodel.PaymentViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint

const val CASH = "Cash"
const val CODE = "Code"
const val FAWRY = "Fawry"

@AndroidEntryPoint
class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentViewModel by viewModels()

    private var selectedLayout: LinearLayout? = null

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
       binding.fragmentPaymentEtCode.setText(result.contents)
    }

    private val requestCameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            viewModel.onCameraPermissionGranted()
            startQrScan()
        } else {
            viewModel.onCameraPermissionDenied()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeToastMessage()

        paymentCashSelectionPressed()
        paymentCodeSelectionPressed()
        paymentFawrySelectionPressed()

        whatsAppIconPressed()
        copyIconPressed()
        qrCodeIconPressed()
        fawryPaymentButtonPressed()
    }

    private fun fawryPaymentButtonPressed(){
        binding.fragmentPaymentBtnDetailsFawry.setOnClickListener {
            viewModel.openFawrySdk(requireActivity())
        }
    }

    private fun qrCodeIconPressed(){
        binding.fragmentPaymentIvQrcodescan.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startQrScan()
            } else {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun whatsAppIconPressed(){
        binding.fragmentPaymentIvCashWhats.setOnClickListener {
            viewModel.openWhatsApp(requireContext(), binding.fragmentPaymentTvDetailsCashPhoneOne.text.toString())
        }
    }
    private fun copyIconPressed(){
        binding.fragmentPaymentIvCashCopy.setOnClickListener {
            viewModel.copyToClipboard(requireContext(), binding.fragmentPaymentTvDetailsCashPhoneTwo.text.toString())
        }

    }

    private fun observeToastMessage(){
        viewModel.toastMessage.observe(viewLifecycleOwner) { message ->
            MainActivity.showToast(message)
        }
    }

    private fun paymentCashSelectionPressed(){
        binding.fragmentPaymentLlCash.setOnClickListener {
            updateSelection(binding.fragmentPaymentLlCash)
            showCheckIcon(CASH)
            showDetails(CASH)
        }
    }
    private fun paymentCodeSelectionPressed(){
        binding.fragmentPaymentLlCode.setOnClickListener {
            updateSelection(binding.fragmentPaymentLlCode)
            showCheckIcon(CODE)
            showDetails(CODE)
        }
    }
    private fun paymentFawrySelectionPressed(){
        binding.fragmentPaymentLlFawry.setOnClickListener {
            updateSelection(binding.fragmentPaymentLlFawry)
            showCheckIcon(FAWRY)
            showDetails(FAWRY)
        }
    }

    private fun startQrScan() {
        val options = ScanOptions().apply {
            setPrompt("Scan a QR Code")
            setBeepEnabled(true)
            setCameraId(0)
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        }
        barcodeLauncher.launch(options)
    }
    private fun updateSelection(selected: LinearLayout) {
        selectedLayout?.setBackgroundResource(R.drawable.custom_payment_background_unselected)
        selected.setBackgroundResource(R.drawable.custom_payment_background_selected)
        selectedLayout = selected
    }

    private fun showCheckIcon(method: String) {
        when (method) {
            CASH -> {
                binding.fragmentPaymentIvCashSelectedIcon.visibility = View.VISIBLE
                binding.fragmentPaymentIvCodeSelectedIcon.visibility = View.GONE
                binding.fragmentPaymentIvFawrySelectedIcon.visibility = View.GONE
            }
           CODE -> {
                binding.fragmentPaymentIvCodeSelectedIcon.visibility = View.VISIBLE
                binding.fragmentPaymentIvCashSelectedIcon.visibility = View.GONE
                binding.fragmentPaymentIvFawrySelectedIcon.visibility = View.GONE
            }
            FAWRY -> {
                binding.fragmentPaymentIvFawrySelectedIcon.visibility = View.VISIBLE
                binding.fragmentPaymentIvCashSelectedIcon.visibility = View.GONE
                binding.fragmentPaymentIvCodeSelectedIcon.visibility = View.GONE
            }
        }
    }
    private fun showDetails(method: String){
        when (method) {
            CASH -> {
                binding.fragmentPaymentTvDetailsCashInstructions.visibility = View.VISIBLE
                binding.fragmentPaymentTvDetailsCashPhoneOne.visibility = View.VISIBLE
                binding.fragmentPaymentTvDetailsCashPhoneTwo.visibility = View.VISIBLE
                binding.fragmentPaymentIvCashWhats.visibility = View.VISIBLE
                binding.fragmentPaymentIvCashCopy.visibility = View.VISIBLE
                //Disable Code Fields
                binding.fragmentPaymentTvDetailsCodeInform.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCodeInstructions.visibility = View.GONE
                binding.fragmentPaymentEtCode.visibility = View.GONE
                binding.fragmentPaymentIvQrcodescan.visibility = View.GONE
                binding.fragmentPaymentBtnDetailsCode.visibility = View.GONE
                //Disable Fawry Fields
                binding.fragmentPaymentTvDetailsFawryInstructions.visibility = View.GONE
                binding.fragmentPaymentEtDetailsFawryRechargeValue.visibility = View.GONE
                binding.fragmentPaymentBtnDetailsFawry.visibility = View.GONE
            }
            CODE -> {
                binding.fragmentPaymentTvDetailsCodeInform.visibility = View.VISIBLE
                binding.fragmentPaymentTvDetailsCodeInstructions.visibility = View.VISIBLE
                binding.fragmentPaymentEtCode.visibility = View.VISIBLE
                binding.fragmentPaymentIvQrcodescan.visibility = View.VISIBLE
                binding.fragmentPaymentBtnDetailsCode.visibility = View.VISIBLE
                //Disable Cash Fields
                binding.fragmentPaymentTvDetailsCashInstructions.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCashPhoneOne.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCashPhoneTwo.visibility = View.GONE
                binding.fragmentPaymentIvCashWhats.visibility = View.GONE
                binding.fragmentPaymentIvCashCopy.visibility = View.GONE
                //Disable Fawry Fields
                binding.fragmentPaymentTvDetailsFawryInstructions.visibility = View.GONE
                binding.fragmentPaymentEtDetailsFawryRechargeValue.visibility = View.GONE
                binding.fragmentPaymentBtnDetailsFawry.visibility = View.GONE
            }
            FAWRY -> {
                binding.fragmentPaymentTvDetailsFawryInstructions.visibility = View.VISIBLE
                binding.fragmentPaymentEtDetailsFawryRechargeValue.visibility = View.VISIBLE
                binding.fragmentPaymentBtnDetailsFawry.visibility = View.VISIBLE
                //Disable Cash Fields
                binding.fragmentPaymentTvDetailsCashInstructions.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCashPhoneOne.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCashPhoneTwo.visibility = View.GONE
                binding.fragmentPaymentIvCashWhats.visibility = View.GONE
                binding.fragmentPaymentIvCashCopy.visibility = View.GONE
                //Disable Code Fields
                binding.fragmentPaymentTvDetailsCodeInform.visibility = View.GONE
                binding.fragmentPaymentTvDetailsCodeInstructions.visibility = View.GONE
                binding.fragmentPaymentEtCode.visibility = View.GONE
                binding.fragmentPaymentIvQrcodescan.visibility = View.GONE
                binding.fragmentPaymentBtnDetailsCode.visibility = View.GONE
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
