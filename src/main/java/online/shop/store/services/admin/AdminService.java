package online.shop.store.services.admin;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import online.shop.store.dto.RegisterAdmin;
import online.shop.store.dto.RegisterLocation;
import online.shop.store.dto.entity.Admin;
import online.shop.store.dto.entity.Role;
import online.shop.store.dto.entity.RoleName;
import online.shop.store.dto.entity.location.Location;
import online.shop.store.repository.AdminRepository;
import online.shop.store.repository.RoleRepository;
import online.shop.store.services.location.LocationService;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LocationService locationService;

    @Override
    public Admin save(RegisterAdmin registerAdmin, Admin admin) {
        admin.setNameAdmin(registerAdmin.getNameAdmin());
        admin.setEmail(registerAdmin.getEmail());
        admin.setPassword(registerAdmin.getPassword());
        admin.setAvatar("url");
        admin.setLock(false);
        Set<Role> roles=new HashSet<>();
        for (String role : registerAdmin.getListRole()) {
            RoleName roleName = Enum.valueOf(RoleName.class, role);
            Role r=roleRepository.findByRoleName(roleName);
            roles.add(r); 
        }
        admin.setRoles(roles);
        Location location=locationService.save(new RegisterLocation(registerAdmin.getLocationDetail(), registerAdmin.getIdWard()));
        admin.setLocation(location);
        Admin newAdmin=adminRepository.save(admin);

        return newAdmin;
    }

    @Override
    public Page<Admin> allAdmin(int page, int total) {
        PageRequest pageDetail = PageRequest.of(page, total);
        Page<Admin> allProducts = adminRepository.findAll(pageDetail);

        return allProducts;
    }

    @Override
    public Boolean existAdminByEmail(String email){
        return adminRepository.existsByEmail(email);
    }
    
    @Override
    public Admin forgetPassword(String email){
        Optional<Admin> admin=adminRepository.findByEmail(email);
        if(!admin.isPresent()){
            new NotFoundException("not Found admin by email");
        }
        return admin.get();
    }
}
