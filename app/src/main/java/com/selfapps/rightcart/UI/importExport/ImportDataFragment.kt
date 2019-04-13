package com.selfapps.rightcart.UI.importExport

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.selfapps.rightcart.R
import com.selfapps.rightcart.UI.CartViewModelFactory
import kotlinx.android.synthetic.main.import_data_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ImportDataFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: CartViewModelFactory by instance()

    companion object {
        fun newInstance() = ImportDataFragment()
    }

    private lateinit var viewModel: ImportDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.import_data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImportDataViewModel::class.java)
        // TODO: Use the ViewModel

        btn_import.setOnClickListener {
            val intent = Intent()
                .setType("text/csv")
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file

            tv_selected.text =
                "string = ${selectedFile.toString()}, PATH = ${selectedFile?.path}, ePATH = ${selectedFile?.encodedPath},  ath = ${selectedFile?.authority},"
            viewModel.importToDb(selectedFile)

        }
    }

}
