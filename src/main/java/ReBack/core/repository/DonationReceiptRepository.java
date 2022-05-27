package ReBack.core.repository;

import ReBack.core.data.DonationReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationReceiptRepository extends JpaRepository<DonationReceipt, Long> {
}