package com.example.jkapplication.view

import androidx.fragment.app.Fragment
import com.example.jkapplication.presenter.BasePresenter

abstract class BaseFragment:Fragment() {
    abstract val presenter: BasePresenter
}