package pl.coderslab.charity.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository <Institution, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM donation")
    int sumQuantity();

}
