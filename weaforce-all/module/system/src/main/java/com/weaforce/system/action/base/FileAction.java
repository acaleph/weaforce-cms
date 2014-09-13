package com.weaforce.system.action.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.common.bean.Node;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.component.struts2.StrutsUtil;
import com.weaforce.system.entity.base.File;
import com.weaforce.entity.app.Module;
import com.weaforce.system.service.IBaseService;

@ParentPackage("default")
@Namespace("/system/base")
public class FileAction extends AbstractCrudAction<File> {
	private static final long serialVersionUID = -589123795748025764L;

	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private File file;
	private Long fileId;

	private List<java.io.File> uploads = new ArrayList<java.io.File>();
	private List<String> uploadFileName = new ArrayList<String>();
	private List<String> fileVersionMaxs;
	private List<String> fileVersionMids;
	private List<String> fileVersionMins;

	private List<File> fileList = new ArrayList<File>();
	private Long fileModuleId;
	private Long fileModuleInstanceId;
	private List<Long> fileIds = new ArrayList<Long>();

	private String queryLocal;
	private Long queryModuleId;

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	
	protected void prepareModel() throws Exception {
		file = baseService.prepareFile(file, fileId);
	}

	/**
	 * 如果没有选择模块,不允许上传
	 */
	
	public String input() throws Exception {
		return INPUT;
	}

	
	public String save() throws Exception {
		file = baseService.saveFile(file);
		return list();
	}

	/**
	 * 上载文件:account creator 由interceptor维护
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		baseService.uploadFile(queryModuleId, uploadFileName, uploads,
				fileVersionMaxs, fileVersionMids, fileVersionMins);
		return list();
	}

	
	public String list() throws Exception {
		pageInfo = baseService.getFilePage(pageInfo, getAdmin().getAccount(),
				getAdmin().getUserLogin(), queryModuleId, queryLocal);
		return SUCCESS;
	}


	
	public String delete() throws Exception {
		// The blob must be free not boundled to a module instance.
		if (fileId != null) {
			file = baseService.getFile(fileId);
			java.io.File file = new java.io.File(this.file.getFileUrl()
					+ this.file.getFileServer());
			if (file.exists())
				file.delete();
			baseService.deleteFile(fileId);
		}
		return list();
	}

	/**
	 * 取得自由文件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String attachment() throws Exception {
		StringBuffer sb = new StringBuffer();
		fileList = baseService.getFileListByModule(getAdmin().getAccount(),
				getAdmin().getUserLogin(), fileModuleId, fileModuleInstanceId);
		for (File f : fileList) {
			if (sb.length() == 0)
				sb.append("[{\"fileId\":\"" + f.getFileId()
						+ "\",\"fileLocal\":\"" + f.getFileLocal()
						+ "\",\"fileIconPath\":\"" + f.getIconPath()
						+ "\",\"fileExtension\":\"" + f.getFileExtension()
						+ "\",\"fileVersion\":\"" + f.getFileVersionMax() + "-"
						+ f.getFileVersionMid() + "-" + f.getFileVersionMin()
						+ "\"}");
			else
				sb.append(",{\"fileId\":\"" + f.getFileId()
						+ "\",\"fileLocal\":\"" + f.getFileLocal()
						+ "\",\"fileIconPath\":\"" + f.getIconPath()
						+ "\",\"fileExtension\":\"" + f.getFileExtension()
						+ "\",\"fileVersion\":\"" + f.getFileVersionMax() + "-"
						+ f.getFileVersionMid() + "-" + f.getFileVersionMin()
						+ "\"}");
		}
		if (sb.length() > 0)
			sb.append("]");
		return StrutsUtil.renderJSON(sb.toString());
	}

	/**
	 * 文件所属模块
	 * 
	 * @param fileModuleId
	 */
	public void setFileModuleId(Long fileModuleId) {
		this.fileModuleId = fileModuleId;
	}

	/**
	 * 文件所属模块实例
	 * 
	 * @param fileModuleInstanceId
	 */
	public void setFileModuleInstanceId(Long fileModuleInstanceId) {
		this.fileModuleInstanceId = fileModuleInstanceId;
	}

	/**
	 * 文件列表
	 * 
	 * @param fileIds
	 */
	public void setFileIds(List<Long> fileIds) {
		this.fileIds = fileIds;
	}

	/**
	 * 保存附件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveAttach() throws Exception {
		StringBuffer sb = new StringBuffer();
		System.out.println("fileModuleId: " + fileModuleId + " fileModuleInstanceId: " + fileModuleInstanceId);
		for (Long l : fileIds) {
			File f = baseService.getFile(l);
			sb
					.append("<img src=\""
							+ f.getIconPath()
							+ "\" + alt=\""
							+ f.getFileLocal()
							+ "\"> <img class=\"pointerImage\" border=\"0\" title=\"Delete\" alt=\"Delete\" src=\"/common/image/delete_inline.gif\" onClick=\"deleteAttach("
							+ f.getFileId() + ")\"> &nbsp;");
			// 保存附件
			f.setFileModuleId(fileModuleId);
			f.setFileModuleInstanceId(fileModuleInstanceId);
			baseService.saveFile(f);
		}
		return StrutsUtil.renderHTML(sb.toString());
	}

	/**
	 * 删除附件,回传文件名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteAttach() throws Exception {
		if (fileId != null) {
			file = baseService.getFile(fileId);
			file.setFileModuleId(fileModuleId);
			file.setFileModuleInstanceId(Long.valueOf(0));
			file = baseService.saveFile(file);
			return StrutsUtil.renderText(file.getFileLocal().toString());
		}
		return StrutsUtil.renderText(file.getFileLocal());
	}

	public List<Module> getModuleList() {
		List<Module> moduleList = new ArrayList<Module>();
		moduleList = baseService.getModuleList("0", "1");
		Module o = new Module();
		o.setModuleNameCn("--- All ---");
		moduleList.add(0, o);
		return moduleList;
	}

	/**
	 * getUpload() not getUploads()
	 * 
	 * @return
	 */

	public void setUpload(List<java.io.File> uploads) {
		this.uploads = uploads;
	}

	/**
	 * 取得文件名
	 */
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setFileVersionMaxs(List<String> fileVersionMaxs) {
		this.fileVersionMaxs = fileVersionMaxs;
	}

	public void setFileVersionMids(List<String> fileVersionMids) {
		this.fileVersionMids = fileVersionMids;
	}

	public void setFileVersionMins(List<String> fileVersionMins) {
		this.fileVersionMins = fileVersionMins;
	}

	/**
	 * 设置版本List
	 * 
	 * @return
	 */
	public List<Node> getVersionList() {
		List<Node> versionList = new ArrayList<Node>();
		for (int i = 0; i < 6; i++) {
			Node node = new Node();
			node.setId(i);
			node.setName(String.valueOf(i));
			versionList.add(node);
		}
		return versionList;
	}

	public String getQueryLocal() {
		return queryLocal;
	}

	public void setQueryLocal(String queryLocal) {
		this.queryLocal = queryLocal;
	}

	public Long getQueryModuleId() {
		// 默认取第一个记录的moduleId
		return queryModuleId;
	}

	public void setQueryModuleId(Long queryModuleId) {
		this.queryModuleId = queryModuleId;
	}

	public File getModel() {
		return file;
	}

}
