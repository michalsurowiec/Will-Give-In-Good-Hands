package pl.coderslab.charity.donation;

import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationService {

    private DonationRepository donationRepository;
    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
    }

    public int totalQuantity(){
        try {
            return donationRepository.sumQuantity();
        } catch (AopInvocationException AIE){
            return 0;
        }
    }

    public Long countDonations() {
        return donationRepository.count();
    }

    public List<DonationDto> findDonationsByUserId(Long id){
        return donationRepository.findAllByUserEquals(userRepository.findById(id).get()).stream()
                .map(DonationDto::new).collect(Collectors.toList());
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
        donation.setUser(userRepository.findById(donationDto.getUser()).get());
        donation.setStatus(donationDto.getStatus());
        donation.setCreationDate(donationDto.getCreationDate());
        donationRepository.save(donation);
    }

}
