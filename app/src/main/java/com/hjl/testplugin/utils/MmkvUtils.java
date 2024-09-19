package com.hjl.testplugin.utils;

import com.tencent.mmkv.MMKV;

/**
 * @author：Chao
 * @date：2023/12/17
 */
public class MmkvUtils {
    /**
     * 读取设备号
     * @return
     */
//    public static String getDeviceNumber() {
//        return MMKV.defaultMMKV().getString(SerialConst.MMKV_ANDROID_DEVICE_NUMBER, SIMUtil.getSerialNumber());
//    }

    /**
     * 保存设备号
     * @param deviceNumber
     * @return
     */
    public static boolean saveDeviceNumber(String deviceNumber) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_ANDROID_DEVICE_NUMBER, deviceNumber);
    }

    /**
     * 工作状态 0:关机  1：开机
     * @return
     */
    public static int getDevicePscWorkStatus() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_WORK_STATUS, 0);
    }

    /**
     * 工作状态 0:关机  1：开机
     * @param status
     * @return
     */
    public static boolean saveDevicePscWorkStatus(int status) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_WORK_STATUS, status);
    }

    /**
     * 堆地址
     * @return
     */
    public static int getDevicePscAddress() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_ADDRESS, 0);
    }

    /**
     * 堆地址
     * @param address
     * @return
     */
    public static boolean saveDevicePscAddress(int address) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_ADDRESS, address);
    }

    /**
     * 获取pcs充放功率
     * @return
     */
    public static int getDevicePscRunPower() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_POWER, 0);
    }

    /**
     * 保存pcs充放功率
     * @param runPower
     * @return
     */
    public static boolean saveDevicePscRunPower(int runPower) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_POWER, runPower);
    }

    /**
     * 获取手动下发停止SOC
     * @return
     */
    public static int getDevicePscRunStopSoc() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_STOP_SOC, 0);
    }

    /**
     * 保存手动下发停止SOC
     * @param runStopSoc
     * @return
     */
    public static boolean saveDevicePscRunStopSoc(int runStopSoc) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_STOP_SOC, runStopSoc);
    }

    /**
     * 获取手动下发DC最大充电功率
     * @return
     */
    public static int getDevicePscRunDcMaxPower() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_DC_MAX_POWER, 0);
    }

    /**
     * 保存手动下发DC最大充电功率
     * @param runDcMaxPower
     * @return
     */
    public static boolean saveDevicePscRunDcMaxPower(int runDcMaxPower) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_DC_MAX_POWER, runDcMaxPower);
    }

    /**
     * 获取手动下发门禁初始状态
     * @return
     */
    public static int getDeviceDoorInitStatus() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_DOOR_INIT_STATUS, 0);
    }

    /**
     * 保存手动下发门禁初始状态
     * @param doorInitStatus 0常闭 1常开
     * @return
     */
    public static boolean saveDeviceDoorInitStatus(int doorInitStatus) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_DOOR_INIT_STATUS, doorInitStatus);
    }

    /**
     * 获取手动下发空调类型  0同飞 1酷特安
     * @return
     */
    public static int getDeviceAcType() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_AC_TYPE, 0);
    }

    /**
     * 保存手动下发空调类型
     * @param AcType 0同飞 1酷特安
     * @return
     */
    public static boolean saveDeviceAcType(int AcType) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_AC_TYPE, AcType);
    }

    public static boolean getDevicePscRunPackAddr1() {
        return MMKV.defaultMMKV().getBoolean(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_1, false);
    }

    public static boolean saveDevicePscRunPackAddr1(boolean runPackAddr1) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_1, runPackAddr1);
    }

    public static boolean getDevicePscRunPackAddr2() {
        return MMKV.defaultMMKV().getBoolean(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_2, false);
    }

    public static boolean saveDevicePscRunPackAddr2(boolean runPackAddr2) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_2, runPackAddr2);
    }

    /**
     * 获取pcs运行模式 0:并网模式  1：离网模式
     * @return
     */
    public static int getDevicePscRunType() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_TYPE, 2);
    }

    /**
     * 保存pcs运行模式
     * @param runType
     * @return
     */
    public static boolean saveDevicePscRunType(int runType) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_TYPE, runType);
    }

    /**
     * 获取PCS控制方式 0:手动  1：时间段
     * @return
     */
    public static int getDevicePscRunControlType() {
        return MMKV.defaultMMKV().getInt(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_CONTROL_TYPE, 0);
    }

    /**
     * 保存PCS控制方式
     * @param runControlType
     * @return
     */
    public static boolean saveDevicePscRunControlType(int runControlType) {
        return MMKV.defaultMMKV().encode(SerialConst.MMKV_BDC_DEVICE_PSC_RUN_CONTROL_TYPE, runControlType);
    }
}
