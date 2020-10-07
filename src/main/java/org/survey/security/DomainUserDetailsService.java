package org.survey.security;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.survey.domain.User;
import org.survey.domain.UserExtra;
import org.survey.repository.UserExtraRepository;
import org.survey.repository.UserRepository;

/**
 * Authenticate a user from the database.
 */


@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {
	
	
	
    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);
    
    @Autowired
    private UserExtraRepository userExtraRepository;
    
    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin);
        return userFromDatabase.map(user -> {
            if (!user.getActivated()) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }
            
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
            /**
             * Custom Authorities
             * */
            /*log.debug(">>>>>>>>>>>>>>>>User ID : {}",user.getId());
            EmployeeMaster employeeMaster = employeeMasterRepository.findByUserId(user.getId());
            log.debug(">>>>>>>>>>>>>>>>Employee : {}", employeeMaster);
            //log.debug(">>>>>>>>>>>>>>>>PostMaster :{}", employeeMaster.getPostMaster());
           // log.debug(">>>>>>>>>>>>>>>>RoleMaster :{}", employeeMaster.getRolesMasters());
            if(employeeMaster!=null)
            	for(RolesMaster role:employeeMaster.getRolesMasters()){
            		log.debug("!!!!!!!!!In Role Foreach:::{}",role);
            		//log.debug(">>>>>>>>>>>>>>>Services::{}",role.getServiceMasters());
            		for(ServiceMaster service: role.getServiceMasters()){
            			log.debug(">>>>>>>>>>>>>>>service getServiceName : {}",service.getServicename());
	            		for(Authority authority: service.getAuthorities()){
	            			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
	            			log.debug(">>>>>>>>>>>>>>>>authority getName : {}",authority.getName());
	            		}
	            		
	            	}
	            }
            */
            org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(lowercaseLogin,
                    user.getPassword(),
                    grantedAuthorities);
            
            
            
            
            UserExtra userExtra = userExtraRepository.findByUserId(user.getId());
            /*return new CurrentUser( springUser, userExtra, employeeMaster);*/
            return new CurrentUser( springUser, userExtra);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
        "database"));
    }
}
