package online.shop.store.utils.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import online.shop.store.dto.entity.admin.Admin;
import online.shop.store.repository.AdminRepository;

@Component
@RequiredArgsConstructor
public class AdminDetailService  implements UserDetailsService{
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("không tìm thấy adminByEmail"));
        return admin;
    }
    
}
