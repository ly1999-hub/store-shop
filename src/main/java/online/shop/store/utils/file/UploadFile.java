package online.shop.store.utils.file;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
    public String uploadFile(MultipartFile file, String path)throws FileNotFoundException{
        try {
            if(file.isEmpty()){
           new FileNotFoundException("chua chon file");
        }
        byte[] fileBytes = file.getBytes();
        File newfile=new File(path+file.getOriginalFilename());
        FileUtils.writeByteArrayToFile(newfile, fileBytes);
        return newfile.getPath().substring(25);

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
