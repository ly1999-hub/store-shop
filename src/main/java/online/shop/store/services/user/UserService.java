package online.shop.store.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.shop.store.dto.entity.RoleName;
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
    private RoleRepository roleRepository;
    
    private final TimeNow timeNow;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User save(UserRegister userRegister, User user) {
       
        user.setUserName(userRegister.getUserName());
        user.setEmail(userRegister.getEmail());
        user.setPhone(userRegister.getPhone());
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        user.setAvatar("url");
        user.setLock(false);
        user.setRole(roleRepository.findByRoleName(RoleName.USER));

        String locationToString= locationService.getLocationToString(userRegister.getIdWard());
        String location=userRegister.getLocationDetail()+","+locationToString;
        user.setLocation(location);
        
        user.setCreatedAt(timeNow.getTimeNow());
        user.setUpdatedAt(timeNow.getTimeNow());

        User newUser=userRepository.save(user);

        return newUser;
    }
    
    @Override
    public Boolean checkExistByEmailOrPhone(String email,String phone){
        List<User> users=userRepository.findByEmailOrPhone(email, phone);
        if(users.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Page<User> allUsers(int page,int total){
        PageRequest pageDetail=PageRequest.of(page,total,Sort.by("userName"));
        Page<User> pageUser= userRepository.findAll(pageDetail);
        return pageUser;
    }
}
