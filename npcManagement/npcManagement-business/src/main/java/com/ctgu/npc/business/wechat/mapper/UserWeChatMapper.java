package com.ctgu.npc.business.wechat.mapper;

import com.ctgu.npc.business.wechat.entity.UserWeChat;
import com.ctgu.npc.fundamental.orm.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 54027 on 2018/3/24.
 */
public interface UserWeChatMapper extends AbstractMapper<UserWeChat>{

    public UserWeChat findByUnionId(@Param("unionId") String unionId);

}
