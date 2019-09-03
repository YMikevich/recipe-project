package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;
import by.mikevich.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import by.mikevich.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Unit of measure service.
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    /**
     * Instantiates a new Unit of measure service.
     *
     * @param unitOfMeasureRepository             the unit of measure repository
     * @param unitOfMeasureToUnitOfMeasureCommand the unit of measure to unit of measure command
     */
    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return unitOfMeasureRepository.findAll().stream()
                .map(unitOfMeasure -> unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure))
                .collect(Collectors.toSet());
    }
}
