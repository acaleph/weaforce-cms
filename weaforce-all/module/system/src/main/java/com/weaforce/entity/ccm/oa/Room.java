package com.weaforce.entity.ccm.oa;

import java.io.Serializable;
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

import com.weaforce.core.entity.PrimaryEntity;

/**
 * 会议室管理类
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
@Entity
@Table(name = "fo_room")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = true)
public class Room extends PrimaryEntity implements Serializable {
	private static final long serialVersionUID = 6952695062607813861L;
	@Id
	@TableGenerator(name = "genId", table = "admin_module", pkColumnName = "MODULE_ID", valueColumnName = "MODULE_NEXT", pkColumnValue = "71", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "genId")
	@Column(name = "ROOM_ID", length = 20)
	private Long roomId;
	@Column(name = "ROOM_CODE", length = 10)
	private String roomCode;
	@Column(name = "ROOM_NAME", length = 45)
	private String roomName;
	@Column(name = "ROOM_IS_ACTIVE", length = 1)
	private String roomIsActive;
	@Column(name = "ROOM_DESCRIPTION", length = 255)
	private String roomDescription;
	//会议通知
	@OneToMany(mappedBy = "infoRoom", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MeetingInfo> roomInfo;

	public Room() {
		roomIsActive = "1";
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomIsActive() {
		return roomIsActive;
	}

	public void setRoomIsActive(String roomIsActive) {
		this.roomIsActive = roomIsActive;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public List<MeetingInfo> getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(List<MeetingInfo> roomInfo) {
		this.roomInfo = roomInfo;
	}

}
