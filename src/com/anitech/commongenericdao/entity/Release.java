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
@Table(name="DB_RELEASES")
public class Release implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VERSION_ID")
	private String versionID;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="OS_NAME")
	private String osName;
	
	@Column(name="UPGRADE_TYPE")
	private String upgradeType;
	
	@Column(name="RELEASE_DATE")
	private Date releaseDate;
	
	@Column(name="ANDROID_UPGRADE_URL")
	private String androidUpgradeURL;
	
	@Column(name="IOS_UPGRADE_URL")
	private String iOSUpgradeURL;

	
	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAndroidUpgradeURL() {
		return androidUpgradeURL;
	}

	public void setAndroidUpgradeURL(String androidUpgradeURL) {
		this.androidUpgradeURL = androidUpgradeURL;
	}

	public String getiOSUpgradeURL() {
		return iOSUpgradeURL;
	}

	public void setiOSUpgradeURL(String iOSUpgradeURL) {
		this.iOSUpgradeURL = iOSUpgradeURL;
	}

}
