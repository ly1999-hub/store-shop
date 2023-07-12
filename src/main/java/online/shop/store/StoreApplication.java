package online.shop.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import online.shop.store.repository.location.ProvinceRepository;
import online.shop.store.dto.entity.Role;
import online.shop.store.dto.entity.RoleName;
import online.shop.store.dto.entity.location.Distric;
import online.shop.store.dto.entity.location.Province;
import online.shop.store.dto.entity.location.Ward;
import online.shop.store.repository.RoleRepository;
import online.shop.store.repository.location.DistrictRepository;
import online.shop.store.repository.location.WardRepository;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(RoleRepository roleRepository,ProvinceRepository provinceRepository,DistrictRepository districtRepository,WardRepository wardRepository){
	// 	return args->{
	// 		roleRepository.save(new Role(RoleName.USER));
	// 		roleRepository.save(new Role(RoleName.ADMIN));
	// 		roleRepository.save(new Role(RoleName.ORDER));
	// 		roleRepository.save(new Role(RoleName.LOCATION));

	// 		Province province=provinceRepository.save(new Province("Nghệ An"));
	// 		Distric distric=districtRepository.save(new Distric("Đô Lương", province));
	// 		Ward ward =wardRepository.save(new Ward("Quang Sơn", distric));
	// 		Ward ward1 =wardRepository.save(new Ward("Thái Sơn", distric));
	// 		Ward ward2 =wardRepository.save(new Ward("Thượng Sơn", distric));
	// 		Ward ward3 =wardRepository.save(new Ward("Minh Sơn", distric));
	// 		Ward ward4 =wardRepository.save(new Ward("Tân Sơn", distric));
	// 		Ward ward5 =wardRepository.save(new Ward("Hòa Sơn", distric));
	// 		Ward ward6 =wardRepository.save(new Ward("Đại Sơn", distric));
	// 		System.out.print(ward.toString()+" "+ward1.toString()+ " "+ward2.toString()+" "+ward3.toString()+" "+ward4.toString()+" "+ward5.toString()+" "+ward6.toString()+" ");
	// 	};
	// }
	
}
