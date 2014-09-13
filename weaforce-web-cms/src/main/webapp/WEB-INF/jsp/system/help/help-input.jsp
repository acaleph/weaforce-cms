<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="96%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 帮助信息</td>
	</tr>
	<tr>
		<td width="598" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="198" align="center"><img border="0"
			src="/common/image/main/CreateArea.jpg" width="131" height="155"
			alt=""></td>

		<td width="511" align="center"><s:form
			action="help!save.action" method="POST">
			<s:hidden name="help.helpId" value="%{help.helpId}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="stripe" bgcolor="#FFFFFF">
				<tr bgcolor="#FFFFFF">
					<td height="30" width="18%">英文名称:</td>
					<td height="30" colspan="3"><s:textfield name="help.helpTitle"
						value="%{help.helpTitle}" size="78" /></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td>系统模块:</td>
					<td><s:select name="help.helpModuleId"
						value="%{help.helpModuleId}" list="helpModule" listKey="id"
						listValue="name" required="true" /></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="8" height="33">
					<div align="right"><input
						onClick="doSimpleSubmit('0','help!save.action');" type="button"
						name="Save" value="Save" class="button"> &nbsp;&nbsp;<input
						onClick="doSimpleSubmit('0','help!doDelete.action');"
						type="button" name="Delete" value="Delete" class="button">
					&nbsp;&nbsp;<input onClick="javascript:window.history.back();"
						type="button" name="Return" value="Return" class="button"></div>
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>