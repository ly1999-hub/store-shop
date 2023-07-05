package online.shop.store;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import online.shop.store.repository.location.ProvinceRepository;

// import online.shop.store.models.Role;
// import online.shop.store.models.RoleName;
// import online.shop.store.models.location.Distric;
// import online.shop.store.models.location.Province;
// import online.shop.store.models.location.Ward;
// import online.shop.store.repository.RoleRepository;
// import online.shop.store.repository.location.DistrictRepository;
// import online.shop.store.repository.location.ProvinceRepository;
// import online.shop.store.repository.location.WardRepository;

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

	// 		Province province=provinceRepository.save(new Province("Da Nang"));
	// 		Distric distric=districtRepository.save(new Distric("Lien Chieu", province));
	// 		Ward ward =wardRepository.save(new Ward("Hoa Khanh Nam", distric));
	// 	};
	// }
	
}
