package com.ctgu.npc.business.wechat.service;

import com.ctgu.npc.business.wechat.entity.WeiXinChannel;
import com.ctgu.npc.business.wechat.mapper.WeChatMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2018/3/22.
 */
public class WeChatService {
    @Autowired
    private WeChatMapper weChatMapper;

    public int addWeChat(WeiXinChannel weiXinChannel){
        return weChatMapper.addWeChat(weiXinChannel);
    }

    public void updateWeChat(WeiXinChannel weiXinChannel){
        weChatMapper.updateWeChat(weiXinChannel);
    }
    public WeiXinChannel getWeiXinChannelByOpId(String opId){
        return weChatMapper.getWeiXinChannelByOpId(opId);
    }
    public WeiXinChannel getWeiXinChannelByUnionId(String unionId){
        return weChatMapper.getWeiXinChannelByUnionId(unionId);
    }
}
