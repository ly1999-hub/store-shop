package online.shop.store.services.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.shop.store.dto.entity.RoleName;
import online.shop.store.dto.entity.products.Bill;
import online.shop.store.dto.entity.user.User;
import online.shop.store.dto.entity.user.UserRegister;
import online.shop.store.repository.RoleRepository;
import online.shop.store.repository.UserRepository;
import online.shop.store.services.location.LocationService;
import online.shop.store.utils.time.TimeNow;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private LocationService locationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    private final TimeNow timeNow;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User save(UserRegister userRegister, User user) {
       
        user.setUserName(userRegister.getUserName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        user.setAvatar("url");
        user.setLock(false);
        user.setRole(roleRepository.findByRoleName(RoleName.USER));

        user.setBills(new ArrayList<Bill>());
        String locationToString= locationService.getLocationToString(userRegister.getIdWard());
        String location=locationToString+","+userRegister.getLocationDetail();
        user.setLocation(location);
        
        user.setCreatedAt(timeNow.getTimeNow());
        user.setUpdatedAt(timeNow.getTimeNow());

        User newUser=userRepository.save(user);

        return newUser;
    }
    
}
