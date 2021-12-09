package com.example.ets7100

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.search_fragment.button
import kotlinx.android.synthetic.main.search_fragment.editText

class SearchFragment : Fragment(){

    private val TAG = "SearchFragment"

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        val editText: EditText? = editText
        var paikkakunta = ""
        this.button?.setOnClickListener {
            if (editText != null) {
                editText.text.toString().also { paikkakunta = it }
                Log.d("paikkakunta", paikkakunta)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater!!.inflate(R.layout.search_fragment,container,false)
    }

}