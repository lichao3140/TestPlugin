package com.hjl.storage.bean.serialBean

import java.io.Serializable

/**
 * @Author      : xin
 * @Date        : on 2024-09-14 09:24.
 * @Description :描述
 */
data class PresenterBean(
    var type: Int,
    var jsonMsg: String
): Serializable