package com.weaforce.cms.entity.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.cms.entity.Registry;
import com.weaforce.core.util.DateUtil;

/**
 * 贴管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_forum_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Post implements Serializable {
	private static final long serialVersionUID = 2179838989279933659L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "128", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "POST_ID", length = 20)
	private Long postId;
	// 标题
	@Column(name = "POST_TITLE", length = 90)
	private String postTitle;
	// 简介
	@Column(name = "POST_INTRO", length = 255)
	private String postIntro;
	// 所有浏览
	@Column(name = "POST_TOTAL_VIEW", length = 10)
	private Integer postTotalView;
	// 所有回复
	@Column(name = "POST_TOTAL_REPLY", length = 10)
	private Integer postTotalReply;
	// 创建时间
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;
	// 创建时间
	@Column(name = "LAST_UPDATE_TIME", length = 20)
	private Long lastUpdateTime;
	// 论坛
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_FORUM_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_POST_FORUM_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Forum postForum;
	// 内容
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "contentPost", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "POST_ID")
	private PostContent postContent;
	// 内容HTML
	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "htmlPost", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinColumn(name = "POST_ID")
	private PostHtml postHtml;
	// 用户
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_REGISTRY_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_FORUM_POST_REGISTRY_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Registry postRegistry;
	// 父贴子(引用贴子)
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_FID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_POST_FID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Post postParent;
	// 子贴子
	@OneToMany(mappedBy = "postParent", cascade = { CascadeType.ALL })
	@org.hibernate.annotations.OrderBy(clause = "POST_ID asc")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Post> postChildren = new ArrayList<Post>();

	// 创建时间
	@Transient
	private String date;
	// 最后编辑时间
	@Transient
	private String lastUpdateDate;

	public Post() {
		postTotalView = 0;
		postTotalReply = 0;
		createTime = System.currentTimeMillis();
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostIntro() {
		return postIntro;
	}

	public void setPostIntro(String postIntro) {
		this.postIntro = postIntro;
	}

	public Integer getPostTotalView() {
		return postTotalView;
	}

	public void setPostTotalView(Integer postTotalView) {
		this.postTotalView = postTotalView;
	}

	public Integer getPostTotalReply() {
		return postTotalReply;
	}

	public void setPostTotalReply(Integer postTotalReply) {
		this.postTotalReply = postTotalReply;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public Forum getPostForum() {
		return postForum;
	}

	public void setPostForum(Forum postForum) {
		this.postForum = postForum;
	}

	public PostContent getPostContent() {
		return postContent;
	}

	public void setPostContent(PostContent postContent) {
		this.postContent = postContent;
	}

	public PostHtml getPostHtml() {
		return postHtml;
	}

	public void setPostHtml(PostHtml postHtml) {
		this.postHtml = postHtml;
	}

	public Registry getPostRegistry() {
		return postRegistry;
	}

	public void setPostRegistry(Registry postRegistry) {
		this.postRegistry = postRegistry;
	}

	public Post getPostParent() {
		return postParent;
	}

	public void setPostParent(Post postParent) {
		this.postParent = postParent;
	}

	public List<Post> getPostChildren() {
		return postChildren;
	}

	/**
	 * 加入子贴
	 * 
	 * @param child
	 */
	public void addPostChild(Post child) {
		if (child == null)
			throw new IllegalArgumentException("Null child menu!");
		if (child.getPostParent() != null)
			child.getPostParent().getPostChildren().remove(child);
		child.setPostParent(this.postParent);
		this.postChildren.add(child);
	}

	/**
	 * 删除子贴
	 * 
	 * @param child
	 */
	public void removePostChild(Post child) {
		if (child == null)
			throw new IllegalArgumentException("Null child menu!");
		child.setPostParent(null);
		this.postChildren.remove(child);
	}

	public void setPostChildren(List<Post> postChildren) {
		this.postChildren = postChildren;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getDate() {
		if (createTime == null || createTime == 0) {
			date = DateUtil
					.completeFormat(new Date(System.currentTimeMillis()));
		} else {
			date = DateUtil.completeFormat(new Date(createTime));
		}
		return date;
	}

	/**
	 * 返回W3C格式日期,如2008-08-08
	 * 
	 * @return
	 */
	public String getLastUpdateDate() {
		if (lastUpdateTime == null || lastUpdateTime == 0) {
			lastUpdateDate = DateUtil.completeFormat(new Date(System
					.currentTimeMillis()));
		} else {
			lastUpdateDate = DateUtil.completeFormat(new Date(lastUpdateTime));
		}
		return lastUpdateDate;
	}
}
