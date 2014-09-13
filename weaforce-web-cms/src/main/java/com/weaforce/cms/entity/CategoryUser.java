package com.weaforce.cms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.weaforce.entity.admin.User;

/**
 * 用户栏目对照表，实现不同用户可以访问属于自已的栏目
 * 
 * @author Manfred
 * 
 */
@Entity
@Table(name = "cms_category_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = true)
public class CategoryUser implements Serializable {
	private static final long serialVersionUID = -2355991820003900725L;

	public CategoryUser(Category userCategory, User categoryUser) {
		this.userCategory = userCategory;
		this.categoryUser = categoryUser;
	}

	// 栏目
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_CATEGORY_USER_ID")
	private Category userCategory;
	// 用户
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	@org.hibernate.annotations.ForeignKey(name = "FK_USER_CATEGORY_ID")
	private User categoryUser;

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = -2156256002675712291L;
		@Column(name = "CATEGORY_ID", length = 20)
		private Long categoryId;
		@Column(name = "USER_ID", length = 20)
		private Long userId;

		public Id() {
		}

		public Id(Long categoryId, Long userId) {
			this.categoryId = categoryId;
			this.userId = userId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id) o;
				return this.categoryId.equals(that.categoryId)
						&& this.userId.equals(that.userId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return categoryId.hashCode() + userId.hashCode();
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

	}

	@EmbeddedId
	private Id id;

	public Category getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(Category userCategory) {
		this.userCategory = userCategory;
	}

	public User getCategoryUser() {
		return categoryUser;
	}

	public void setCategoryUser(User categoryUser) {
		this.categoryUser = categoryUser;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

}
