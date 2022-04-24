package com.ahmedorabi.currencyapplication.features.currency_details.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import com.ahmedorabi.currencyapplication.databinding.FragmentCurrencyDetailsBinding
import com.ahmedorabi.currencyapplication.features.currency_details.presentation.adapter.CurrencyAdapter
import com.ahmedorabi.currencyapplication.features.currency_details.presentation.viewmodel.CurrencyDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class CurrencyDetailsFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyDetailViewModel by viewModels()

    private val pupularList = ArrayList<RateModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val x = it.getParcelableArrayList<RateModel>("PopularCurrencies")
            pupularList.addAll(x!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = pupularList.toString()

        viewModel.ratesResponse.observe(viewLifecycleOwner) {
            it?.let { currencies ->
                Timber.e(currencies.toString())
                val adapter = CurrencyAdapter()
                binding.recyclerViewMain.adapter = adapter
                adapter.submitList(currencies)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}