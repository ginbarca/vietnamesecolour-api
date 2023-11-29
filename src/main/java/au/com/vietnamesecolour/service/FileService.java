package au.com.vietnamesecolour.service;

import org.springframework.core.io.Resource;

public interface FileService {

    Resource loadAsResource(String filename);
}
