package com.rafaelsouza.listreposgithub.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory<T : BaseViewModel> @Inject constructor(viewModel: T) : ViewModelProvider.Factory {

    private val mViewModel: T = viewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mViewModel as? T ?: throw IllegalArgumentException("Unknow class name")
    }

}