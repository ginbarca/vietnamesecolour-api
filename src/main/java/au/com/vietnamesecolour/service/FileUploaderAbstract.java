package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public abstract class FileUploaderAbstract<T, S> {

    protected final String uploadDir;
    protected final Path rootLocation;

    protected FileUploaderAbstract(String uploadDir, Path rootLocation) {
        this.uploadDir = uploadDir;
        this.rootLocation = rootLocation;
    }

    public ResponseData<T> uploadHandler(T dto, S entity, MultipartFile file) {
        ResponseData<T> responseData;
        if (uploadDir.trim().length() == 0) {
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "File upload location can not be empty");
            return responseData;
        }
        try {
            if (file.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Failed to store empty file");
                return responseData;
            }
            Path destinationFile = this.rootLocation
                    .resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename()))
                    )
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Cannot store file outside current directory");
                return responseData;
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                // TODO: implement process method
                responseData = process(dto, entity, file);
            }
        } catch (IOException e) {
            responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "Failed to store file");
            return responseData;
        }
        return responseData;
    }

    public abstract ResponseData<T> process(T dto, S entity, MultipartFile file);
}
