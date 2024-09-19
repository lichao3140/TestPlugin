package com.hjl.storage.bean.serialBean

/**
 * @Author      : xin
 * @Date        : on 2024-09-14 09:21.
 * @Description :描述
 */
data class StoredAcParamSet (
    val msgType: Int,
    val openStatus: Int,
    val acColdTemp: Int,
    val acColdDiff: Int,
    val acHeatTemp: Int,
    val acHeatDiff: Int
)