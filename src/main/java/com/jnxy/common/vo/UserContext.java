package com.jnxy.common.vo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jnxy.sys.entity.User;

public final class UserContext {
    private static final ThreadLocal<String> user = new ThreadLocal<String>();

    public static void add(String userName) {
        user.set(userName);
    }

    public static void remove() {
        user.remove();
    }

    public static String getCurrentUser() {
        return user.get();
    }

    public static User getCurrentUserObj() {
        User userobj = JSON.parseObject(user.get(), User.class);
        return userobj;
    }

    public static String getCurrentUserName() {
        JSONObject jsonObject = JSONObject.parseObject(user.get());
        JSONArray jsonArray = jsonObject.getJSONArray("username");
        return jsonArray.toJSONString();
    }
}
