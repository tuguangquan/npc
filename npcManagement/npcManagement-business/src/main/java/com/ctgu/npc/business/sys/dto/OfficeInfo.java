package com.ctgu.npc.business.sys.dto;

/**
 * Created by user on 2018/3/28.
 */
public class OfficeInfo {
    private String id;
    private String name; //名称
    private String code; 	// 机构编码
    private String type; 	// 机构类型（1：公司；2：部门；3：小组）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
