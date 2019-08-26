package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.CategoryCommand;
import by.mikevich.recipeproject.model.Category;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Category to category command.
 */
@Component
@Slf4j
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        log.debug("Converting CategoryCommand to Category");

        if (category == null)
            return null;

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
