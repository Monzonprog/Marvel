package com.openbank.marvel.presentation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.openbank.marvel.R
import com.openbank.marvel.databinding.FragmentDetailBinding
import com.openbank.marvel.presentation.result.MarvelResult
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModel<DetailViewModel> {
        parametersOf(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.character.collect { result ->
                when (result) {
                    is MarvelResult.Loading -> {
                        binding.progress.isVisible = true
                    }
                    is MarvelResult.Success -> {
                        binding.progress.isVisible = false
                        binding.image.load(result.data.thumbnailPath)
                        binding.toolbar.title = result.data.name
                        binding.description.text = String.format(getString(R.string.description), result.data.description)
                        binding.modified.text = String.format(getString(R.string.modified), result.data.modified)
                        binding.comics.text = String.format(getString(R.string.comics), result.data.comics.joinToString("\n"))
                        binding.series.text = String.format(getString(R.string.series), result.data.series.joinToString("\n"))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}