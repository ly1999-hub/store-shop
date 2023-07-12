package online.shop.store.controllers.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import online.shop.store.dto.entity.admin.Admin;
import online.shop.store.dto.entity.admin.DtoLoginAdmin;
import online.shop.store.dto.entity.admin.RegisterAdmin;
import online.shop.store.repository.AdminRepository;
import online.shop.store.services.admin.AdminService;
import online.shop.store.utils.file.UserExcelExporter;

@RestController
@RequestMapping(value = "/api/v1/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    // GetLogin ...
    @GetMapping("/no-authen/login")
    public String loginAdmin(){
        return "login";
    }

    // AllAdmin by Page
    @GetMapping("/authen/all")
    public ResponseEntity<?> allAdmin(@RequestParam int page,@RequestParam int total){
        Page<Admin> pageAdmins=adminService.allAdmin(page, total);
        if(page>pageAdmins.getTotalPages()-1){
            return new ResponseEntity<String>("not found",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Admin>>(pageAdmins, HttpStatus.OK);
    }
    
    // GetAdminById ..
    @GetMapping(value = "/authen/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") Integer id) throws NotFoundException{
        Optional<Admin> admin =adminRepository.findById(id);
        if(admin.isEmpty()){
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Admin>(admin.orElse(null), HttpStatus.OK);
           
        }
    }

    // RegisterAdmin ..
    @PostMapping("/no-authen/register")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid RegisterAdmin registerAdmin){
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

    // UpdateAdminById ...
    @PostMapping("/authen/update/{id}")
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

    // DeleteAdminById ...
    @RequestMapping(value="/authen/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAdminById(@PathVariable("id") Integer id){
        Optional<Admin> admin=adminRepository.findById(id);
        if(admin.isEmpty()){
            return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
        }else{
            adminRepository.deleteById(id);
            return new ResponseEntity<Integer>(id,HttpStatus.NO_CONTENT);
        }            
    }

    // lockAndUnlock
    @PostMapping("/authen/lock-unlock/{id}")
    public  ResponseEntity<?> lockAndUnlock(@PathVariable("id") Integer id){
        Optional<Admin> admin =adminRepository.findById(id);
        if(admin.isEmpty()){
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }else{
            Admin admin2=admin.orElse(null);
            admin2.setLock(!admin2.getLock());
            System.out.println(admin2.getRoles());
            return new ResponseEntity<Admin>(adminRepository.save(admin2), HttpStatus.OK);
        }
    }

    @PostMapping("/no-authen/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestParam("email") String email){
        Admin admin=adminService.forgetPassword(email);
        return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/no-authen/login")
    public String login(@RequestBody DtoLoginAdmin dtoLoginAdmin){
        String token =adminService.loginAdmin(dtoLoginAdmin);
        return token;
    }

    @GetMapping("/authen/search")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name){
        List<Admin> admins=adminService.findAdminByAdminName(name);
        return new ResponseEntity<List<Admin>>(admins,HttpStatus.OK);
    }

    @GetMapping("/no-authen/export-excel")
    public void exportExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Admin> listUsers = adminRepository.findAll();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);  
        
    }
}
