package com.scm.services.Implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;

    private Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public String uploadImage(MultipartFile profileImage) {
        if (profileImage.isEmpty()) {
            throw new RuntimeException("Empty file");
        }

        try{
            String filename = UUID.randomUUID().toString();

            byte[] data = profileImage.getBytes();

//            profileImage.getInputStream().read(data);
             cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id",filename));
//            System.out.println(filename);
//            logger.info(filename);
//            String newFileName = (String)result.get("public_id");
//            logger.info(newFileName);
            return this.getUrlFromPublicId(filename);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url()
                .transformation(
                        new Transformation<>()
                                .width(500)
                                .height(500)
                                .crop("fill"))

                .publicId(publicId)
                .generate();
    }
}
