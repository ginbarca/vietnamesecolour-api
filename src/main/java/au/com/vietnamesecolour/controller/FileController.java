package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.service.FileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/public/files")
@RequiredArgsConstructor
@Validated
public class FileController {

    private final FileService fileService;

    @GetMapping(value = "/image/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<Resource> serveFile(
            @Valid @NotBlank(message = "File name must not be blank") @PathVariable String filename
    ) throws IOException {

        Resource file = fileService.loadAsResource(filename);
        if (file == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(file);
    }
}
