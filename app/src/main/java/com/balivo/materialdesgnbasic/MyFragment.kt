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

        // Get a RequestQueue
        val mRequestQueue = VolleySingleton.getInstance(MyApplication.appContext).requestQueue

        val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Mission%20Viejo%2C%20ca%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"


        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    //Toast.makeText(activity,"Response is: ${response.substring(0, 500)}", Toast.LENGTH_LONG).show()
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                 Response.ErrorListener { error ->  textView.text = "ERROR: %s".format(error.toString())

                })

        // Add the request to the RequestQueue.
        //mRequestQueue.add(stringRequest)

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleySingleton.getInstance(MyApplication.appContext).addToRequestQueue(stringRequest)

        return layout
    }
}