package com.ctgu.npc.business.wechat.service;

import com.ctgu.npc.business.wechat.entity.Account;
import com.ctgu.npc.business.wechat.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 54027 on 2018/3/24.
 */
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account findByAppId(String appId){
        return accountMapper.findByAppId(appId);
    }
}
