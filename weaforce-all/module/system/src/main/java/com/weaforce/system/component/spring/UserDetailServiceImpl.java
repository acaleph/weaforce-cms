package com.weaforce.system.component.spring;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.entity.admin.Authority;
import com.weaforce.entity.admin.Role;
import com.weaforce.entity.admin.User;
import com.weaforce.system.service.ISystemService;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author acaleph8@yahoo.com.cn
 */
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	@Qualifier("systemService")
	private ISystemService systemService;;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		User user = systemService.getUserByLogin(userName);
		if (user == null)
			throw new UsernameNotFoundException("用户" + userName + " 不存在");
		else {
			user.setUserLastLoginTime(user.getUserCurrentLoginTime());
			user.setUserCurrentLoginTime(System.currentTimeMillis());
			user = systemService.saveUser(user);
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		User userdetail = new User(user.getUserId(), user.getAccount(),
				user.getUserLogin(), user.getUserLogin(), user.getUserPwd(),
				user.getUserCity(), user.getUserZone(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);
		return userdetail;
	}

	/**
	 * 获得用户所有角色的权限.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : user.getUserRole()) {
			for (Authority authority : role.getRoleAuthority()) {
				authSet.add(new SimpleGrantedAuthority(authority
						.getAuthorityCode()));
			}
		}
		return authSet;
	}
}
