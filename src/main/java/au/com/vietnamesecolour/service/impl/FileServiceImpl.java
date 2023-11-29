package au.com.vietnamesecolour.service.impl;

import au.com.vietnamesecolour.service.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final String uploadDir;
    private Path rootLocation;

    public FileServiceImpl(@Value("${application.upload-dir}") String uploadDir) {
        this.uploadDir = uploadDir;
        this.rootLocation = Paths.get(uploadDir);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
