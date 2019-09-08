package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.service.ImageService;
import by.mikevich.recipeproject.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Image controller.
 */
@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    /**
     * Instantiates a new Image controller.
     *
     * @param imageService  the image service
     * @param recipeService the recipe service
     */
    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    /**
     * Show upload form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));

        return "recipe/image-upload-form";
    }

    /**
     * Handle image post string.
     *
     * @param id   the id
     * @param file the file
     * @return the string
     */
    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(id, file);

        return "redirect:/recipe/update/" + id;
    }

    /**
     * Render image from db.
     *
     * @param id       the id
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id));

        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
