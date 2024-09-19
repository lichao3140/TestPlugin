package com.hjl.storage.bean.serialBean

import com.google.gson.annotations.SerializedName

/**
 * @Author      : xin
 * @Date        : on 2024-09-12 17:08.
 * @Description :描述
 */
data class Storage320 (
    @SerializedName("ID") var id: Int = 0,
    var msgType: Int = 0,
    var systStu: String = "",
    var isData: Boolean = false,//是否有串口数据
    @SerializedName("Outvol") var outvol: String = "",
    @SerializedName("Outcur") var outcur: String = "",
    @SerializedName("P") var p: String = "",
    var temp: Int = 0,
    @SerializedName("Power_limit") var powerLimit: Int = 0,
    @SerializedName("ID_duplicates") var idDuplicates: Int = 0,
    @SerializedName("Uneven_current") var unevenCurrent: Int = 0,
    @SerializedName("In_Short") var inShort: Int = 0,
    @SerializedName("In_unblance") var inUnbalance: Int = 0,
    @SerializedName("In_Down") var inDown: Int = 0,
    @SerializedName("In_over") var inOver: Int = 0,
    @SerializedName("Pfc_off") var pfcOff: Int = 0,
    @SerializedName("DC_power_off") var dcPowerOff: Int = 0,
    @SerializedName("Module_Fault") var moduleFault: Int = 0,
    @SerializedName("Module_Protect") var moduleProtect: Int = 0,
    @SerializedName("Fun_Fault") var funFault: Int = 0,
    @SerializedName("Over_Temp") var overTemp: Int = 0,
    @SerializedName("OUT_Over") var outOver: Int = 0,
    @SerializedName("WALK_IN") var walkIn: Int = 0,
    @SerializedName("Module_Interrupt") var moduleInterrupt: Int = 0,
    @SerializedName("Out_short") var outShort: Int = 0,
    @SerializedName("Dormant") var dormant: Int = 0
)