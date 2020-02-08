package com.usercontrol.apirest.repository.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.usercontrol.apirest.models.User;
import com.usercontrol.apirest.repository.FindUserRepo;



@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	private final FindUserRepo user;
	
	@Autowired
	public CustomUserDetailsService(FindUserRepo user) {
		super();
		this.user = user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = Optional.ofNullable(user.findByEmail(email)).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado."));
		List<GrantedAuthority> authorithyListAdmin =  AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		List<GrantedAuthority> authorithyListUser =  AuthorityUtils.createAuthorityList("ROLE_USER");
		
		return new org.springframework.security.core.userdetails.User
				(usuario.getEmail(), usuario.getSenha(), 
						usuario.isPerfil() ? authorithyListAdmin : authorithyListUser);
		
	}

}
