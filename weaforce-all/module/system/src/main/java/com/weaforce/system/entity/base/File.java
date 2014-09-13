package com.weaforce.system.entity.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;

@Entity
@Table(name = "base_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class File extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 5533138822534088371L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "28", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "FILE_ID", length = 20)
	private Long fileId;
	// 模块
	@Column(name = "FILE_MODULE_ID", length = 20)
	private Long fileModuleId;
	// 模块实例
	@Column(name = "FILE_MODULE_INSTANCE_ID", length = 20)
	private Long fileModuleInstanceId;
	// 服务器文件名
	@Column(name = "FILE_SERVER", length = 45)
	private String fileServer;
	// 服务器文件路径
	@Column(name = "FILE_URL", length = 45)
	private String fileUrl;
	// 版本
	@Column(name = "FILE_VERSION_MAX", length = 3)
	private byte fileVersionMax;
	// 版本
	@Column(name = "FILE_VERSION_MID", length = 3)
	private byte fileVersionMid;
	// 版本
	@Column(name = "FILE_VERSION_MIN", length = 3)
	private byte fileVersionMin;
	// 描述
	@Column(name = "FILE_DESCRIPTION", length = 255)
	private String fileDescription;
	// 扩展名
	@Column(name = "FILE_EXTENSION", length = 5)
	private String fileExtension;
	// 本地文件名
	@Column(name = "FILE_LOCAL", length = 180)
	private String fileLocal;
	// 文件长度
	@Column(name = "FILE_LENGTH", length = 20)
	private Long fileLength;

	@Transient
	private String iconPath;

	public File() {
		fileModuleInstanceId = Long.valueOf("0");
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getFileModuleId() {
		return fileModuleId;
	}

	public void setFileModuleId(Long fileModuleId) {
		this.fileModuleId = fileModuleId;
	}

	public Long getFileModuleInstanceId() {
		return fileModuleInstanceId;
	}

	public void setFileModuleInstanceId(Long fileModuleInstanceId) {
		this.fileModuleInstanceId = fileModuleInstanceId;
	}

	public String getFileServer() {
		return fileServer;
	}

	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public byte getFileVersionMax() {
		return fileVersionMax;
	}

	public void setFileVersionMax(byte fileVersionMax) {
		this.fileVersionMax = fileVersionMax;
	}

	public byte getFileVersionMid() {
		return fileVersionMid;
	}

	public void setFileVersionMid(byte fileVersionMid) {
		this.fileVersionMid = fileVersionMid;
	}

	public byte getFileVersionMin() {
		return fileVersionMin;
	}

	public void setFileVersionMin(byte fileVersionMin) {
		this.fileVersionMin = fileVersionMin;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileLocal() {
		return fileLocal;
	}

	public void setFileLocal(String fileLocal) {
		this.fileLocal = fileLocal;
	}

	public Long getFileLength() {
		return fileLength;
	}

	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}

	public String getIconPath() {
		if (fileExtension != null)
			iconPath = com.weaforce.core.util.Global.getIconMap(fileExtension);
		return iconPath;
	}

}
