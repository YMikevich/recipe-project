package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private RecipeRepository recipeRepository;

    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file){
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

            if(!recipeOptional.isPresent()) {
                //todo error handling
            }

            Recipe recipe = recipeOptional.get();
            Byte[] image = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                image[i++] = b;
            }

            recipe.setImage(image);
            recipeRepository.save(recipe);
        }
        catch (IOException ex) {
            log.error("i/o error while saving the image");
            //todo better handling
            ex.printStackTrace();
        }
    }
}
