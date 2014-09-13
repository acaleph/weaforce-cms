package com.weaforce.cms.entity.social;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "cms_social_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

@Proxy(lazy = true)
public class Book implements Serializable {
	private static final long serialVersionUID = -2164925105352621284L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "90", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "BOOK_ID", length = 20)
	private Long bookId;
	// Name
	@Column(name = "BOOK_NAME", length = 45)
	private String bookName;
	// URL
	@Column(name = "BOOK_URL", length = 90)
	private String bookURL;
	// Owner
	@Column(name = "BOOK_OWNER", length = 45)
	private String bookOwner;
	// Request
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "requestBook")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<BookRequest> bookRequest = new ArrayList<BookRequest>();

	public Book() {

	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookURL() {
		return bookURL;
	}

	public void setBookURL(String bookURL) {
		this.bookURL = bookURL;
	}

	public String getBookOwner() {
		return bookOwner;
	}

	public void setBookOwner(String bookOwner) {
		this.bookOwner = bookOwner;
	}

	public List<BookRequest> getBookRequest() {
		return bookRequest;
	}

	public void setBookRequest(List<BookRequest> bookRequest) {
		this.bookRequest = bookRequest;
	}

}
