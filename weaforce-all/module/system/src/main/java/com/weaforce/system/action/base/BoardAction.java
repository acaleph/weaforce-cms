package com.weaforce.system.action.base;

import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weaforce.core.util.DateUtil;
import com.weaforce.system.component.struts2.AbstractCrudAction;
import com.weaforce.system.entity.base.Board;
import com.weaforce.system.service.IBaseService;

@ParentPackage("default")
@Namespace("/system/base")
public class BoardAction extends AbstractCrudAction<Board> {

	private static final long serialVersionUID = -1299514972704230629L;
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private Board board;
	private Long boardId;

	private String queryTitle;
	private String queryDate;
	private String currentLogin;

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	
	protected void prepareModel() throws Exception {
		if (boardId == null)
			board = new Board();
		else {
			board = baseService.getBoard(boardId);
			if (!board.getCreator().equals(getAdmin().getUserLogin()))
				board.setEnableSave("0");
		}
	}

	
	public String input() throws Exception {
		currentLogin = getAdmin().getUserLogin();
		return INPUT;
	}

	
	public String save() throws Exception {
		board = baseService.saveBoard(board);
		return list();
	}

	
	public String list() throws Exception {
		if (queryDate == null)
			queryDate = DateUtil.defaultFormat(new Date(System
					.currentTimeMillis()));
		currentLogin = getAdmin().getUserLogin();
		pageInfo = baseService.getBoardPage(pageInfo, getAdmin().getAccount(),
				queryTitle, queryDate);
		return SUCCESS;
	}

	/**
	 * 删除公告
	 */
	
	public String delete() throws Exception {
		if (boardId != null)
			baseService.deleteBoard(boardId);
		return list();
	}


	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getCurrentLogin() {
		return currentLogin;
	}

	public Board getModel() {
		return board;
	}

}
