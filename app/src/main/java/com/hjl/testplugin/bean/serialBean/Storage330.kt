package com.hjl.storage.bean.serialBean

/**
 * @Author      : xin
 * @Date        : on 2024-09-12 11:47.
 * @Description :上报BMS数据
 */
data class Storage330 (
    var msgType: Int = 330,
    var isData: Boolean = false,//是否有串口数据
    var bmsStatu: Int = 0, // BMS充放电状态
    var pwr: String = "", // 电池总功率
    var vol: String = "", // 电池总电压
    var cur: String = "", // 电池总电流
    var soc1: String = "", // 当前容量Soc
    var soh: String = "", // SOH
    var dispow: String = "", // 电池最大放电功率
    var chpow: String = "", // 电池最大充电功率
    var cellmaxV: String = "", // 电芯最大电压
    var cellminV: String = "", // 电芯最小电压
    var delldiffV: String = "", // 电芯最大压差
    var cellmaxT: String = "", // 电池最高温度
    var cellminT: String = "", // 电池最低温度
    var delldiffT: String = "", // 电池最大温差
    var runStatus: String = "", // 运行状态
    var hvpTemp1: String = "", // 高压箱温度
    var switchSta: String = "", // 并网状态
    var rackEnable1: String = "", // Rack1启用/停用状态
    var rackEnable2: String = "", // Rack2启用/停用状态
    var rackStatus1: String = "", // Rack1 在线状态
    var rackStatus2: String = "", // Rack2 在线状态
    var onlineNum: String = "", // 当前在网簇数
    var allNum: String = "", // 堆内总簇数
    var curDiff: String = "", // 簇间电流差
    var volDiff: String = "", // 簇间电压差

    // 告警信息
    var vollH: Int = 0,
    var vollL: Int = 0,
    var cellVH: Int = 0,
    var cellVL: Int = 0,
    var disCurH: Int = 0,
    var chgCurH: Int = 0,
    var disTempH: Int = 0,
    var disTempL: Int = 0,
    var chgTempH: Int = 0,
    var chgTempL: Int = 0,
    var insRL: Int = 0,
    var batPostTempH: Int = 0,
    var linkerTempH: Int = 0,
    var singleVH: Int = 0,
    var singleTempH: Int = 0,
    var socL: Int = 0,
    var rackFault: Int = 0, // Rack故障
    var pileAlarm: Int = 0, // 总控报警状态
    var pileComFail1: Int = 0, // 主控通信故障报警
    var pileComFail2: Int = 0 // 主控通信故障报警
)