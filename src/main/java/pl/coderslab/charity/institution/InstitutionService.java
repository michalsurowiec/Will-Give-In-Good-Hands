package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<InstitutionDto> findAll(){
        List<InstitutionDto> institutionDtoList = new ArrayList<>();
        for (Institution institutionEach : institutionRepository.findAll()){
            institutionDtoList.add(new InstitutionDto(institutionEach));
        }
        return institutionDtoList;
    }
}
