package com.yuanye.njdt.constants;

import android.content.Context;
import android.text.TextUtils;

import com.millet.androidlib.Utils.LogUtils;
import com.millet.androidlib.Utils.SDCardUtils;
import com.millet.androidlib.Utils.SharedPreferencesHelper;
import com.yuanye.njdt.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class AppUtils {

    /**
     * 保存文件的路径地址
     *
     * @return
     */
    public static String getFilePath() {
        try {
            String fileName = "down/";
            String filePath = SDCardUtils.createSubDir(fileName).getAbsolutePath();
            return filePath;
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
            return null;
        }
    }

    /**
     * 判断下载的文件是否存在
     *
     * @param _fileName
     * @return
     */
    public static boolean existFile(String _fileName) {
        try {
            String fileName = "down/";
            File _file = SDCardUtils.createSubDir(fileName).getAbsoluteFile();
            File _file1 = new File(_file, _fileName);
            if (_file1.exists())
                return true;
            else
                return false;
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
            return false;
        }
    }

    /***
     * 获取文件的路径
     *
     * @param _fileName
     * @return
     */
    public static String getFilePath(String _fileName) {
        try {
            if (TextUtils.isEmpty(_fileName)) return null;
            String fileName = "down/";
            File _file = SDCardUtils.createSubDir(fileName).getAbsoluteFile();
            File _file1 = new File(_file, _fileName);
            if (_file1.exists()) {
                return _file1.getPath();
            } else {
                return null;
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
            return null;
        }
    }

    public static void deleteFile(String _fileName) {
        try {
            String fileName = "down/";
            File _file = SDCardUtils.createSubDir(fileName).getAbsoluteFile();
            File _file1 = new File(_file, _fileName);
            if (_file1.exists())
                _file1.delete();
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    /**
     * 截取最后一个字符串
     *
     * @param _str
     * @return
     */
    public static String getLastString(String _str) {
        if (TextUtils.isEmpty(_str))
            return null;
        String[] _split = _str.split("/");
        if (1 == _split.length)
            return _str;
        return _split[_split.length - 1];
    }

    /**
     * 获取联系人的手机号数组
     *
     * @param _str
     * @return
     */
    public static String[] getPhoneStringArray(String _str) {
        if (TextUtils.isEmpty(_str))
            return null;
        String[] _stringPhone = _str.split(",");
        if (1 == _stringPhone.length)
            return new String[]{_str};
        return _stringPhone;
    }

    /**
     * 获取联系人名字和地址
     *
     * @param _str
     * @return
     */
    public static String[] getNameAndPlace(String _str) {
        if (TextUtils.isEmpty(_str))
            return null;
        String[] _strings = _str.split("-");
        return _strings;
    }

    /**
     * 获取线路
     *
     * @param _str
     */
    public static void getJsonStation(String _str) {
        if (TextUtils.isEmpty(_str))
            return;
        try {
            JSONObject _jsonObject = new JSONObject(_str);
            Context _context = MyApplication.getInstance();
            if (_jsonObject.has(KeyConfig.one_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.one_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_ONE_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.two_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.two_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_TWO_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.three_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.three_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_THREE_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.four_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.four_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_FOUR_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.ten_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.ten_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_TEN_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.s_one_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.s_one_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_S_ONE_LINE, _j);
            }
            if (_jsonObject.has(KeyConfig.s_eight_line)) {
                JSONArray _jsonArray = _jsonObject.getJSONArray(KeyConfig.s_eight_line);
                String _j = String.valueOf(_jsonArray);
                SharedPreferencesHelper.getInstance(_context).put(KeyConfig.SHARE_KEY_S_EIGHT_LINE, _j);
            }
            SharedPreferencesHelper.getInstance(_context).put(KeyConfig.EXIST_SHARE_KEY_STATION_INFO, true);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    public static String[] getStringArray(String _str) {
        if (TextUtils.isEmpty(_str))
            return null;
        try {
            JSONArray _jsonArray = new JSONArray(_str);
            int _length = _jsonArray.length();
            String[] _strings = new String[_length];
            for (int i = 0; i < _length; i++) {
                _strings[i] = (String) _jsonArray.get(i);
            }
            return _strings;
        } catch (JSONException _e) {
            _e.printStackTrace();
            return null;
        }
    }

}
