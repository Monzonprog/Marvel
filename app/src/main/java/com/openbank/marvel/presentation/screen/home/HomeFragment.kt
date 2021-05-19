package com.openbank.marvel.presentation.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.openbank.marvel.databinding.FragmentHomeBinding
import com.openbank.marvel.presentation.result.MarvelResult
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    private val charactersAdapter: CharactersAdapter
        get() = binding.characters.adapter as CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCharacters()

        lifecycleScope.launchWhenCreated {
            viewModel.characters.collect { result ->
                when (result) {
                    is MarvelResult.Loading -> {
                        binding.progress.isVisible = true
                        binding.empty.isVisible = false
                    }
                    is MarvelResult.Success -> {
                        binding.progress.isVisible = false
                        binding.empty.isVisible = result.data.isEmpty()
                        charactersAdapter.submitList(result.data.toMutableList())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCharacters() {
        binding.characters.setHasFixedSize(true)
        binding.characters.adapter = CharactersAdapter(
            characterClicked = viewModel::characterClicked
        )
    }
}