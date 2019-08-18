package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.institution.Institution;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM donation")
    int sumQuantity();

    void deleteAllByInstitution(Institution institution);

}
