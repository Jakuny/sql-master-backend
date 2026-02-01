package com.jakunya.sqlmaster.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FileStorageService {
    private final String uploadPath = System.getProperty("user.dir") + "/uploads";

    public String saveAvatar(MultipartFile file) throws IOException {;

        var readers = ImageIO.getImageReaders(ImageIO.createImageInputStream(file.getInputStream()));
        if (!readers.hasNext()) {
            throw new RuntimeException("Not image");
        }
        ImageReader reader = readers.next();

        String formatName = reader.getFormatName();

        File uploadDir = new File(uploadPath + "/avatars");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (!formatName.equals("png")) {
            throw new RuntimeException("The file or its name must not be empty or not png.");
        } else {
            String newFileName = UUID.randomUUID().toString() + "." + formatName;
            String fullPath = uploadDir.getAbsolutePath() + "/" + newFileName;
            File destinationFile = new File(fullPath);

            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            if (originalImage == null) {
                throw new RuntimeException("Invalid image data");
            }
            ImageIO.write(originalImage, "png", destinationFile);
            return "/uploads/avatars/" + newFileName;
        }

    }
}
