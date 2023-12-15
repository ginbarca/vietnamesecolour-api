package au.com.vietnamesecolour.repos;

import au.com.vietnamesecolour.entity.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("SELECT iv FROM Invoice iv WHERE LOWER(iv.invoiceCode) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<Invoice> findByInvoiceCode(String invoiceCode, Pageable pageable);

}
