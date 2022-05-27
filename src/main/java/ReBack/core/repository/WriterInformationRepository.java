package ReBack.core.repository;

import ReBack.core.data.WriterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterInformationRepository extends JpaRepository<WriterInformation, Long> {
//    void findByWriterLecturePlaceLikeAndAvailableStartTim
}
