package com.taks.prueba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.taks.prueba.entity.RolEntity;
import com.taks.prueba.entity.UsuarioEntity;
import com.taks.prueba.repository.UsuarioRP;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private  UsuarioRP usuarioRP;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UsuarioEntity usuarioEntity = usuarioRP.findByEmail(email);
        if (usuarioEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for( RolEntity  rol: usuarioEntity.getRolEntities()) {
        	grantedAuthorities.add(new SimpleGrantedAuthority( rol.getRol()));
        }
        
        return new User(usuarioEntity.getEmail(), usuarioEntity.getPassword(), grantedAuthorities);
	
	}

}
