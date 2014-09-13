package com.weaforce.system.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.core.entity.PrimaryEntity;
import com.weaforce.core.util.DateUtil;

/**
 * 公告板管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "base_board")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Board extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 2967158386970509477L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "34", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BOARD_ID", length = 20)
	private Long boardId;
	@Column(name = "BOARD_TITLE", length = 90)
	private String boardTitle;
	@Column(name = "BOARD_CONTENT")
	private String boardContent;
	@Column(name = "BOARD_IS_ACTIVE", length = 1)
	private String boardIsActive;
	@Column(name = "BOARD_WRITER", length = 45)
	private String boardWriter;
	@Column(name = "BOARD_DATE", length = 20)
	private Long boardDate;

	@Transient
	private String boardDateDate;
	@Transient
	private String enableSave;
	@Transient
	private String enableDelete;

	public Board() {
		enableSave = "1";
		enableDelete = "1";
		boardIsActive = "1";
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardIsActive() {
		return boardIsActive;
	}

	public void setBoardIsActive(String boardIsActive) {
		this.boardIsActive = boardIsActive;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Long getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Long boardDate) {
		this.boardDate = boardDate;
	}

	/**
	 * 以W3C格式显示公告日期
	 * 
	 * @return
	 */
	public String getBoardDateDate() {
		if (boardDate != null)
			boardDateDate = DateUtil.defaultFormat(new Date(boardDate));
		else
			boardDateDate = DateUtil.defaultFormat(new Date(System.currentTimeMillis()));
		return boardDateDate;
	}

	/**
	 * 以UTC格式保存公告日期
	 * 
	 * @param boardDate
	 */
	public void setBoardDateDate(String boardDateDate) {
		if (StringUtils.isNotEmpty(boardDateDate))
			boardDate = DateUtil.getUTCDate(boardDateDate);
		this.boardDateDate = boardDateDate;
	}

	public String getEnableSave() {
		return enableSave;
	}

	public void setEnableSave(String enableSave) {
		this.enableSave = enableSave;
	}

	public String getEnableDelete() {
		return enableDelete;
	}

	public void setEnableDelete(String enableDelete) {
		this.enableDelete = enableDelete;
	}

}
