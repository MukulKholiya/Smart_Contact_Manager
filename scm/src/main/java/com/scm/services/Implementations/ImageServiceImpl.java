package com.scm.services.Implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.scm.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile profileImage) {

        String filename = UUID.randomUUID().toString();

        try{
            byte[] data = new byte[profileImage.getInputStream().available()];
            profileImage.getInputStream().read(data);
             cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_Id",filename
            ));
            return this.getUrlFromPublicId(filename);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }



    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url().publicId(publicId).generate();
    }
}
