package com.yuanye.njdt.constants;

import com.yuanye.njdt.data.entity.UserInfoEntity;

/***
 * 全局变量：用于保存用户当前信息
 * @author qjm
 *
 * @date 2015年9月18日
 */
public class GlobalVariables {

    public static UserInfoEntity userInfo = new UserInfoEntity();
    // 账户是否存在
    public static Boolean accountExist = false;

}
