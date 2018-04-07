package com.ctgu.npc.business.wechat.mapper;

import com.ctgu.npc.business.wechat.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 54027 on 2018/4/6.
 */
public interface AccountMapper {

    public Account findByAppId(@Param("appId") String appId);

}
