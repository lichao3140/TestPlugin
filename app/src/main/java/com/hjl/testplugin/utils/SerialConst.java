package com.hjl.testplugin.utils;

/**
 * 串口常量
 * @author：Chao
 * @date：2023/10/20
 */
public class SerialConst {

    /**
     * MMKV设备号
     */
    public static final String MMKV_ANDROID_DEVICE_NUMBER = "ANDROID_DEVICE_NUMBER";

    /**
     * MMKV工作状态 0:关机  1：开机
     */
    public static final String MMKV_BDC_DEVICE_PSC_WORK_STATUS = "BDC_DEVICE_PSC_WORK_STATUS";

    /**
     * PCS工作模式 1-离网 2-并网
     */
    public static final String MMKV_BDC_DEVICE_PSC_WORK_MODE = "BDC_DEVICE_PSC_WORK_MODE";

    /**
     * 堆地址
     */
    public static final String MMKV_BDC_DEVICE_PSC_ADDRESS = "BDC_DEVICE_PSC_ADDRESS";

    /**
     * 手动下发充放功率
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_POWER = "BDC_DEVICE_PSC_RUN_POWER";

    /**
     * 手动下发停止SOC
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_STOP_SOC = "BDC_DEVICE_PSC_RUN_STOP_SOC";

    /**
     * 手动下发DC最大充电功率
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_DC_MAX_POWER = "BDC_DEVICE_PSC_RUN_DC_MAX_POWER";

    /**
     * 手动下发门禁初始状态
     */
    public static final String MMKV_BDC_DEVICE_DOOR_INIT_STATUS = "BDC_DEVICE_DOOR_INIT_STATUS";

    /**
     * 手动下发空调类型
     */
    public static final String MMKV_BDC_DEVICE_AC_TYPE = "BDC_DEVICE_AC_TYPE";

    /**
     * 时段控制堆1是否启用
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_1 = "BDC_DEVICE_PSC_RUN_PACK_ADDR_1";

    /**
     * 时段控制堆2是否启用
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_PACK_ADDR_2 = "BDC_DEVICE_PSC_RUN_PACK_ADDR_2";

    /**
     * 获取pcs运行模式 0:并网模式  1：离网模式
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_TYPE = "BDC_DEVICE_PSC_RUN_TYPE";

    /**
     * PCS控制方式 0:手动  1：时间段
     */
    public static final String MMKV_BDC_DEVICE_PSC_RUN_CONTROL_TYPE = "BDC_DEVICE_PSC_RUN_CONTROL_TYPE";

    /**
     * 鸿嘉利管理平台服务器地址
     */
    public static final String HJL_GL_SERVER_ADDRESS = "8.129.49.66";

    /**
     * 鸿嘉利管理平台端口
     */
    public static final int HJL_GL_TCP_SERVER_PORT = 59011;
    public static final String HJL_GL_TCP_SERVICE_LOGIN = "login";
    public static final String HJL_GL_TCP_SERVICE_UPDATE = "update";

    /**
     * 数据开头
     */
    public static final String FRAME_HEADER = "55";

    /**
     * 数据最小长度
     */
    public static final int MIN_DATA_LENGTH = 8;

    /**
     * 单个包最长
     */
    public static final int MAX_SINGLE_PACKAGE_LENGTH = 32;

    /**********************接口板上行数据*****************************/
    /**
     * 解析串口数据成功
     */
    public static final int PARSE_MSG_SUCCESS = 1000;
    /**
     * 解析串口数据失败
     */
    public static final int PARSE_MSG_FAIL = 9999;

    /**
     * 接口板版本信息
     */
    public static final int UPLOAD_REQUEST_310 = 310;

    /**
     * 回复310
     */
    public static final int UPLOAD_RESPONSE_311 = 311;

    /**
     * 充电模块信息
     */
    public static final int UPLOAD_REQUEST_320 = 320;

    /**
     * 回复320
     */
    public static final int UPLOAD_RESPONSE_321 = 321;

    /**
     * 上报BMS数据
     */
    public static final int UPLOAD_REQUEST_330 = 330;

    /**
     * 回复330
     */
    public static final int UPLOAD_RESPONSE_331 = 331;

    /**
     * 上报火灾告警信息
     */
    public static final int UPLOAD_REQUEST_340 = 340;

    /**
     * 回复340
     */
    public static final int UPLOAD_RESPONSE_341 = 341;

    /**
     * 上报开关量信息
     */
    public static final int UPLOAD_REQUEST_350 = 350;

    /**
     * 回复350
     */
    public static final int UPLOAD_RESPONSE_351 = 351;

    /**
     * 上报充电信息
     */
    public static final int UPLOAD_REQUEST_360 = 360;

    /**
     * 回复360
     */
    public static final int UPLOAD_RESPONSE_361 = 361;

    /**
     * 上报EVCC信息
     */
    public static final int UPLOAD_REQUEST_370 = 370;

    /**
     * 回复370
     */
    public static final int UPLOAD_RESPONSE_371 = 371;

    /**
     * 上报空调信息
     */
    public static final int UPLOAD_REQUEST_380 = 380;

    /**
     * 回复380
     */
    public static final int UPLOAD_RESPONSE_381 = 381;

    /**
     * 告警上报
     */
    public static final int UPLOAD_REQUEST_410 = 410;

    /**
     * 回复410
     */
    public static final int UPLOAD_RESPONSE_411 = 411;


    /**********************安卓下行数据*****************************/

    /**
     * 空调设置信息
     */
    public static final int SETTING_REQUEST_510 = 510;

    /**
     * 回复510
     */
    public static final int SETTING_RESPONSE_511 = 511;

    /**
     * PCS设置信息
     */
    public static final int SETTING_REQUEST_520 = 520;

    /**
     * 回复520
     */
    public static final int SETTING_RESPONSE_521 = 521;

    /**
     *
     */
    public static final int SETTING_REQUEST_530 = 530;

    /**
     * 回复530
     */
    public static final int SETTING_RESPONSE_531 = 531;

    /**
     *
     */
    public static final int SETTING_REQUEST_540 = 540;

    /**
     * 回复540
     */
    public static final int SETTING_RESPONSE_541 = 541;

    /**
     *
     */
    public static final int SETTING_REQUEST_550 = 550;

    /**
     * 回复550
     */
    public static final int SETTING_RESPONSE_551 = 551;

    /**
     *
     */
    public static final int SETTING_REQUEST_610 = 610;

    /**
     * 回复610
     */
    public static final int UPLOAD_RESPONSE_611 = 611;

    /**
     * 固件升级
     */
    public static final int UPLOAD_BIN_UPDATE_INFO_REQUEST = 1300;

    /**
     * 固件升级回复
     */
    public static final int UPLOAD_BIN_UPDATE_INFO_RESPONSE = 1301;

    /**
     * 固件升级完成回复
     */
    public static final int UPLOAD_BIN_UPDATE_FINISH_RESPONSE = 1302;

    public static final String BIN_START_FILE = "start_file";
    public static final String BIN_UPDATE = "update";
    public static final String BIN_STOP_UPDATE = "stop_update";

}
