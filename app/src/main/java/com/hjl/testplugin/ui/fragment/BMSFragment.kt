package com.hjl.testplugin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.hjl.storage.bean.serialBean.Storage320
import com.hjl.storage.bean.serialBean.Storage330
import com.hjl.testplugin.R
import com.hjl.testplugin.databinding.FragmentBmsBinding
import com.hjl.testplugin.ui.base.BaseLandFragment
import com.hjl.testplugin.utils.BlueUtils
import com.hjl.testplugin.utils.MmkvUtils
import com.lxj.xpopup.XPopup
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * BMS页面
 * @author：Chao
 * @date：2024/9/7
 */
class BMSFragment : BaseLandFragment<FragmentBmsBinding>() {

    //系统运行状态
    private var batRunStatusBuf: Array<String>? = arrayOf()

    //并网开关状态
    private var batGridStatusBuf: Array<String>? = arrayOf()
    private var rack1Switch = "0"
    private var rack2Switch = "0"

    private var rack1online = "0"
    private var rack2online = "0"

    private var arrayIcon100 = IntArray(16)
    private var arrayIcon101 = IntArray(16)
    private var arrayIcon102 = IntArray(16)
    private var arrayIcon103 = IntArray(16)

    private var array100 = Array(16) { "" }
    private var array101 = Array(16) { "" }
    private var array102 = Array(16) { "" }
    private var array103 = Array(16) { "" }

    companion object BMS {
        fun newInstance(): BMSFragment {
            return BMSFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onClick()
    }

    private fun init() {
        batRunStatusBuf = mActivity.resources.getStringArray(R.array.bms_rock_run_status_array)
        batGridStatusBuf = mActivity.resources.getStringArray(R.array.bms_Grid_status_array)
        // 获取簇的状态和在线状态的数组
        val baseSwitchArray = mActivity.resources.getStringArray(R.array.bms_Rack_Switch_array)
        val baseOnlineArray = mActivity.resources.getStringArray(R.array.bms_Rack_online_array)

        // 初始化 array100 和 array102
        for (i in 0 until 16) {
            array100[i] = String.format(baseSwitchArray[0], i + 1)
            array102[i] = String.format(baseOnlineArray[0], i + 1)
        }

        // 初始化 array101 和 array103
        for (i in 0 until 16) {
            array101[i] = String.format(baseSwitchArray[0], i + 17) // i + 1 + 16 等同于 i + 17
            array103[i] = String.format(baseOnlineArray[0], i + 17)
        }

        refreshUI()

    }

    private fun refreshUI() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            var cardTimes = 0L
            flow {
                while (true) {
                    emit(cardTimes)
                    cardTimes++
                    delay(2000L)
                }
            }.catch { e ->
                // 处理异常，例如记录日志或发送错误信息
                e.message?.let { Logger.e(it) }
            }.collect {
                withContext(Dispatchers.Main) {
                    if (isAdded && view != null) {
                        mainActivity?.let {
//                            onParseResp(it.storage330)
//                            onParseResp2(it.storage320)
                        }
                    }
                }
            }
        }
    }

    private fun onClick() {
        with(binding.includeBatterySingleInfo) {

            tvBatCluster1.setOnClickListener {
                if (MmkvUtils.getDevicePscRunType() == 0) { // 并网
                    showFaultListDialog(array100, tvBatCluster1, rack1Switch.toInt(), 100)
                }
            }

            tvBatCluster2.setOnClickListener {
                if (MmkvUtils.getDevicePscRunType() == 0) { // 并网
                    showFaultListDialog(array101, tvBatCluster2, rack2Switch.toInt(), 101)
                }
            }

            tvBatCluster1Online.setOnClickListener {
                if (MmkvUtils.getDevicePscRunType() == 0) { // 并网
                    showFaultListDialog(array102, tvBatCluster1Online, rack1online.toInt(), 102)
                }
            }

            tvBatCluster2Online.setOnClickListener {
                if (MmkvUtils.getDevicePscRunType() == 0) { // 并网
                    showFaultListDialog(array103, tvBatCluster2Online, rack2online.toInt(), 103)
                }
            }
        }
    }

    /**
     * 显示故障列表对话框
     */
    private fun showFaultListDialog(
        showArray: Array<String>,
        mView: View,
        faultCode: Int,
        type: Int
    ) {
        var hexFault = BlueUtils.toBinString(faultCode.toLong(), 2, false)
        hexFault = hexFault.reversed()

        XPopup.Builder(mActivity)
            .hasShadowBg(false)
            .hasStatusBar(false)
            .isViewMode(true)
            .atView(mView)
            .isClickThrough(true)
            .isDestroyOnDismiss(true)
            .popupWidth(300)
            .asAttachList(showArray, getShowIcons(hexFault, type)) { position, _ ->
                when (position) {
                    0 -> {
                        // Your code here for case 0
                    }
                    // Add other cases if needed
                }
            }
            .show()
    }

    private fun getShowIcons(hexFault: String, type: Int): IntArray {
        val resultIntArray: IntArray

        when (type) {
            100 -> {
                for (i in hexFault.indices) {
                    arrayIcon100[i] = if (hexFault[i] == '0') {
                        R.drawable.shape_bg_oval_red
                    } else {
                        R.drawable.shape_bg_oval_green
                    }
                }
                resultIntArray = arrayIcon100
            }

            101 -> {
                for (i in hexFault.indices) {
                    arrayIcon101[i] = if (hexFault[i] == '0') {
                        R.drawable.shape_bg_oval_red
                    } else {
                        R.drawable.shape_bg_oval_green
                    }
                }
                resultIntArray = arrayIcon101
            }

            102 -> {
                for (i in hexFault.indices) {
                    arrayIcon102[i] = if (hexFault[i] == '0') {
                        R.drawable.shape_bg_oval_red
                    } else {
                        R.drawable.shape_bg_oval_green
                    }
                }
                resultIntArray = arrayIcon102
            }

            103 -> {
                for (i in hexFault.indices) {
                    arrayIcon103[i] = if (hexFault[i] == '0') {
                        R.drawable.shape_bg_oval_red
                    } else {
                        R.drawable.shape_bg_oval_green
                    }
                }
                resultIntArray = arrayIcon103
            }

            else -> {
                resultIntArray = intArrayOf() // Handle unexpected types
            }
        }

        return resultIntArray
    }

    private fun onParseResp(storedBMSInfo: Storage330) {
        if (storedBMSInfo.isData) {
            // 运行状态
            val runStatusTemp = when {
                storedBMSInfo.runStatus.toInt() !in 0..4 -> {
                    Logger.e("电池运行状态未知: ${storedBMSInfo.runStatus}")
                    5 // 数组越界情况下，设置为5，表示未知情况
                }

                else -> storedBMSInfo.runStatus.toInt()
            }

            batRunStatusBuf?.let {
                binding.includeBatterySingleInfo.tvBatRunStatus.text =
                    String.format(
                        "%s\t%s",
                        mActivity.getString(R.string.text_bms_bat_run_status),
                        runStatusTemp
                    )
            }
            when (MmkvUtils.getDevicePscRunType()) {
                0 -> { // 并网
                    val gridStatusTemp = when {
                        storedBMSInfo.switchSta.toInt() !in 0..3 -> {
                            Logger.e("并网状态未知: ${storedBMSInfo.switchSta}")
                            4 // 数组越界情况下，设置为4，表示未知情况
                        }

                        else -> storedBMSInfo.switchSta.toInt()
                    }
                    batGridStatusBuf?.let {
                        binding.includeBatterySingleInfo.tvGridStatus.text =
                            String.format(
                                "%s\t%s",
                                mActivity.getString(R.string.text_bms_grid_connect_status),
                                it[gridStatusTemp]
                            )
                    }
                    rack1Switch = storedBMSInfo.rackEnable1

                    // 更新簇1和簇2信息
                    binding.includeBatterySingleInfo.tvBatCluster1.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster1_status),
                            storedBMSInfo.rackEnable1
                        )

                    rack2Switch = storedBMSInfo.rackEnable2

                    binding.includeBatterySingleInfo.tvBatCluster2.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster2_status),
                            storedBMSInfo.rackEnable2
                        )

                    rack1online = storedBMSInfo.rackStatus1
                    binding.includeBatterySingleInfo.tvBatCluster1Online.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster1_online),
                            storedBMSInfo.rackStatus1
                        )

                    rack2online = storedBMSInfo.rackStatus2

                    binding.includeBatterySingleInfo.tvBatCluster2Online.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster2_online),
                            storedBMSInfo.rackStatus2
                        )

                    // 更新簇数和温度、电流差等
                    binding.includeBatterySingleInfo.tvClustersNumOnline.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_clusterNum_online),
                            storedBMSInfo.onlineNum
                        )
                    binding.includeBatterySingleInfo.tvClustersNum.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_clusterNum),
                            storedBMSInfo.allNum
                        )
                    binding.includeBatteryTempInfo.tvBatHvpTemp.text =
                        String.format(
                            "%s\t--℃",
                            mActivity.getString(R.string.text_bms_bat_hvp_Temp)
                        )
                    binding.includeBatteryTempInfo.tvCurDiff.text =
                        String.format(
                            "%s\t%sA",
                            mActivity.getString(R.string.move_text_rack_cur_diff),
                            storedBMSInfo.curDiff
                        )
                    binding.includeBatteryTempInfo.tvRackVolDiff.text =
                        String.format(
                            "%s\t%sA",
                            mActivity.getString(R.string.move_text_rack_vol_diff),
                            storedBMSInfo.volDiff
                        )

                }

                1 -> { // 离网
                    binding.includeBatterySingleInfo.tvGridStatus.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_grid_connect_status),
                            mActivity.getString(R.string.text_rackdis_status_show)
                        )

                    binding.includeBatterySingleInfo.tvBatCluster1.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster1_status),
                            mActivity.getString(R.string.text_rack_status_show)
                        )

                    binding.includeBatterySingleInfo.tvBatCluster2.text =
                        String.format(
                            "%s\t%s",
                            mActivity.getString(R.string.text_bms_cluster2_status),
                            mActivity.getString(R.string.text_rackdis_status_show)
                        )

                    binding.includeBatterySingleInfo.tvBatCluster1Online.text =
                        String.format(
                            "%s\t--",
                            mActivity.getString(R.string.text_bms_cluster1_online)
                        )
                    binding.includeBatterySingleInfo.tvBatCluster2Online.text =
                        String.format(
                            "%s\t--",
                            mActivity.getString(R.string.text_bms_cluster2_online)
                        )
                    binding.includeBatterySingleInfo.tvClustersNumOnline.text =
                        String.format(
                            "%s\t1",
                            mActivity.getString(R.string.text_bms_clusterNum_online)
                        )
                    binding.includeBatterySingleInfo.tvClustersNum.text =
                        String.format("%s\t1", mActivity.getString(R.string.text_bms_clusterNum))
                    binding.includeBatteryTempInfo.tvBatHvpTemp.text =
                        String.format(
                            "%s\t%s℃",
                            mActivity.getString(R.string.text_bms_bat_hvp_Temp),
                            storedBMSInfo.hvpTemp1
                        )
                    binding.includeBatteryTempInfo.tvCurDiff.text =
                        String.format(
                            "%s\t--%s",
                            mActivity.getString(R.string.move_text_rack_cur_diff),
                            mActivity.getString(R.string.text_bms_unit_a)
                        )

                    binding.includeBatteryTempInfo.tvRackVolDiff.text =
                        String.format(
                            "%s\t--%s",
                            mActivity.getString(R.string.move_text_rack_vol_diff),
                            mActivity.getString(R.string.text_bms_unit_v)
                        )

                }
            }

            // 更新电压和温度信息
            binding.includeBatteryTempInfo.tvMaxVol.text =
                String.format(
                    "%s\t%sV",
                    mActivity.getString(R.string.move_text_max_cell_vol),
                    storedBMSInfo.cellmaxV
                )

            binding.includeBatteryTempInfo.tvMinVol.text =
                String.format(
                    "%s\t%sV",
                    mActivity.getString(R.string.move_text_min_cell_vol),
                    storedBMSInfo.cellminV
                )

            binding.includeBatteryTempInfo.tvMaxVolDiff.text =
                String.format(
                    "%s\t%sV",
                    mActivity.getString(R.string.move_text_max_cell_vol_diff),
                    storedBMSInfo.delldiffV
                )


            binding.includeBatteryTempInfo.tvMaxTemp.text =
                String.format(
                    "%s\t%s℃",
                    mActivity.getString(R.string.move_text_max_temp),
                    storedBMSInfo.cellmaxT
                )

            binding.includeBatteryTempInfo.tvMinTemp.text =
                String.format(
                    "%s\t%s℃",
                    mActivity.getString(R.string.move_text_min_temp),
                    storedBMSInfo.cellminT
                )

            binding.includeBatteryTempInfo.tvMaxTempDiff.text =
                String.format(
                    "%s\t%s℃",
                    mActivity.getString(R.string.move_text_max_temp_diff),
                    storedBMSInfo.delldiffT
                )


            // BMS 状态和功率信息
            binding.includePackInfo.tvBmsStatus.text = when (storedBMSInfo.bmsStatu) {
                0 -> "${mActivity.getString(R.string.text_bms_chang_status)}\t${
                    mActivity.getString(
                        R.string.text_bms_chang_status_k
                    )
                }"

                1 -> "${mActivity.getString(R.string.text_bms_chang_status)}\t${
                    mActivity.getString(
                        R.string.text_bms_chang_status_c
                    )
                }"

                2 -> "${mActivity.getString(R.string.text_bms_chang_status)}\t${
                    mActivity.getString(
                        R.string.text_bms_chang_status_f
                    )
                }"

                else -> ""
            }

            binding.includePackInfo.tvCuVoltage.text =
                String.format(
                    "%s\t%sV",
                    mActivity.getString(R.string.move_text_total_vol),
                    storedBMSInfo.vol
                )

            binding.includePackInfo.tvCuPower.text =
                String.format(
                    "%s\t%sA",
                    mActivity.getString(R.string.move_text_total_cur),
                    storedBMSInfo.cur
                )


            binding.includePackInfo.tvTotalPower.text =
                String.format(
                    "%s\t%sKW",
                    mActivity.getString(R.string.move_text_total_power),
                    storedBMSInfo.pwr
                )

            binding.includePackInfo.tvCurSoc1.text =
                String.format(
                    "%s\t%s",
                    mActivity.getString(R.string.move_text_soc),
                    storedBMSInfo.soc1
                )

            binding.includePackInfo.tvCurSoc2.text =
                String.format(
                    "%s\t%s",
                    mActivity.getString(R.string.move_text_soh),
                    storedBMSInfo.soh
                )


//        binding.includePackInfo.waterWaveView.setProgress(storedBMSInfo.soc1.split(".")[0].toInt())

            // soc <= 3% 时限制功率为0，否则正常显示
            binding.includePackInfo.tvDispow.text =
                String.format(
                    "%s\t%sKW",
                    mActivity.getString(R.string.move_text_min_dis_pow),
                    storedBMSInfo.dispow
                )

            binding.includePackInfo.tvBatChargePow.text =
                String.format(
                    "%s\t%sKW",
                    mActivity.getString(R.string.move_bat_max_chg_power),
                    storedBMSInfo.chpow
                )
        }
    }

    private fun onParseResp2(storedHomeInfo: Storage320) {
        if (!storedHomeInfo.isData){
            return
        }
        // 0：空闲 1：充电 2：放电 3：故障维护
        val systStatusText = when (storedHomeInfo.systStu) {
            "1" -> "${mActivity.getString(R.string.move_text_battery_nominal_capacity)}\t${
                mActivity.getString(
                    R.string.text_bms_chang_status_c
                )
            }"

            "2" -> "${mActivity.getString(R.string.move_text_battery_nominal_capacity)}\t${
                mActivity.getString(
                    R.string.text_bms_chang_status_f
                )
            }"

            "3" -> "${mActivity.getString(R.string.move_text_battery_nominal_capacity)}\t${
                mActivity.getString(
                    R.string.text_bms_chang_status_fault
                )
            }"

            else -> "${mActivity.getString(R.string.move_text_battery_nominal_capacity)}\t${
                mActivity.getString(
                    R.string.text_bms_chang_status_k
                )
            }"
        }
        binding.includePackInfo.tvSystStatus.text = systStatusText

        // ID 设置
//        binding.includePackInfo.tvChargeModeId.text = "${mActivity.getString(R.string.move_text_chg_power)}\t${storedHomeInfo.id}"

        // 电压、电流、功率等信息设置
        binding.includePackInfo.tvChargeOutV.text =
            String.format(
                "%s\t%sV",
                mActivity.getString(R.string.text_bms_charging_mode_out_v),
                storedHomeInfo.outvol
            )

        binding.includePackInfo.tvChargeOutC.text =
            String.format(
                "%s\t%sA",
                mActivity.getString(R.string.text_bms_charging_mode_out_c),
                storedHomeInfo.outcur
            )

        binding.includePackInfo.tvChargeOutP.text =
            String.format(
                "%s\t%sKW",
                mActivity.getString(R.string.text_bms_charging_mode_out_p),
                storedHomeInfo.p
            )

    }


    //与充电桩进行通信
//    fun onParseResp4(connect: Boolean) {
//        val drawableRed =
//            ContextCompat.getDrawable(mActivity, R.drawable.shape_bg_oval_red)?.apply {
//                setBounds(0, 0, minimumWidth, minimumHeight)
//            }
//
//        val drawableGreen =
//            ContextCompat.getDrawable(mActivity, R.drawable.shape_bg_oval_green)?.apply {
//                setBounds(0, 0, minimumWidth, minimumHeight)
//            }
//
//        if (connect) {
//            binding.includePackInfo.tvChargeStatus.setCompoundDrawables(
//                null,
//                null,
//                drawableGreen,
//                null
//            )
//            binding.includePackInfo.tvChargePow.text = String.format(
//                "%s\t%.1fkw",
//                mActivity.getString(R.string.text_bms_charge_Pow),
//                chargingCurrentPower / 10f
//            )
//        } else {
//            binding.includePackInfo.tvChargeStatus.setCompoundDrawables(
//                null,
//                null,
//                drawableRed,
//                null
//            )
//            binding.includePackInfo.tvChargePow.text = String.format(
//                "%s\t%dkw",
//                mActivity.getString(R.string.text_bms_charge_Pow),
//                0
//            )
//        }
//    }

}