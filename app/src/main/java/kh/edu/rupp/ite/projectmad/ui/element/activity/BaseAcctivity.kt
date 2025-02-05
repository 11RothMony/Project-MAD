package kh.edu.rupp.ite.visitme.ui.element.activity

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartInNavigationBarViewModel

@Suppress("DEPRECATION")
open class BaseActivity: AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    fun showLoading() {
        if(progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }

        progressDialog!!.show()
    }

    fun hideLoading() {
        progressDialog?.hide()
    }

    fun showAlert(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton("OK", null)
        dialog.show()
    }

}