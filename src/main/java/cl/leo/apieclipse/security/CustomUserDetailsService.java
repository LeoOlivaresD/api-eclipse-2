package cl.leo.apieclipse.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.leo.apieclipse.models.Roles;
import cl.leo.apieclipse.models.UsersEntity;
import cl.leo.apieclipse.repositories.IUserEntityRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private IUserEntityRepository userRepository;

	@Autowired
	public CustomUserDetailsService(IUserEntityRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Collection<GrantedAuthority> mapToAutorities(List<Roles> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity usersEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new User(usersEntity.getUsername(), usersEntity.getPassword(), mapToAutorities(usersEntity.getRoles()));
	}

}
