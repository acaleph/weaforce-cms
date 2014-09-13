package com.weaforce.core.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import com.weaforce.core.common.bean.TreeNode;

public class BootStrap {
	private static final String splitButtonTemplate = "<button class=\"btn\">${TEXT}</button><button class=\"btn dropdown-toggle\" data-toggle=\"dropdown\"><span class=\"caret\"></span></button>";
	private static final String itemTemplate = "<li><a href=\"${URL}\">${ITEM}</a></li>";
	private static final String dropdownTemplate = "<ul${CLASS}>${ITEMS}</ul>";
	private static final String buttonMenuTemplate = "<div class=\"btn-group\">${BUTTON}${MENU}</div>";
	private static final String buttonBarTemplate = "<div class=\"btn-toolbar\" style=\"margin: 0;\">${BAR}</div>";
	// Button drop down or split button drop down?
	private static final String dropdownMenuTemplate = "<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">${ITEM}<b class=\"caret\"></b></a>${ITEMS}</li>";
	private static final String itemsHeadTemplate = "<ul class=\"nav nav-list\">${HEADER}${ITEMS}</ul>";
	private static final String navListTemplate = "<ul class=\"nav nav-list\">${ITEMS}</ul>";
	private static final String navHeaderTemplate = "<li${CLASS}>${HEADER}</li>";
	// Accordion
	private static final String accordionTemplate = "<div class=\"accordion\" id=\"${ID}\">${GROUPS}</div>";
	private static final String accordionGroupTemplate = "<div class=\"accordion-group\">${HEAD}${BODY}</div>";
	private static final String accordionHeadTemplate = "<div class=\"accordion-heading\"><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#${PID}\" href=\"#${ID}\">${ICON}${HEAD}</a></div>";
	private static final String accordionBodyTemplate = " <div id=\"${ID}\" class=\"accordion-body collapse\" style=\"height: 0px;\"><div class=\"accordion-inner\">${ITEMS}</div></div>";
	// Glyphicons
	private static final String iconTemplate = "<i class=\"${ICON}\"></i>";
	private static final String classNavHeader = " class=\"nav-header\"";
	private static final String classDropdownMenu = " class=\"dropdown-menu\"";
	private static final String _offset = "&nbsp";

	public BootStrap() {

	}

	/**
	 * Get the button text
	 * 
	 * @param text
	 * @return
	 */
	public static String getSplitButton(String text) {
		String splitButton = splitButtonTemplate;
		return StringUtils.replace(splitButton, "${TEXT}", text);
	}

	/**
	 * Get item element
	 * 
	 * @param url
	 *            URL
	 * @param item
	 *            item name
	 * @return
	 */
	public static String getItem(String url, String item) {
		if (url == null)
			url = "#";
		String itemTemp = itemTemplate;
		itemTemp = StringUtils.replace(itemTemp, "${URL}", url);
		itemTemp = StringUtils.replace(itemTemp, "${ITEM}", item);
		return itemTemp;
	}

	/**
	 * Generate items
	 * 
	 * @param items
	 *            Items elements <li>...</li>
	 * @return
	 */
	public static String getItems(String items[]) {
		StringBuffer itemsBuffer = new StringBuffer();
		for (int i = 0; i < items.length; i++)
			itemsBuffer.append(items[i]);
		return itemsBuffer.toString();
	}

	/**
	 * Get the drop down menu
	 * 
	 * @param items
	 *            Menu items
	 * @return
	 */
	public static String getDropdown(String className, String items[]) {
		String dropdownMenu = dropdownTemplate;
		if (className == null)
			dropdownMenu = StringUtils.replace(dropdownMenu, "${CLASS}", "");
		else
			dropdownMenu = StringUtils.replace(dropdownMenu, "${CLASS}",
					className);
		return StringUtils.replace(dropdownMenu, "${ITEMS}", getItems(items));
	}

	/**
	 * Get drop down item and menu
	 * 
	 * @param item
	 *            Drop down item
	 * @param items
	 *            Menu items
	 * @return
	 */
	public static String getDropdownMenu(String item, String items[]) {
		String dropdown = dropdownMenuTemplate;
		dropdown = StringUtils.replace(dropdown, "${ITEM}", item);
		return StringUtils.replace(dropdown, "${ITEMS}",
				getDropdown(classDropdownMenu, items));
	}

	/**
	 * Get the button and drop down menu
	 * 
	 * @param button
	 *            Button
	 * @param menu
	 *            Menu
	 * @return
	 */
	public static String getButtonMenu(String button, String menu) {
		String buttonMenu = buttonMenuTemplate;
		buttonMenu = StringUtils.replace(buttonMenu, "${BUTTON}", button);
		buttonMenu = StringUtils.replace(buttonMenu, "" + "${MENU}", menu);
		return buttonMenu;
	}

	/**
	 * Get the button bar
	 * 
	 * @param buttonMenus
	 *            Buttons and drop down menus
	 * @return
	 */

	public static String getButtonBar(String buttonMenus[]) {
		StringBuffer bar = new StringBuffer();
		for (int i = 0; i < buttonMenus.length; i++)
			bar.append(buttonMenus[i]);
		String buttonBar = buttonBarTemplate;
		return StringUtils.replace(buttonBar, "${BAR}", bar.toString());
	}

	/**
	 * Get the NAV header
	 * 
	 * @param classNameitems
	 *            [i] User defined class name
	 * @param header
	 *            Header name
	 * @return
	 */
	public static String getNavHeader(String className, String header) {
		String navHeader = navHeaderTemplate;
		if (className == null)
			navHeader = StringUtils.replace(navHeader, "${CLASS}",
					classNavHeader);
		else
			navHeader = StringUtils.replace(navHeader, "${CLASS}", className);
		navHeader = StringUtils.replace(navHeader, "${HEADER}", header);
		return navHeader;
	}

	/**
	 * Get NAV lists
	 * 
	 * @param className
	 *            User defined class name
	 * @param header
	 *            Header name
	 * @param items
	 *            Items HTML
	 * @return
	 */
	public static String getNavListWithHead(String className, String header,
			String items[]) {
		String navListHead = itemsHeadTemplate;
		navListHead = StringUtils.replace(navListHead, "${HEADER}",
				getNavHeader(className, header));
		navListHead = StringUtils.replace(navListHead, "${ITEMS}",
				getItems(items));

		return navListHead;
	}

	/**
	 * Generate NAV list items component
	 * 
	 * @param items
	 *            Items
	 * @return
	 */
	public static String getNavList(String items[]) {
		String navList = navListTemplate;
		return StringUtils.replace(navList, "${ITEMS}", getItems(items));
	}

	/**
	 * 
	 * @param pid
	 *            Accordion id
	 * @param id
	 *            Body id
	 * @param icon
	 * @param head
	 * @return
	 */
	public static String getAccordionHead(String pid, String id, String icon,
			String head) {
		String accordionHead = accordionHeadTemplate;
		accordionHead = StringUtils.replace(accordionHead, "${PID}", pid);
		accordionHead = StringUtils.replace(accordionHead, "${ID}", id);
		accordionHead = StringUtils.replace(accordionHead, "${ICON}",
				getIconItem(icon));
		// Head text
		accordionHead = StringUtils.replace(accordionHead, "${HEAD}", head);
		return accordionHead;
	}

	/**
	 * 
	 * @param id
	 *            Body id
	 * @param items
	 * @return
	 */
	public static String getAccordionBody(String id, String items) {
		String accordionBody = accordionBodyTemplate;
		accordionBody = StringUtils.replace(accordionBody, "${ID}", id);
		accordionBody = StringUtils.replace(accordionBody, "${ITEMS}", items);
		return accordionBody;
	}

	public static String getAccordionGroup(String head, String body) {
		String accordionGroup = accordionGroupTemplate;
		accordionGroup = StringUtils.replace(accordionGroup, "${HEAD}", head);
		accordionGroup = StringUtils.replace(accordionGroup, "${BODY}", body);
		return accordionGroup;
	}

	/**
	 * Generate icon element item
	 * 
	 * @param icon
	 *            icon name
	 * @param text
	 *            icon text
	 * @return
	 */
	public static String getIconItem(String iconName) {
		String icon = iconTemplate;
		icon = StringUtils.replace(icon, "${ICON}", iconName);
		return icon;
	}

	/**
	 * Generate the accordion component
	 * 
	 * @param list
	 *            Tree node list
	 * @param id
	 *            Accordion ID
	 * @return
	 */
	public static String getAccordion(List<TreeNode> list, String id) {
		StringBuffer sb = new StringBuffer();
		for (TreeNode o : list) {
			List<TreeNode> children = o.getChildren();
			if (children == null)
				children = new ArrayList<TreeNode>();
			// Head
			String headId = String.valueOf(o.getId());
			String bodyId = headId + "-" + children.size();
			String head = getAccordionHead(id, bodyId, o.getOption(), _offset
					+ o.getName());
			// Body
			int length = children.size();
			String[] items = new String[length];
			for (int i = 0; i < length; i++) {
				TreeNode node = children.get(i);
				String icon = node.getOption();
				String text = node.getName();
				String url = node.getUrl();
				String iconItem = getIconItem(icon);
				items[i] = getItem(url, iconItem + _offset + text);
			}
			String navList = getNavList(items);
			String body = getAccordionBody(bodyId, navList);
			// Appended to body area
			sb.append(getAccordionGroup(head, body));
		}
		String accordion = accordionTemplate;
		accordion = StringUtils.replace(accordion, "${ID}", id);
		accordion = StringUtils.replace(accordion, "${GROUPS}", sb.toString());
		return accordion;
	}
}
