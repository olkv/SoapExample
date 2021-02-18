package com.example.soapexample

class CRoleProductionTask(val pAdd:Boolean,val pEdit:Boolean,val pDel:Boolean, val pView:Boolean) {
    var add:Boolean = false

    init {
        
    }

}

data class CRoleQualityControl(var pAdd:Boolean,var pEdit:Boolean,var pDel:Boolean, var pView: Boolean)

data class CRolePerformanceMonitor(var pView: Boolean)

data class CRoles(var pProductionTask: CRoleProductionTask, var pQualityControl: CRoleQualityControl, var pPerformanceMonitor: CRolePerformanceMonitor)

data class CUser(var pUserName:String, var pRoles: CRoles)

