package com.jinke.housekeeper.db;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.jinke.housekeeper.utils.CommonlyUtils;

import org.litepal.crud.DataSupport;

import www.jinke.com.library.db.LoginInfo;
import www.jinke.com.library.db.SessionInfo;
import www.jinke.com.library.db.UserInfo;
import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-5-17.
 */

public class DBUserUtils {

    public String userId;

    public static void saveLoginInfo(Context mContext, LoginInfo info, EditText pwd, EditText userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassWord(pwd.getText().toString().trim());
        userInfo.setUserName(userName.getText().toString().trim());
        userInfo.setSex(info.getSex());
        userInfo.setSessionid(info.getSessionId());
        userInfo.setUserId(info.getUserId() + "");
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(String.valueOf(info.getUserId()));
        sessionInfo.setSessionId(info.getSessionId());
        sessionInfo.setUserName(userName.getText().toString().trim());//
        sessionInfo.setPassword(pwd.getText().toString().trim());//
        sessionInfo.setRoleType(info.getRoleType());
        userInfo.setRoleType(info.getRoleType());
        userInfo.setPower(info.getPower());
        userInfo.setType(info.getType());
        userInfo.setName(info.getName());

        if (info.getOrganization() != null && info.getOrganization().size() > 0) {
            userInfo.setOrganization(info.getOrganization().get(0).getOrgName());
            userInfo.setOrgCodel(info.getOrganization().get(0).getOrgCode());
            userInfo.setOrgName(info.getOrganization().get(0).getOrgName());
            sessionInfo.setOrgCodel(info.getOrganization().get(0).getOrgCode());
            sessionInfo.setOrgName(info.getOrganization().get(0).getOrgName());
        }
        if (info.getRoles() != null && info.getRoles().size() > 0) {
            userInfo.setRoles(info.getRoles().get(0).getRoleName());
        }
        userInfo.setLeftOrgId(info.getOrganization2().getOrgCode());
        userInfo.setLeftOrgName(info.getOrganization2().getOrgName());
        userInfo.setMobile(info.getMobile());
        userInfo.setFenCompany(info.getFenCompany());
        userInfo.setDeptName(info.getDeptName());
        userInfo.setPosition(info.getPosition());
        userInfo.setSex(info.getSex());
        userInfo.setCommpany(info.getCommpany());

        CommonlyUtils.saveSession(mContext, sessionInfo);
        CommonlyUtils.saveUserInfo(mContext, userInfo);
        SharedPreferencesUtils.init(mContext)
                .put("sessionId", userInfo.getSessionid() + "")
                .put("userId", userInfo.getUserId() + "")
                .put("userName", userInfo.getUserName())
                .put("pwd", userInfo.getPassWord());
        userInfo.save();//先保存后查询
        UserInfo info1 = DataSupport.find(UserInfo.class, 1);
        //判断数据库里面是否存在此条数据
        Log.i("data", "userInfo==" + DataSupport.findAll(UserInfo.class).size());
        if (info1 == null) {
            userInfo.save();
        } else {
            //更新本条数据
            info1.setSessionid(userInfo.getSessionid());
            info1.setCommpany(userInfo.getCommpany());
            info1.setDeptName(userInfo.getDeptName());
            info1.setFenCompany(userInfo.getFenCompany());
            info1.setLeftOrgId(userInfo.getLeftOrgId());
            info1.setMobile(userInfo.getMobile());
            info1.setName(userInfo.getName());
            info1.setOrganization(userInfo.getOrganization());
            info1.setOrgCodel(userInfo.getOrgCodel());
            info1.setPassWord(userInfo.getPassWord());
            info1.setOrgName(userInfo.getOrgName());
            info1.setPosition(userInfo.getPosition());
            info1.setPower(userInfo.getPower());
            info1.setRoles(userInfo.getRoles());
            info1.setRoleType(userInfo.getRoleType());
            info1.setSex(userInfo.getSex());
            info1.setUserName(userInfo.getUserName());
            info1.setUserId(userInfo.getUserId());
            info1.save();
        }

        SessionInfo sessionInfo1 = DataSupport.find(SessionInfo.class, 1);
        Log.i("data", "SessionInfo==" + DataSupport.findAll(SessionInfo.class).size());
        if (sessionInfo1 == null) {
            sessionInfo.save();
        } else {
            //更新本条数据
            sessionInfo1.setOrgCodel(sessionInfo.getOrgCodel());
            sessionInfo1.setOrgName(sessionInfo.getOrgName());
            sessionInfo1.setPassword(sessionInfo.getPassword());
            sessionInfo1.setRoleType(sessionInfo.getRoleType());
            sessionInfo1.setSessionId(sessionInfo.getSessionId());
            sessionInfo1.setUserName(sessionInfo.getUserName());
            sessionInfo1.save();
        }
    }

}
