package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.donation.DonationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public InstitutionService(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public List<InstitutionDto> findAll(){
        List<InstitutionDto> institutionDtoList = new ArrayList<>();
        for (Institution institutionEach : institutionRepository.findAll()){
            institutionDtoList.add(new InstitutionDto(institutionEach));
        }
        return institutionDtoList;
    }

    public InstitutionDto findById(Long id){
        return new InstitutionDto(institutionRepository.findById(id).get());
    }

    public void create (InstitutionDto institutionDto){
        Institution institution = new Institution();
        institution.setName(institutionDto.getName());
        institution.setDescription(institutionDto.getDescription());
        institutionRepository.save(institution);
    }

    public void update (InstitutionDto institutionDto){
        Institution institution = institutionRepository.findById(institutionDto.getId()).get();
        institution.setName(institutionDto.getName());
        institution.setDescription(institutionDto.getDescription());
        institutionRepository.save(institution);
    }

    public void delete (Long id){
        donationRepository.deleteAllByInstitution(institutionRepository.findById(id).get());
        institutionRepository.deleteById(id);
    }

    public long countInstitutions(){
        return institutionRepository.count();
    }
}
