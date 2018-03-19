package com.ctgu.npc.business.sug.entity;

import java.util.List;

/**
 * 议案详细实体类
 * @author 旺旺
 *
 */
public class BillsEntity {
	
	Suggestion sug;
	
	List<FilePuton> plist ;//议案上传文件列表

	public Suggestion getSug() {
		return sug;
	}

	public void setSug(Suggestion sug) {
		this.sug = sug;
	}

	public List<FilePuton> getPlist() {
		return plist;
	}

	public void setPlist(List<FilePuton> plist) {
		this.plist = plist;
	}

	
}
