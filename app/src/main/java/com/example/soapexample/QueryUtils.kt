package com.example.soapexample

import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.xml.sax.InputSource
import javax.xml.parsers.DocumentBuilderFactory


object QueryUtils {
    private val NAMESPACE = "http://www.wellit.pro/mobmf"

    //private val URL = "http://10.10.10.102/ForUPP/ws/mobilemf.1cws?wsdl" //так вызывать неверно
    private val URL = "http://10.10.10.102/ForUPP/ws/mobilemf.1cws"

    fun GetVersion():String {
        val METHOD_NAME = "GetVersion"
        val SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME

        var strRes:String = ""

        val request = SoapObject(NAMESPACE, METHOD_NAME)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)

        envelope.bodyOut = request
        envelope.dotNet = true

        envelope.setOutputSoapObject(request)

        val httpTransport = HttpTransportSE(URL)

        try {
            httpTransport.call(SOAP_ACTION, envelope)
            strRes = envelope.response.toString()
        } catch (e: Exception) { // Many kinds of exceptions can be caught here
            Log.e("SOAP", e.toString())
        }

        Log.w("SOAP", strRes)

        return  strRes
    }

    fun GetUsers():String {
        val METHOD_NAME = "GetUsers"
        val SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME

        var usersObject:SoapObject? = null
        var strRes:String = ""

        val request = SoapObject(NAMESPACE, METHOD_NAME)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)

        envelope.bodyOut = request
        envelope.dotNet = true

        envelope.setOutputSoapObject(request)

        val httpTransport = HttpTransportSE(URL)

        try {
            httpTransport.call(SOAP_ACTION, envelope)
            strRes = envelope.response.toString()

            usersObject = envelope.response as SoapObject?
            Log.w("SOAP","Количество свойств - "+usersObject?.propertyCount.toString())

            var propertyUser = usersObject?.getProperty("user")
            Log.w("SOAP", "user="+propertyUser.toString())


            var objectUserName = propertyUser as SoapObject

            var propertyUserName = objectUserName.getProperty("username")


            Log.w("SOAP", "username="+propertyUserName.toString())

            var propertyRoles = objectUserName.getProperty("roles")
            Log.w("SOAP", "roles="+propertyRoles.toString())

            var objectRoles = propertyRoles as SoapObject

            var propertyRoleProductionTask = objectRoles.getProperty("RoleProductionTask")
            Log.w("SOAP", "propertyRoleProductionTask="+propertyRoleProductionTask.toString())

            var objectRoleProductionTask = propertyRoleProductionTask as SoapObject

            var rolesAddProductionTask = objectRoleProductionTask.getProperty("AddProductionTask")
            Log.w("SOAP", "AddProductionTask="+rolesAddProductionTask.toString())


        } catch (e: Exception) { // Many kinds of exceptions can be caught here
            Log.e("SOAP", e.toString())
        }

        Log.w("SOAP", strRes)

        return  strRes
    }

    fun GetUserName(UserCode:String):String {
        val METHOD_NAME = "GetUserName"
        val SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME

        var strRes:String = ""

        val request = SoapObject(NAMESPACE, METHOD_NAME)

        request.addProperty("code",UserCode)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)

        envelope.bodyOut = request
        envelope.dotNet = true

        envelope.setOutputSoapObject(request)

        val httpTransport = HttpTransportSE(URL)

        try {
            httpTransport.call(SOAP_ACTION, envelope)
            strRes = envelope.response.toString()
        } catch (e: Exception) { // Many kinds of exceptions can be caught here
            Log.e("SOAP", e.toString())
        }

        Log.w("SOAP", strRes)

        return  strRes
    }

}