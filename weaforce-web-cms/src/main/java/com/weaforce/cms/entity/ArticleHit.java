package com.weaforce.cms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 文章点击管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "cms_article_hit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class ArticleHit implements Serializable {
	private static final long serialVersionUID = 1151164350817622741L;
	// 主键
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "159", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "HIT_ID", length = 20)
	private Long hitId;
	// 文章主键
	@Column(name = "HIT_ARTICLE_ID", length = 20)
	private Long hitArticleId;
	// IP
	@Column(name = "HIT_IP", length = 20)
	private String hitIp;
	// 文章主键
	@Column(name = "CREATE_TIME", length = 20)
	private Long createTime;

	public ArticleHit() {
		createTime = System.currentTimeMillis();
	}

	public Long getHitId() {
		return hitId;
	}

	public void setHitId(Long hitId) {
		this.hitId = hitId;
	}

	public Long getHitArticleId() {
		return hitArticleId;
	}

	public void setHitArticleId(Long hitArticleId) {
		this.hitArticleId = hitArticleId;
	}

	public String getHitIp() {
		return hitIp;
	}

	public void setHitIp(String hitIp) {
		this.hitIp = hitIp;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
