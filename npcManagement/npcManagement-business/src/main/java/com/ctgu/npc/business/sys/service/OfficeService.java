package com.ctgu.npc.business.sys.service;

import com.ctgu.npc.business.sys.dto.OfficeInfo;
import com.ctgu.npc.business.sys.entity.Office;
import com.ctgu.npc.business.sys.mapper.OfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by user on 2018/3/28.
 */
public class OfficeService {

    @Autowired
    private OfficeMapper officeMapper;

    public List<OfficeInfo> getChildOfficeByTypeAndCode( String type, String code){
        return officeMapper.getChildOfficeByTypeAndCode(type,code);
    }
    public OfficeInfo getOfficeByCode(String code){
        List<OfficeInfo> officeList = officeMapper.getOfficeByCode(code);
        if (officeList.size()>0){
            return officeList.get(0);
        }else{
            return null;
        }
    }
}
