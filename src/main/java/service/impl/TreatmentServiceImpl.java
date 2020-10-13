package service.impl;

import dao.Page;
import dao.TreatmentDao;
import dao.UserDao;
import dto.TreatmentDto;
import entity.Treatment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TreatmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreatmentServiceImpl implements TreatmentService {
    private static final Logger LOGGER = LogManager.getLogger(TreatmentServiceImpl.class);

    private final TreatmentDao treatmentDao;
    private final UserDao userDao;
    private static final Integer TREATMENTS_PER_PAGE = 5;

    public TreatmentServiceImpl(TreatmentDao treatmentDao, UserDao userDao) {
        this.treatmentDao = treatmentDao;
        this.userDao = userDao;
    }

    @Override
    public Optional<Treatment> findByName(String treatmentName) {
        return Optional.empty();
    }

    @Override
    public Page<TreatmentDto> findAllWithSpecialistsAndRates(String pageNumber) {
        Map<String, Double> rates = userDao.findAllSpecialistsWithRates();
        int validPageNum = validatePageNumber(pageNumber);
        List<TreatmentDto> allTreatments = treatmentDao.findAllWithSpecialists();
        int totalItems = allTreatments.size();
        int totalPages = totalItems/TREATMENTS_PER_PAGE + 1;

        List<TreatmentDto> dtos = treatmentDao.
                findAllWithSpecialistsPaged(TREATMENTS_PER_PAGE, validPageNum*TREATMENTS_PER_PAGE).stream()
                .map(e -> new TreatmentDto.TreatmentDtoBuilder(e)
                        .setSpecialistName(e.getSpecialistName())
                        .setRate(rates.get(e.getSpecialistName()))
                        .build())
                .collect(Collectors.toList());

        return new Page<>(dtos, validPageNum, TREATMENTS_PER_PAGE, totalItems, totalPages);
    }

    private int validatePageNumber(String page) {
        int pageNumber = 0;
        if (page == null) {
            return pageNumber;
        }
        try {
            pageNumber = Integer.parseInt(page) - 1;
        } catch (NumberFormatException e) {
            String message = String.format("Could not parse page number from provided string: %s", page);
            LOGGER.warn(message, e);
        }
        return pageNumber;
    }

}
