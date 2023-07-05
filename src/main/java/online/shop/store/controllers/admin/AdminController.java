package online.shop.store.controllers.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.shop.store.dto.RegisterAdmin;
import online.shop.store.models.Admin;
import online.shop.store.repository.AdminRepository;
import online.shop.store.services.admin.AdminService;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/login")
    public String loginAdmin(){
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdmin registerAdmin){
        if(adminService.existAdminByEmail(registerAdmin.getEmail())){
            return new ResponseEntity<String>("email đã tồn tại", HttpStatus.CONFLICT);
        }
        Admin admin=adminService.save(registerAdmin,new Admin());
        if(admin!=null){
            return new ResponseEntity<Admin>(admin,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("exception when create Admin", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> allAdmin(@RequestParam int page,@RequestParam int total){
        Page<Admin> pageAdmins=adminService.allAdmin(page, total);
        if(page>pageAdmins.getTotalPages()-1){
            return new ResponseEntity<String>("not found",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Admin>>(pageAdmins, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Integer id) throws NotFoundException{
        Optional<Admin> admin =adminRepository.findById(id);
        if(admin.isEmpty()){
            return new ResponseEntity<Admin>(admin.orElse(null), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAdminById(@PathVariable("id") Integer id,@RequestBody RegisterAdmin registerAdmin){
        Optional<Admin> admin=adminRepository.findById(id);
        if(admin.isEmpty()){
            return new ResponseEntity<String>("not found",HttpStatus.NOT_FOUND);
        }else{
            Admin adminById=admin.orElse(null);
            Admin a=adminService.save(registerAdmin,adminById);
            return new ResponseEntity<Admin>(a, HttpStatus.CREATED);
        }            
    }
}
