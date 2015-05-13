package core.service.security;

import core.entity.Account;
import core.entity.Role;
import core.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Adrian on 12/05/2015.
 */
@Service("accountDetailsService")
@Transactional
public class AccountDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AccountRepo accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Finding by email: " + email);
        Account acc = accountRepository.findAccountByEmail(email);
        if (acc == null) {
            System.out.println("AccountDetailsService - No username was found.");
            throw new UsernameNotFoundException("No username found. Provided email was: " + email);
        }

        List<GrantedAuthority> authorities =
                buildUserAuthority(acc.getRoles());

        return buildUserForAuthentication(acc, authorities);
       /* boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;*/

        /*return new org.springframework.security.core.userdetails.User(acc.getUsername(), acc.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(acc.getRoles()));*/
    }

    private User buildUserForAuthentication(Account acc,
                                            List<GrantedAuthority> authorities) {
        return new User(acc.getEmail(), acc.getPassword(),
                acc.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}
