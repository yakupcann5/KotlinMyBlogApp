package com.example.kotlinandroidassigment.fragment

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinandroidassigment.R
import com.example.kotlinandroidassigment.service.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profil.*
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ProfilFragment() : Fragment() {

    private lateinit var button:Button
    lateinit var fNa:TextView
    private lateinit var sNa : TextView
    private lateinit var eMa:TextView
    private lateinit var pic:TextView
    private lateinit var imView : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url : String = Utils.PROFIL_URL
        button = view.findViewById(R.id.buttonProfil)
        fNa = view.findViewById(R.id.fnameView)
        sNa = view.findViewById(R.id.snameView)
        eMa = view.findViewById(R.id.emailView)
        pic = view.findViewById(R.id.urlView)
        imView = view.findViewById(R.id.imageView)
        buttonProfil.setOnClickListener {
            background().execute(Utils.PROFIL_URL)
        }
    }
    inner class background() : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val url = URL("https://randomuser.me/api/")
            val connection : HttpURLConnection
            var bufferedReader: BufferedReader
            var inputStream : InputStream
            try {
                connection = url.openConnection() as HttpURLConnection
                connection.connect()
                inputStream = connection.inputStream
                bufferedReader = inputStream.bufferedReader()
                var dosya = ""
                bufferedReader.forEachLine {
                    dosya = it
                }
                return dosya
            } catch (e : IOException) {
                print(e.localizedMessage)
            }
            return "error"
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                var fName : String = ""
                var sName : String = ""
                var eMail : String = ""
                var pUrl : String = ""
                var json : JSONObject = JSONObject(result)
                fName = json.getJSONArray("results").get(0).toString()
                json = JSONObject(fName)
                fName = json.getJSONObject("name").getString("first")
                sName = json.getJSONObject("name").getString("last")
                eMail = json.getString("email")
                pUrl = json.getJSONObject("picture").getString("large")

                fNa.text = fName
                sNa.text = sName
                eMa.text = eMail
                pic.text = pUrl
                Picasso.get().load(pUrl).into(imView)
            }catch (e:Exception){
                println(e.localizedMessage)
            }
        }
    }
}