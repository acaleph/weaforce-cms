package com.weaforce.cms.entity.social;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "cms_social_book_share_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Proxy(lazy = true)
public class BookRequest implements Serializable {
	private static final long serialVersionUID = 4511428134349198515L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "182", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "REQUEST_ID", length = 20)
	private Long requestId;
	// Date
	@Column(name = "REQUEST_DATE", length = 20)
	private Long requestDate;
	// User login
	@Column(name = "REQUEST_USER_LOGIN", length = 45)
	private String requestUserLogin;
	// Car share
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_BOOK_ID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "FK_REQUEST_BOOK_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private CarShare requestBook;

	public BookRequest() {

	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Long requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestUserLogin() {
		return requestUserLogin;
	}

	public void setRequestUserLogin(String requestUserLogin) {
		this.requestUserLogin = requestUserLogin;
	}

	public CarShare getRequestBook() {
		return requestBook;
	}

	public void setRequestBook(CarShare requestBook) {
		this.requestBook = requestBook;
	}

}
