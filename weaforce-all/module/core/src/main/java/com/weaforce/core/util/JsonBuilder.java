package com.weaforce.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weaforce.core.common.bean.Node;
import com.weaforce.core.common.bean.TreeNode;

public class JsonBuilder {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static Logger logger = LoggerFactory.getLogger(JsonBuilder.class);

	public JsonBuilder() {
	}

	/**
	 * Get object mapper
	 * 
	 * @return
	 */
	public synchronized static ObjectMapper getInstance(boolean createNew) {
		if (createNew) {
			return new ObjectMapper();
		}
		return objectMapper;
	}

	/**
	 * Convert the bean list to be JSON string
	 * 
	 * @param beanList
	 *            Bean list collection
	 * @return String
	 * @throws IOException
	 */
	public String beanToJson(List<?> beanList) throws IOException {
		StringWriter writer = new StringWriter();
		// JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
		objectMapper.writeValue(writer, beanList);
		// gen.close();
		String json = writer.toString();
		writer.close();
		return json;
	}

	/**
	 * Write collection to JSON
	 * 
	 * @param object
	 *            Collection object
	 * @return
	 */
	public static String toJson(Object object) {

		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("Ｗrite object to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * Convert the bean set to be JSON string
	 * 
	 * @param beanSet
	 *            Bean set collection
	 * @return
	 * @throws IOException
	 */
	public String beanToJson(Set<T> beanSet) throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
		objectMapper.writeValue(gen, beanSet);
		gen.close();
		String json = writer.toString();
		writer.close();
		return json;
	}

	/**
	 * Save the node list to be a map
	 * 
	 * @param map
	 *            Node map
	 * @param list
	 *            Node list
	 */
	public static void setTreeNodeMap(Map<Long, List<TreeNode>> map,
			List<Node> list, final boolean onlyLeafCheck) {
		for (final Node n : list) {
			List<TreeNode> children = map.get(n.getPid());
			if (children == null) {
				children = new ArrayList<TreeNode>();
			}
			children.add(new TreeNode() {
				{
					setId(n.getId());
					setPid(n.getPid());
					setName(n.getName());
					setChecked(n.getChecked());
					if (onlyLeafCheck)
						setNocheck(!n.getIsLeaf());
					setIsParent(!n.getIsLeaf());
					setSeq(n.getSeq());
					setOpen(true);
				}
			});
			map.put(n.getPid(), children);
		}
	}

	/**
	 * Generate the tree collection structure
	 * 
	 * @param map
	 *            Tree node map
	 * @param ts
	 *            Parent tree node
	 */
	public static void setTreeNode(Map<Long, List<TreeNode>> map, TreeNode ts) {
		List<TreeNode> children = map.get(ts.getId());
		if (children != null) {
			for (TreeNode _ts : children) {
				setTreeNode(map, _ts);
			}
			Collections.sort(children);
			ts.setChildren(children);
		}
	}
}
