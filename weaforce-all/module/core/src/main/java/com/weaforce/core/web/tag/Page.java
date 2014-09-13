package com.weaforce.core.web.tag;

import java.io.IOException;
import java.io.Writer;
import com.weaforce.core.util.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Page<T> extends Component {

	private static final Log logger = LogFactory.getLog(Page.class);

	protected String styleClass = "";
	protected String beanName = "";
	protected String formName = "";
	protected String javaScript = "";
	protected String value;

	public Page(ValueStack stack) {
		super(stack);
	}

	@SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
		// System.out.println("Here is beanName: " + beanName);
		boolean result = super.start(writer);
		PageInfo<T> pi = (PageInfo<T>) this.getStack().findValue(value);
		String strFirstPage = "首页";
		String strLastPage = "末页";
		String strNextPage = "下一页";
		String strPrevPage = "上一页";
		String strCurrentPage = "页次";
		String strGoto = "转至：";
		StringBuffer sb = new StringBuffer();
		sb.append("<script language=\"JavaScript\">\r\n");
		sb.append("<!--\r\n");
		sb.append("function ");
		sb.append(beanName);
		sb.append("_Jumping(i){ \r\n\t");
		sb.append("document.all.");
		sb.append(beanName);
		sb.append("_curPage.value = i;\r\n\t");
		sb.append("document.");
		sb.append(formName);
		sb.append(".submit();\r\n\t");
		sb.append("return ;\r\n");
		sb.append("}\r\n");
		sb.append("function ");
		sb.append(beanName);
		sb.append("_gotoPage(pagenum){ \r\n\t");
		sb.append("document.all.");
		sb.append(beanName);
		sb.append("_curPage.value = pagenum;\r\n\t");
		sb.append("document.");
		sb.append(formName);
		sb.append(".submit();\r\n\t");
		sb.append("return ;\r\n");
		sb.append("}\r\n");
		sb.append("-->\r\n");
		sb.append("\r\n</script>\r\n");
		sb.append("<table border=\"0\" align=\"center\">\r\n");
		sb.append("<tr>\r\n\t<td>\r\n\t\t");
		sb.append("<b>共 <font color=\"#930909\">");
		sb.append(pi.getTotalCount());
		sb.append("</font> 条记录</b>&nbsp;\r\n\t");
		if (pi.getTotalPageCount() == 1 || pi.getTotalPageCount() == 0) {
			sb
					.append("\t<font face=\"webdings\" color=\"#930909\">9</font><font color=\"#930909\"><b>");
			sb.append(strFirstPage);
			sb.append("</b></font>\r\n\t");
			sb
					.append("\t<font face=\"webdings\" color=\"#930909\">7</font><font color=\"#930909\"><b>");
			sb.append(strPrevPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<font color=\"#930909\"><b>");
			sb.append(strNextPage);
			sb
					.append("</b></font><font face=\"webdings\" color=\"#930909\">8</font>\r\n\t");
			sb.append("\t<font color=\"#930909\"><b>");
			sb.append(strLastPage);
			sb
					.append("</b></font><font face=\"webdings\" color=\"#930909\">:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1 && pi.getCurPage() == 1) {
			sb
					.append("\t<font face=\"webdings\" color=\"#930909\">9</font><font color=\"#930909\"><b>");
			sb.append(strFirstPage);
			sb.append("</b></font>\r\n\t");
			sb
					.append("\t<font face=\"webdings\" color=\"#930909\">7</font><font color=\"#930909\"><b>");
			sb.append(strPrevPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append((pi.getCurPage() + 1));
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strNextPage);
			sb.append("</b></font></a><font face=\"webdings\">8</font>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getTotalPageCount());
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strLastPage);
			sb.append("</b></font></a><font face=\"webdings\">:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1
				&& pi.getCurPage() < pi.getTotalPageCount()) {
			sb
					.append("\t<font face=\"webdings\">9</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(1)\"><font color=\"black\"><b>");
			sb.append(strFirstPage);
			sb.append("</b></font></a>\r\n\t");
			sb
					.append("\t<font face=\"webdings\">7</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() - 1);
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strPrevPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() + 1);
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strNextPage);
			sb.append("</b></font></a><font face=\"webdings\">8</font>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getTotalPageCount());
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strLastPage);
			sb.append("</b></font></a><font face=\"webdings\">:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1
				&& pi.getCurPage() == pi.getTotalPageCount()) {
			sb
					.append("\t<font face=\"webdings\">9</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(1)\"><font color=\"black\"><b>");
			sb.append(strFirstPage);
			sb.append("</b></font></a>\r\n\t");
			sb
					.append("\t<font face=\"webdings\">7</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() - 1);
			sb.append(")\"><font color=\"black\"><b>");
			sb.append(strPrevPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<font color=\"#930909\"><b>");
			sb.append(strNextPage);
			sb
					.append("</b></font><font face=\"webdings\" color=\"#930909\">8</font>\r\n\t");
			sb.append("\t<font color=\"#930909\"><b>");
			sb.append(strLastPage);
			sb
					.append("</b></font><font face=\"webdings\" color=\"#930909\">:</font>\r\n\t");
		}
		sb.append("\t<font color=\"black\"><b>");
		sb.append(strGoto);
		sb.append("</b></font>\r\n\t\t<select id=\"");
		sb.append(beanName);
		sb.append("_curPage\" name=\"");
		sb.append(beanName);
		sb.append(".curPage\" onchange=\"");
		sb.append(beanName);
		sb.append("_Jumping(this.value)\">");
		for (int i = 1; i <= pi.getTotalPageCount(); i++) {
			if (i == pi.getCurPage()) {
				sb.append("\r\n\t\t\t<option selected value=\"" + i + "\">第 " + i
						+ " 页</option>");
			} else {
				sb.append("\r\n\t\t\t<option value=\"" + i + "\">第 " + i
						+ " 页</option>");
			}
		}
		sb.append("\r\n\t\t</select>\r\n\t");
		sb.append("\t<font color=\"black\"><b>");
		sb.append(strCurrentPage);
		sb.append("\uFF1A</b></font><font color=\"#930909\"><b>");
		sb.append(pi.getCurPage());
		sb
				.append("</b></font>\r\n\t\t<font color=\"black\"><b>/</b></font>\r\n\t\t<font color=\"#930909\"><b>");
		sb.append(pi.getTotalPageCount());
		sb.append("</b></font><font color=\"black\"><b> 页 </b></font>");
		sb.append("\r\n\t<td>\r\n<tr>\r\n\r\n</table>");
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		}
		return result;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
