package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.CategoryCommand;
import by.mikevich.recipeproject.model.Category;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Category command to category.
 */
@Component
@Slf4j
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        log.debug("Converting Category to CategoryCommand");

        if (categoryCommand == null)
            return null;

        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());

        return category;
    }
}
