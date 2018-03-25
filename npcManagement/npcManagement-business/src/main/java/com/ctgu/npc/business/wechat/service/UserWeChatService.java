package com.ctgu.npc.business.wechat.service;

import com.ctgu.npc.business.wechat.entity.UserWeChat;
import com.ctgu.npc.business.wechat.mapper.UserWeChatMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 54027 on 2018/3/24.
 */
public class UserWeChatService {

    @Autowired
    private UserWeChatMapper userWeChatMapper;

    public void add(UserWeChat userWeChat){
         userWeChatMapper.add(userWeChat);
    }

    public UserWeChat findByUnionId(String unionId){
        return userWeChatMapper.findByUnionId(unionId);
    }
}
