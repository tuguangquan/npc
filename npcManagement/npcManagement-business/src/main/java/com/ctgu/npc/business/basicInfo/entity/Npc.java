package com.ctgu.npc.business.basicInfo.entity;


import com.ctgu.npc.business.common.persistence.BaseEntity;
import com.ctgu.npc.business.common.utils.annotation.ExcelField;
import com.ctgu.npc.business.sys.entity.Users;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Npc extends BaseEntity<Npc> {
    
    private static final long serialVersionUID = 1L;

    private String id;//db_npc_member表id
    
    @NotNull(message="代表证号不能为空")
    @Length(max=3,min=3,message="请输入三位的代表证号，例如：001")
    @ExcelField(title="代表证号",type=2,sort=1)
    private String cardId; //代表证号
    
    @NotNull
    @ExcelField(title="姓名",type=2,sort=2)
    private String realName;//代表姓名
    
    @ExcelField(title="工作单位及职务",type=2,sort=3)
    private String job;
    
    @ExcelField(title="性别",type=2,sort=4)
    private String sex;
    
    @ExcelField(title="民族",type=2,sort=5)
    private String nation;
    
    @ExcelField(title="出生年月",type=2,sort=6)
    private String birth;
    
    @ExcelField(title="籍贯",type=2,sort=7)
    private String nativePlace;
    
    @ExcelField(title="政治面貌",type=2,sort=8)
    private String party;
    
    @ExcelField(title="文化程度",type=2,sort=9)
    private String education;//学历
    
    @ExcelField(title="邮箱",type=2,sort=10)
    private String email;
    
    @ExcelField(title="座机号",type=2,sort=11)
    private String phone;//座机号
    
    @ExcelField(title="手机号",type=2,sort=12)
    private String mobile;
    
    @ExcelField(title="QQ",type=2,sort=13)
    private String qq;
    
    @ExcelField(title="微信",type=2,sort=14)
    private String WeChat;
    
    @ExcelField(title="邮政编码",type=2,sort=15)
    private String postalCode;
    
    @ExcelField(title="结构类型",type=2,sort=16)
    private String structureType;
    
    @ExcelField(title="是否连任",type=2,sort=17)
    private String reappointment;
    
    @NotNull
    @ExcelField(title="原选举单位或选区",type=2,sort=18)
    private String companyOrDistrict;
    
    @NotNull
   // @Length(min=18,max=18,message="请填写18位的身份证号")
    @ExcelField(title="身份证号",type=2,sort=19)
    private String idNumber;
    
    @NotNull
    @ExcelField(title="用户名",type=2,sort=20)
    private String loginName;
    
    @ExcelField(title="毕业院校",type=2,sort=21)
    private String graduation;
    
    @ExcelField(title="专业",type=2,sort=22)
    private String major;
    
    @ExcelField(title="学位",type=2,sort=23)
    private String degree;
    
    @ExcelField(title="通讯地址",type=2,sort=24)
    private String address;
    
    @ExcelField(title="履职承诺",type=2,sort=25)
    private String commitment;
    
    @NotNull
    @ExcelField(title="状态",type=2,sort=26)
    private String status;
    
    @ExcelField(title="人大职务",type=2,sort=27)
    private String npcDuty;
    
    @ExcelField(title="代表级别",type=2,sort=28)
    private String representLevel;
    
    @NotNull(message="所属行政区不能为空")
    @ExcelField(title="所属行政区",type=2,sort=29)
    private String region;

    
    private String technicalTitle;

    private String photo;//代表电子照片

    private String isChecked;

    private Integer userId;

    private String officeId;

    private String password;

    private String userType;

    private String loginIp;

    private Date loginDate;

    private String loginFlag;

    private String delFlag;

    private String userLevel;
    
    private String specialLevel;
    
    private String mission; //代表团名称
    
    private String missionId;//代表团id
    
    private Integer npcId;
    
    private String oldLoginIp;
    
    private Date oldLoginDate;
    
    private String period;//代表届次
    
    private Date createDate;

    private Date updateDate;

    private String remarks;
    
    private String createBy;
    
    private String updateBy;
    
    private String changeTime;
    
    private Integer operatorId;
    
    private Integer specialId;
    
    private String reason;
    
    private String changeFlag;//换届标志位
    
    private String proviceLevel;// 省代表级别
    private String provicePeriod; // 省代表届次
    private String nationLevel;// 全国代表级别
    private String nationPeriod;// 全国代表届次
    private List<Integer> specialIdList;
    private Integer studioId;
    private String siteName;
    
    private String beginAge;
    private String endAge;
    
    private String sort;//排序号
    private String jc;
    
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Integer getNpcId() {
        return npcId;
    }

    public void setNpcId(Integer npcId) {
        this.npcId = npcId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }

   
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }


    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getSpecialLevel() {
        return specialLevel;
    }

    public void setSpecialLevel(String specialLevel) {
        this.specialLevel = specialLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party == null ? null : party.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation == null ? null : graduation.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    public String getReappointment() {
        return reappointment;
    }

    public void setReappointment(String reappointment) {
        this.reappointment = reappointment == null ? null : reappointment.trim();
    }

    
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCommitment() {
        return commitment;
    }

    public void setCommitment(String commitment) {
        this.commitment = commitment == null ? null : commitment.trim();
    }

    
    public String getNpcDuty() {
        return npcDuty;
    }

    public void setNpcDuty(String npcDuty) {
        this.npcDuty = npcDuty;
    }

    public String getTechnicalTitle() {
        return technicalTitle;
    }

    public void setTechnicalTitle(String technicalTitle) {
        this.technicalTitle = technicalTitle;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String weChat) {
        WeChat = weChat;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

   
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    
    public String getCompanyOrDistrict() {
        return companyOrDistrict;
    }

    public void setCompanyOrDistrict(String companyOrDistrict) {
        this.companyOrDistrict = companyOrDistrict;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getOldLoginIp() {
        return oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp;
    }

    public Date getOldLoginDate() {
        return oldLoginDate;
    }

    public void setOldLoginDate(Date oldLoginDate) {
        this.oldLoginDate = oldLoginDate;
    }
    
    
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    
    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    
    
    public String getRepresentLevel() {
        return representLevel;
    }

    public void setRepresentLevel(String representLevel) {
        this.representLevel = representLevel;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    

    public String getProviceLevel() {
        return proviceLevel;
    }

    public void setProviceLevel(String proviceLevel) {
        this.proviceLevel = proviceLevel;
    }

    public String getProvicePeriod() {
        return provicePeriod;
    }

    public void setProvicePeriod(String provicePeriod) {
        this.provicePeriod = provicePeriod;
    }

    public String getNationLevel() {
        return nationLevel;
    }

    public void setNationLevel(String nationLevel) {
        this.nationLevel = nationLevel;
    }

    public String getNationPeriod() {
        return nationPeriod;
    }

    public void setNationPeriod(String nationPeriod) {
        this.nationPeriod = nationPeriod;
    }

    
    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }
    
    

    public List<Integer> getSpecialIdList() {
        return specialIdList;
    }

    public void setSpecialIdList(List<Integer> specialIdList) {
        this.specialIdList = specialIdList;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBeginAge(){
        return beginAge;
    }
    
    public void setBeginAge(String beginAge){
        this.beginAge = beginAge;
    }
    
    public String getEndAge(){
        return endAge;
    }
    
    public void setEndAge(String endAge){
        this.endAge = endAge;
    }
    
    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    @Override
    public void preInsert() {
        
    }

    @Override
    public void preUpdate() {
    }

	public void setNpcData(Users auser) {
		// TODO Auto-generated method stub
		this.setAddress("");
		this.setEmail("");
		this.setOfficeId(auser.getOfficeId()+"");
		this.setMission("");
		this.setIdNumber(auser.getPhone());
		this.setPostalCode("");
		this.setQq("");
		this.setWeChat("");
	}

    
}