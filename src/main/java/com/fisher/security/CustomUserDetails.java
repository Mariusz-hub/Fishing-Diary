package com.fisher.security;

import com.fisher.domain.User;
import com.fisher.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//create a class of subtype UserDetails (defined by Spring Security) to represent an authentication user,
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> userRoles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();  // <span sec:authentication="principal.authorities">Roles</span> pokazuje to role przy zalogowanym uzytkowniku

        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public String getFullName(){
        return user.getFirstName()+" "+user.getLastName();
    } //Welcome <b>[[${#request.userPrincipal.principal.fullName}]]</b>

}
