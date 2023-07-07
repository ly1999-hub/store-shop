package online.shop.store.services.admin;

import org.springframework.data.domain.Page;

import online.shop.store.dto.entity.admin.Admin;
import online.shop.store.dto.entity.admin.RegisterAdmin;

public interface IAdminService {
    Admin save(RegisterAdmin registerAdmin,Admin admin);
    Page<Admin> allAdmin(int page,int total);
    Boolean existAdminByEmail(String email);
    Admin forgetPassword(String email);
}
