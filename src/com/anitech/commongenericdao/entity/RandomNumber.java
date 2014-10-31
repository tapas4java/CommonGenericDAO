package com.anitech.commongenericdao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tapas
 */
@Entity
@Table(name="DB_RANDOM_NUMBER_MGR")
public class RandomNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="APPLICATION_RANDOM_NUMBER")
	private String appRandomNumber;
	
	@Column(name="RANDOM_NUMBER")
	private String hsmRandomNumber;
	
	@Column(name="TIME_STAMP")
	private Date timeStamp;
	
	@Column(name="SERVER_NAME")
	private String serverName;

	
	public String getAppRandomNumber() {
		return appRandomNumber;
	}

	public void setAppRandomNumber(String appRandomNumber) {
		this.appRandomNumber = appRandomNumber;
	}

	public String getHsmRandomNumber() {
		return hsmRandomNumber;
	}

	public void setHsmRandomNumber(String hsmRandomNumber) {
		this.hsmRandomNumber = hsmRandomNumber;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
