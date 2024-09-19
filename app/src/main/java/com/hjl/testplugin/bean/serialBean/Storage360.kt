package com.hjl.storage.bean.serialBean

import com.google.gson.annotations.SerializedName

/**
 * @Author      : xin
 * @Date        : on 2024-09-12 14:30.
 * @Description :上报充电信息
 */
data class Storage360 (
    var msgType: Int = 0,
    var isData: Boolean = false,//是否有串口数据
    var chgtype: Int = 0,
    @SerializedName("DCvol") var dCvol: Float = 0.0f,
    @SerializedName("DCcur") var dCcur: Float = 0.0f,
    @SerializedName("ACvol") var aCvol: Float = 0.0f,
    @SerializedName("ACcur") var aCcur: Float = 0.0f,
    @SerializedName("ASKvol") var aSKvol: Float = 0.0f,
    @SerializedName("ASKcur") var aSKcur: Float = 0.0f,
    @SerializedName("SOC") var sOC: Int = 0,
    var gunstatu: Int = 0,
    @SerializedName("Guntemp1") var guntemp1: Int = 0,
    @SerializedName("Guntemp2") var guntemp2: Int = 0,
    @SerializedName("Guncc") var guncc: Int = 0,
    var cpvol: Int = 0,
    @SerializedName("Cpfre") var cpfre: Int = 0,
    @SerializedName("Cpduyt") var cpduyt: Int = 0,
    @SerializedName("Cabtemp1") var cabtemp1: Int = 0,
    @SerializedName("Cabtemp2") var cabtemp2: Int = 0,
    var chgtime: Int = 0,
    var chgpwr: Float = 0.0f,
    var chgkwh: Float = 0.0f,
    var stopreason: Int = 0
)