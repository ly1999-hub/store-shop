package online.shop.store.services.admin;

import org.springframework.data.domain.Page;

import online.shop.store.dto.RegisterAdmin;
import online.shop.store.models.Admin;

public interface IAdminService {
    Admin save(RegisterAdmin registerAdmin,Admin admin);
    Page<Admin> allAdmin(int page,int total);
    Boolean existAdminByEmail(String email);
}
