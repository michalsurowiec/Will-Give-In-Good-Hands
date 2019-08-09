package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

@Service
@Transactional
public class DonationService {

    private DonationRepository donationRepository;
    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;

    public DonationService(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    public int totalQuantity(){
        return donationRepository.sumQuantity();
    }

    public void saveDonation(DonationDto donationDto){
        Donation donation = new Donation();
        donation.setQuantity(donationDto.getQuantity());
        donation.setCategories(categoryRepository.findAllById(donationDto.getCategoriesId()));
        donation.setInstitution(institutionRepository.findById(donationDto.getInstitution()).get());
        donation.setStreet(donationDto.getStreet());
        donation.setCity(donationDto.getCity());
        donation.setZipCode(donationDto.getZipCode());
        donation.setPickUpDate(donationDto.getPickUpDate());
        donation.setPickUpTime(donationDto.getPickUpTime());
        donation.setPickUpComment(donationDto.getPickUpComment());
        donationRepository.save(donation);
    }

}
