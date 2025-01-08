package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.app.ProgressDialog
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

open class BaseFragment: Fragment() {

    fun showLoading() {
        val hostActivity = activity as BaseActivity?
        hostActivity?.showLoading()
    }

    fun hideLoading() {
        val hostActivity = activity as BaseActivity?
        hostActivity?.hideLoading()
    }

    fun showAlert(title: String, message: String) {
        val hostActivity = activity as BaseActivity?
        hostActivity?.showAlert(title, message)
    }

}