package com.ahmedorabi.currencyapplication.features.rates_list.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.ahmedorabi.currencyapplication.R
import com.ahmedorabi.currencyapplication.core.data.api.Resource
import com.ahmedorabi.currencyapplication.core.domain.model.RateModel
import com.ahmedorabi.currencyapplication.core.domain.model.RatesResponse
import com.ahmedorabi.currencyapplication.databinding.FragmentRatesListBinding
import com.ahmedorabi.currencyapplication.features.rates_list.presentation.viewmodel.RatesListViewModel
import com.ahmedorabi.currencyapplication.features.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class RatesListFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentRatesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RatesListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRatesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
    }

    private fun initUI() {
        binding.detailsBtn.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_ratesListFragment_to_rateDetailsFragment)
        }

        binding.ratesSpinnerFrom.onItemSelectedListener = this
        binding.ratesSpinnerTo.onItemSelectedListener = this



        binding.rateEdFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {

                Timber.e(s.toString())
                if (s.toString().isNotEmpty()) {

                    viewModel.fromValue = s.toString()
                    makeConversion()
                } else {
                    binding.rateEdTo.setText("0")
                    //  binding.planetsEd.setText("0")
                }

            }

        })

        binding.rateEdTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {

                Timber.e(s.toString())
                if (s.toString().isNotEmpty()) {
//                    val toValue = s.toString()
//                    val result = toValue.toDouble() * viewModel.from.value
//
//                    binding.rateEdFrom.setText(result.toString())
                    viewModel.toValue = s.toString()
                } else {
                    //binding.rateEdFrom.setText("0")
                    //  binding.planetsEd.setText("0")
                }

            }

        })


        binding.swapBtn.setOnClickListener {
            if (!viewModel.isSwap) {
                Timber.e("true")
                binding.ratesSpinnerFrom.setSelection(viewModel.toPosition)
                binding.rateEdFrom.setText(viewModel.toValue)
                binding.ratesSpinnerTo.setSelection(viewModel.fromPosition)
                binding.rateEdTo.setText(viewModel.fromValue)
               // viewModel.isSwap = true
            } else {
                Timber.e("false")
                binding.ratesSpinnerTo.setSelection(viewModel.fromPosition)
                binding.rateEdTo.setText(viewModel.from.rateValue.toString())
                viewModel.isSwap = false
            }
        }

    }

    private fun makeConversion(){
        if (viewModel.fromValue.isNotEmpty()){
            val result = viewModel.getExchangeRate(viewModel.fromValue.toDouble())
            binding.rateEdTo.setText(String.format("%.4f", result))
        }
    }


    private fun observeViewModel() {

        lifecycleScope.launch {
            viewModel.ratesResponse.observe(viewLifecycleOwner) { userState ->

                when (userState.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        hideLoading()
                        Timber.e(userState.data.toString())
                        setAdapters(userState.data!!)

                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Toast.makeText(activity, userState.message, Toast.LENGTH_LONG).show()
                    }

                }
            }
        }

    }

    private fun setAdapters(ratesResponse: RatesResponse) {
        val rateModelList = ArrayList<RateModel>()
        ratesResponse.rates.forEach {
            if (it.key == "AED" || it.key == "EUR" || it.key == "EGP"  || it.key == "USD" ){
                rateModelList.add(RateModel(name = it.key, rateValue = it.value))

            }
        }

        val rateAdapter: ArrayAdapter<RateModel> =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                rateModelList
            )

        rateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.ratesSpinnerFrom.adapter = rateAdapter
        binding.ratesSpinnerTo.adapter = rateAdapter

        EspressoIdlingResource.decrement()
    }


    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressbar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Timber.e(position.toString())
        when (parent?.id) {
            R.id.rates_spinner_from -> {
                val from = parent.selectedItem as RateModel
                viewModel.from = from
                viewModel.fromPosition = position
                makeConversion()
            }

            R.id.rates_spinner_to -> {
                val to = parent.selectedItem as RateModel
                viewModel.to = to
                viewModel.toPosition = position
                makeConversion()
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}