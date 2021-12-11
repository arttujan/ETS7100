package com.example.ets7100

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment(){

    private lateinit var comm: Communicator
    private val TAG = "SearchFragment"

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return inflater!!.inflate(R.layout.search_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comm = requireActivity() as Communicator
        btnSend.setOnClickListener { view ->
            val paikkakunta = editText.text.toString()
            comm.passDataCom(editText.text.toString())
            Log.d("VALITTU PAIKKAKUNTA", paikkakunta)
        }
    }

}