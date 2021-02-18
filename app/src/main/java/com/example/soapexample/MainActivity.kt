package com.example.soapexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var txtVersion:TextView? = null
    var txtUseName:TextView? = null
    var edCodeUser:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Init()
    }

    fun Init() {
        txtVersion = findViewById(R.id.txtVersion)
        txtUseName = findViewById(R.id.txtUserName)
        edCodeUser = findViewById(R.id.edCodeUser)
    }

    fun onClickGetDate(view: View) {


        var mTh = Thread {
            var mRes = ""
            mRes = QueryUtils.GetVersion()

            if(!mRes.isBlank()) {
                runOnUiThread {
                    txtVersion?.text = "Версия протокола : $mRes"
                    Toast.makeText(this,"Получение версии протокола.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        mTh.start()

    }

    fun onClickGetUser(view: View) {
        var mTh = Thread {
            val mCodeUser = edCodeUser?.text.toString()
            val mRes = QueryUtils.GetUserName(mCodeUser)

            if(!mRes.isBlank()) {
                runOnUiThread {
                    txtUseName?.text = "Имя пользователя : $mRes"
                    Toast.makeText(this, "Получено имя пользователя.", Toast.LENGTH_SHORT).show()
                }

            }

        }

        mTh.start()

    }

    fun onClickGetUsers(view: View) {
        var mTh = Thread {
            val mRes = QueryUtils.GetUsers()

            if(!mRes.isBlank()) {
                runOnUiThread {
                    //txtUseName?.text = "Имя пользователя : $mRes"
                    Toast.makeText(this, "Получен список пользователей.", Toast.LENGTH_SHORT).show()
                }

            }

        }

        mTh.start()

    }

}