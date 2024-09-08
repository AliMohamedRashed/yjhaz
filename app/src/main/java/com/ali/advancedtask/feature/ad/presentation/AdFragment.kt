package com.ali.advancedtask.feature.ad.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ali.advancedtask.R
import com.ali.advancedtask.core.State
import com.ali.advancedtask.databinding.FragmentAdBinding
import com.ali.advancedtask.feature.ad.domain.viewmodel.AdViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdFragment : Fragment() {

    private var player: ExoPlayer? = null
    private val adViewModel: AdViewModel by viewModels()

    private lateinit var mNavController: NavController

    private var _binding: FragmentAdBinding? = null
    private val binding get() = _binding!!
    private var isFullscreen = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = findNavController()

        player = ExoPlayer.Builder(requireContext()).build().apply {
            binding.fragmentAdPlayerView.player = this
        }

        lifecycleScope.launch {
            adViewModel.state.collect { state ->
                when (state) {
                    is State.Success -> {
                        setupMediaPlayer(state.data.videoUrl)
                        binding.fragmentAdTvTitle.text = state.data.title
                        binding.fragmentAdTvAdTitle.text = state.data.title
                        binding.fragmentAdTvAdText.text = state.data.text
                        binding.fragmentAdProgressBar.visibility = View.GONE
                    }
                    is State.Loading -> {
                        binding.fragmentAdProgressBar.visibility = View.VISIBLE
                    }
                    is State.Error -> {
                        binding.fragmentAdProgressBar.visibility = View.GONE
                    }
                }
            }
        }

        setupPlayerControls()

        binding.fragmentAdBtn.setOnClickListener {
            val action = AdFragmentDirections.actionAdFragmentToPaymentFragment()
            mNavController.navigate(action)
        }

    }

    private fun setupMediaPlayer(videoUrl: String) {
        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }


    private fun setupPlayerControls() {
        binding.fragmentAdPlayerView.findViewById<View>(R.id.exo_play).setOnClickListener {
            if (player?.isPlaying == true) {
                player?.pause()
                (it as ImageView).setImageResource(R.drawable.ic_play)  // Change icon to play
            } else {
                player?.play()
                (it as ImageView).setImageResource(R.drawable.ic_pause)  // Change icon to pause
            }
        }

        binding.fragmentAdPlayerView.findViewById<View>(R.id.exo_rew).apply {
            setOnClickListener {
                player?.seekBack()
            }
        }

        binding.fragmentAdPlayerView.findViewById<View>(R.id.exo_ffwd).apply {
            setOnClickListener {
                player?.seekForward()
            }
        }

        binding.fragmentAdPlayerView.findViewById<View>(R.id.exo_fullscreen).apply {
            visibility = View.VISIBLE
            setOnClickListener {
                toggleFullscreen()
            }
        }

    }

    private fun toggleFullscreen() {
        if (isFullscreen) {
            exitFullscreen()
        } else {
            enterFullscreen()
        }
    }

    private fun enterFullscreen() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        updateFullscreenUI(true)
        binding.fragmentAdPlayerView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.fragmentAdPlayerView.updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
        isFullscreen = true
    }

    private fun exitFullscreen() {
        updateFullscreenUI(false)
        binding.fragmentAdPlayerView.updateLayoutParams { height = ViewGroup.LayoutParams.WRAP_CONTENT }
        isFullscreen = false
    }

    private fun updateFullscreenUI(enable: Boolean) {
        val windowInsetsController = WindowInsetsControllerCompat(requireActivity().window, binding.root)
        if (enable) {
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        player?.release()
        player = null
    }

}