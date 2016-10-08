package au.com.jcloud.security;

/**
 * Created by david on 13/06/16.
 */
import org.apache.commons.lang3.StringUtils;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import au.com.jcloud.model.Role;
import au.com.jcloud.model.User;

/**
 * An extension for the HTTPServletRequest that overrides the getUserPrincipal()
 * and isUserInRole(). We supply these implementations here, where they are not
 * normally populated unless we are going through the facility provided by the
 * container.
 * <p>
 * If he user or roles are null on this wrapper, the parent request is consulted
 * to try to fetch what ever the container has set for us. This is intended to
 * be created and used by the UserRoleFilter.
 * 
 * @author thein
 *
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

	User user;
	Set<Role> roles = null;
	HttpServletRequest realRequest;

	public UserRoleRequestWrapper(User user, Set<Role> roles, HttpServletRequest request) {
		super(request);
		this.user = user;
		this.roles = roles;
		this.realRequest = request;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (StringUtils.isBlank(role) || role.startsWith("*")) {
			return false;
		}
		if (roles == null || roles.size()==0) {
			return this.realRequest.isUserInRole(role);
		}
		for (Role roleItem: roles) {
			if (roleItem!=null && roleItem.getName().equals(role)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		if (this.user == null) {
			return realRequest.getUserPrincipal();
		}

		// make an anonymous implementation to just return our user
		return new Principal() {
			@Override
			public String getName() {
				return user.getUsername();
			}
		};
	}

	@Override
	public String getRemoteUser() {
		if (user == null) {
			return super.getRemoteUser();
		} else {
			return user.getUsername();
		}
	}
}