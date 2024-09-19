package com.hjl.storage.bean.serialBean

/**
 * 接口板版本信息
 * @author：Chao
 * @date：2024/1/15
 */
data class Storage310(
    val msgType: Int,  //属性类别
    val haldV: String, //嵌入式硬件版本
    val mcu: String,   //主控芯片类型
    val softV: String, //软件版本
    val txnNo: String  //序列号
)