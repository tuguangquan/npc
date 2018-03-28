package com.ctgu.npc.business.sys.mapper;

import com.ctgu.npc.business.sys.dto.OfficeInfo;
import com.ctgu.npc.business.sys.entity.Office;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface OfficeMapper {
    public List<OfficeInfo> getChildOfficeByTypeAndCode(@Param("type") String type,@Param("code") String code);
    public List<OfficeInfo> getOfficeByCode(@Param("code") String code);
}
