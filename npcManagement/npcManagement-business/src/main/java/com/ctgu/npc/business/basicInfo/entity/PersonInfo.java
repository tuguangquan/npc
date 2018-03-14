package com.ctgu.npc.business.basicInfo.entity;


import com.ctgu.npc.business.sys.entity.Users;

public class PersonInfo {
	
	Npc npc;
	Users user;
	public Npc getNpc() {
		return npc;
	}
	public void setNpc(Npc npc) {
		this.npc = npc;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	

}
