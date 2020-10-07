package org.survey.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.survey.domain.UserExtra;

public class CurrentUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8255499505241223194L;

	private final Logger log = LoggerFactory.getLogger(CurrentUser.class);
	
	User user;   
	UserExtra userExtra;
	
	/*public CurrentUser(User user,  UserExtra userExtra,EmployeeMaster employeeMaster){
		this.employeeMaster = employeeMaster;
		this.user = user;
		this.userExtra = userExtra;
	}*/
	public CurrentUser(User user,  UserExtra userExtra){
		this.user = user;
		this.userExtra = userExtra;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		log.debug("user getAuthorities : {}",user.getAuthorities());
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
	/*public String getEmployeeName(){
		if(this.employeeMaster==null)
			return user.getUsername();
		
		return (this.employeeMaster.getEmployeefirstname()+" "+this.employeeMaster.getEmployeelastname()).toUpperCase();
	}
	
	public PostMaster getPostMasterDetails(){
		if(this.employeeMaster==null)
			return null;
		return (this.employeeMaster.getPostMaster());
	}
	
	public String getEmployeePhotoPath(){
		if(this.employeeMaster==null)
			return "default";
		return (this.employeeMaster.getPhotopath());
	}
	
	public Integer getEmployeeId(){
		if(this.employeeMaster==null)
			return 0;
		return (this.employeeMaster.getEmployeeid());
	}
	
	public Integer getEmployeePostId(){
		if(this.employeeMaster==null)
			return 0;
		return (this.employeeMaster.getPostMaster().getId().intValue());
	}
	
	public Integer getSuperiorPostId(){
		if(this.employeeMaster==null)
			return 0;
		return (this.employeeMaster.getPostMaster().getSuperiorpostid());
	}
	
	
	public EmployeeMaster getEmployeeMaster(){
		
		return (this.employeeMaster);
	}
	public Set<RolesMaster> getRoleMasterDetails(){
		if(this.employeeMaster==null)
			return null;
		return (this.employeeMaster.getRolesMasters());
	}
	
	public in.serviceshub.cgg.pms.domain.User getUserMasterDetails(){
		if(this.employeeMaster==null)
			return null;
		return (in.serviceshub.cgg.pms.domain.User) (this.employeeMaster.getUser());
	}*/
	
}
