package com.hjl.storage.bean.serialBean

import com.google.gson.annotations.SerializedName

/**
 * @Author      : xin
 * @Date        : on 2024-09-12 14:26.
 * @Description :上报开关量信息
 */
data class Storage350 (
    var msgType: Int = 350,
    var isData: Boolean = false,//是否有串口数据
    var smokstatus: String = "",
    var waterSoak: String = "",
    var firestatus: String = "",
    var doorstatus: String = "",
    @SerializedName("ACconnect")
    var aCconnect: String = "",
    @SerializedName("DCconnect")
    var dCconnect: String = ""
)