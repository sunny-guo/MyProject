package com.base.model;

import java.sql.Timestamp;

/*
** Copyright (c) 2008, 2009, 2010
**      The Regents of the Tsinghua University, PRC.  All rights reserved.
**
** Redistribution and use in source and binary forms, with or without  modification, are permitted provided that the following conditions are met:
** 1. Redistributions of source code must retain the above copyright  notice, this list of conditions and the following disclaimer.
** 2. Redistributions in binary form must reproduce the above copyright  notice, this list of conditions and the following disclaimer in the  documentation and/or other materials provided with the distribution.
** 3. All advertising materials mentioning features or use of this software  must display the following acknowledgement:
**  This product includes software (iNetBoss) developed by Tsinghua University, PRC and its contributors.
** THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
** ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
** IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
** ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
** FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
** DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
** OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
** HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
** LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
** OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
** SUCH DAMAGE.
*
*/
public class FaultHistory extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private Timestamp beginTime;
	private String faultIp;
	private String deviceName;
	private String persistTime;
	private String faultReason;
	private Timestamp recoverTime;	

	public String getFaultIp() {
		return faultIp;
	}
	public void setFaultIp(String faultIp) {
		this.faultIp = faultIp;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getFaultReason() {
		return faultReason;
	}
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getRecoverTime() {
		return recoverTime;
	}
	public void setRecoverTime(Timestamp recoverTime) {
		this.recoverTime = recoverTime;
	}
	public String getPersistTime() {
		return persistTime;
	}
	public void setPersistTime(String persistTime) {
		this.persistTime = persistTime;
	}
}
