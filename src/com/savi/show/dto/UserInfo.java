package com.savi.show.dto;

import java.util.Date;

public class UserInfo {
	private Long id;
	private String name;
	private Integer ipVersion;
	private String ipAddress;
	private String time;
	private Integer status;
	private String switchName;
	private Integer ifIndex;
	private Long privateTime;
	public Long getPrivateTime() {
		return privateTime;
	}

	public void setPrivateTime(Long privateTime) {
		this.privateTime = privateTime;
	}

	@SuppressWarnings("deprecation")
	public UserInfo(PreUserInfo savibindingtablehis,int flag){
		this.id = savibindingtablehis.getId();
		this.name = savibindingtablehis.getUserName();
		this.ipVersion = savibindingtablehis.getIpAddressType();
		this.ipAddress = savibindingtablehis.getIpAddress();
		if(flag==1){
			this.status = 1;
			this.time=new Date(savibindingtablehis.getStartTime()).toLocaleString();
			this.privateTime=savibindingtablehis.getStartTime();
		}else if(flag==2){
			this.status = 0;
			this.privateTime=savibindingtablehis.getEndTime();
			this.time=new Date(savibindingtablehis.getEndTime()).toLocaleString();
		}
		this.ifIndex = savibindingtablehis.getIfIndex();
		this.switchName = savibindingtablehis.getSwitchName();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIpVersion() {
		return ipVersion;
	}
	public void setIpVersion(Integer ipVersion) {
		this.ipVersion = ipVersion;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSwitchName() {
		return switchName;
	}
	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}
	public Integer getIfIndex() {
		return ifIndex;
	}
	public void setIfIndex(Integer ifIndex) {
		this.ifIndex = ifIndex;
	}
}
