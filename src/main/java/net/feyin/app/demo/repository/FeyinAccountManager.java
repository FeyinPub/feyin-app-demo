package net.feyin.app.demo.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/***
 *  注意：改类仅为飞印应用展示用，不建议模仿。
 *  用户数据请使用持久化工具进行存储
 */
public class FeyinAccountManager {

    private static final Map<String, List<String>> FEYIN_ACCOUNTS = Maps.newHashMap();

    //检查用户账号是否存在
    public static boolean checkAccount(String uid) {
        return FEYIN_ACCOUNTS.containsKey(uid);
    }

    //查询用户名下的设备
    public static List<String> getAccountDevices(String uid) {
        return FEYIN_ACCOUNTS.get(uid);
    }

    //用户授权后添加账号
    public static void addAccount(String uid) {
        FEYIN_ACCOUNTS.put(uid, Lists.newArrayList());
    }

    //添加用户的设备
    public static void addDevice(String udi, List<String> devices) {
        FEYIN_ACCOUNTS.put(udi, devices);
    }

    //用户解除授权
    public static void removeAccount(String uid) {
        FEYIN_ACCOUNTS.remove(uid);
    }
}
