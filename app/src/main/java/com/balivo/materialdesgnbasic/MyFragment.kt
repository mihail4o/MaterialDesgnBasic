package com.balivo.materialdesgnbasic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MyFragment: Fragment() {

    private lateinit var textView : TextView

    companion object {

        fun getInstance(position:Int):MyFragment {

            val myFragment = MyFragment()
            val args = Bundle()
            args.putInt("position", position)
            myFragment.arguments = args

            return myFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater!!.inflate(R.layout.fragment_my, container, false)

        textView = layout.findViewById(R.id.position) as TextView

        val bundle = arguments

        if (bundle!=null) {
            textView.text= "The Page Selected Is " + bundle.getInt("position").toString()
        }

        val mRequestQueue = Volley.newRequestQueue(activity)
        val url = "http://www.php.net"


        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    //Toast.makeText(activity,"Response is: ${response.substring(0, 500)}", Toast.LENGTH_LONG).show()
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                Response.ErrorListener { error ->  Toast.makeText(activity,"That didn't work! Error: $error",
                        Toast.LENGTH_LONG).show() })

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest)

        return layout
    }
}