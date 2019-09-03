package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Exception handler controller.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * Not found exception handler model and view.
     *
     * @param exception the exception
     * @return the model and view
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundExceptionHandler(Exception exception){
        log.error("handling not found error");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    /**
     * Number format exception model and view.
     *
     * @param exception the exception
     * @return the model and view
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatException(Exception exception) {
        log.error("handling number format exception");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("400Error");

        return modelAndView;
    }
}
