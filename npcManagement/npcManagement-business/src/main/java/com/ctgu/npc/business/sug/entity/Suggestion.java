package com.ctgu.npc.business.sug.entity;

public class Suggestion {
	private int id;//ID
	private String title;//标题
	private String content;//内容
	private String firstWriterID;//领衔人
	private String secondWriterIDS;//联名人
	private String year;
	private String meeting; // 1闭会间 2大会间
	private String secondary;//几届
	private String sequence;//几次
	private Integer code;//编号
	private String classify;//分类
	private String type;//1建议2议案
	private String keyWord;//关键词
	private String tel;//电话
	private String address;//地址
	private String teamID;//代表团
	private String sgmanageUnit;//建议承办单位
	private String sgsecondUnit;//建议会办单位
	private Integer manageUnitID;//承办单位
	private String secondUnitIDS;//会办单位
	private String createTime;//创建时间
	private String createUserID;//创建人
	private String checkTime;//审核时间
	private Integer checkUserID;//审核人
	private String checkRemark;//审核意见
	private String importantFlag = "2";//1是2否
	private String excellentFlag = "2";//1是2否
	private String privateFlag = "1";//1公开2内部公开3不公开
	private String supervisingFlag = "2";//1督办2不督办
	private String putOnFlag = "0"; //0未立案1立案
	private String status;//-2未审核-1暂存0退回1审核2转交3交办4办理5评价6办结
	private String attevaluation;//1满意，2基本满意，3不满意
	private String resevaluation;//1满意，2基本满意，3不满意
	private String evaluate;//不满意理由
	private String result;//A,B,C,D
	private String level;//系统级别
	private String fileName;//附件
	
	private String firstWriter;//领衔人
	private String mission;//代表团
	private String checkUserName;
	private String createUserName;
	
	private String secondWriter;//联名人
	private String state; //状态(中文)
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFirstWriterID() {
		return firstWriterID;
	}
	public void setFirstWriterID(String firstWriterID) {
		this.firstWriterID = firstWriterID;
	}
	public String getSecondWriterIDS() {
		return secondWriterIDS;
	}
	public void setSecondWriterIDS(String secondWriterIDS) {
		this.secondWriterIDS = secondWriterIDS;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMeeting() {
		return meeting;
	}
	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
	public String getSecondary() {
		return secondary;
	}
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public String getSgmanageUnit() {
		return sgmanageUnit;
	}
	public void setSgmanageUnit(String sgmanageUnit) {
		this.sgmanageUnit = sgmanageUnit;
	}
	public String getSgsecondUnit() {
		return sgsecondUnit;
	}
	public void setSgsecondUnit(String sgsecondUnit) {
		this.sgsecondUnit = sgsecondUnit;
	}
	public Integer getManageUnitID() {
		return manageUnitID;
	}
	public void setManageUnitID(Integer manageUnitID) {
		this.manageUnitID = manageUnitID;
	}
	public String getSecondUnitIDS() {
		return secondUnitIDS;
	}
	public void setSecondUnitIDS(String secondUnitIDS) {
		this.secondUnitIDS = secondUnitIDS;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getCheckUserID() {
		return checkUserID;
	}
	public void setCheckUserID(Integer checkUserID) {
		this.checkUserID = checkUserID;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	public String getImportantFlag() {
		return importantFlag;
	}
	public void setImportantFlag(String importantFlag) {
		this.importantFlag = importantFlag;
	}
	public String getExcellentFlag() {
		return excellentFlag;
	}
	public void setExcellentFlag(String excellentFlag) {
		this.excellentFlag = excellentFlag;
	}
	public String getPrivateFlag() {
		return privateFlag;
	}
	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}
	public String getSupervisingFlag() {
		return supervisingFlag;
	}
	public void setSupervisingFlag(String supervisingFlag) {
		this.supervisingFlag = supervisingFlag;
	}
	public String getPutOnFlag() {
		return putOnFlag;
	}
	public void setPutOnFlag(String putOnFlag) {
		this.putOnFlag = putOnFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttevaluation() {
		return attevaluation;
	}
	public void setAttevaluation(String attevaluation) {
		this.attevaluation = attevaluation;
	}
	public String getResevaluation() {
		return resevaluation;
	}
	public void setResevaluation(String resevaluation) {
		this.resevaluation = resevaluation;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getFirstWriter() {
		return firstWriter;
	}
	public void setFirstWriter(String firstWriter) {
		this.firstWriter = firstWriter;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getSecondWriter() {
		return secondWriter;
	}
	public void setSecondWriter(String secondWriter) {
		System.out.println("2ndwriter"+secondWriter);
		this.secondWriter = secondWriter;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
