package by.mikevich.recipeproject.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Image service.
 */
public interface ImageService {
    /**
     * Save image file.
     *
     * @param id   the id
     * @param file the file
     */
    void saveImageFile(Long id, MultipartFile file);
}
