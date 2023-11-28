package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.service.DishInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/public/files")
@RequiredArgsConstructor
@Validated
public class FileController {

    private final DishInfoService dishInfoService;

    @GetMapping(value = "/image/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

        Resource file = dishInfoService.loadAsResource(filename);
        return ResponseEntity.ok(file);
//        if (file == null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
