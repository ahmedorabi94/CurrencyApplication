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
import com.ahmedorabi.currencyapplication.R
import com.ahmedorabi.currencyapplication.core.data.api.Resource
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
         observeViewModel()

        binding.planetsSpinner.onItemSelectedListener = this
        binding.planetsSpinnerTwo.onItemSelectedListener = this

        binding.planetsEd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {

                Timber.e(s.toString())
                if (s.toString().isNotEmpty()){
                    val fromValue = s.toString()
                    val result = fromValue.toInt() * 20

                    binding.planetsEdTwo.setText(result.toString())
                }else{
                    binding.planetsEdTwo.setText("0")
                  //  binding.planetsEd.setText("0")
                }

            }

        })

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

                        val keys = userState.data?.rates?.keys
                        val values = userState.data?.rates?.values
                        Timber.e(values.toString())

                        val userAdapter: ArrayAdapter<String> =
                            ArrayAdapter(requireContext(),  android.R.layout.simple_spinner_item, keys!!.toList())

                        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        binding.planetsSpinner.adapter = userAdapter
                        binding.planetsSpinnerTwo.adapter = userAdapter

                        EspressoIdlingResource.decrement()
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Toast.makeText(activity, userState.message, Toast.LENGTH_LONG).show()
                    }

                }
            }
        }

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
        var from = "EUR"
        var to = "EUR"
        when (parent?.id) {
            R.id.planets_spinner -> {
                from = parent.selectedItem.toString()

            }

            R.id.planets_spinner_Two -> {
                to = parent.selectedItem.toString()

            }
        }
        Timber.e("From $from")
        Timber.e("To $to")




    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}