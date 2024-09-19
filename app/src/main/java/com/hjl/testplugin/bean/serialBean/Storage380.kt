package com.hjl.storage.bean.serialBean

import com.google.gson.annotations.SerializedName

/**
 * @Author      : xin
 * @Date        : on 2024-09-12 14:33.
 * @Description :描述
 */
data class Storage380 (
    var msgType: Int = 380,
    var isData: Boolean = false,//是否有串口数据

    @SerializedName("com_status")
    var comStatus: Int = 0,

    var status: Int = 0,

    @SerializedName("In_humi")
    var inHumi: Int = 0,

    @SerializedName("In_temp")
    var inTemp: Float = 0f,

    @SerializedName("compressor")
    var comp: Int = 0,

    var heat: Int = 0,

    var ac: Int = 0,

    @SerializedName("heat_alarm")
    var heatAlarm: Int = 0,

    @SerializedName("high_humi")
    var highHumi: Int = 0,

    @SerializedName("cf_pro")
    var cfPro: Int = 0,

    @SerializedName("ets_fail")
    var etsFail: Int = 0,

    @SerializedName("cts_fail")
    var ctsFail: Int = 0,

    @SerializedName("its_fail")
    var itsFail: Int = 0,

    @SerializedName("hs_fail")
    var hsFail: Int = 0,

    @SerializedName("HP_alarm")
    var hpAlarm: Int = 0,

    @SerializedName("HP_lock")
    var hpLock: Int = 0,

    @SerializedName("ov_alarm")
    var ovAlarm: Int = 0,

    @SerializedName("uv_alarm")
    var uvAlarm: Int = 0
)