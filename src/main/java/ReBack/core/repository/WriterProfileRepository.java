package ReBack.core.repository;

import ReBack.core.data.WriterInformation;
import ReBack.core.dto.ConsultingMatchingDTO;
import ReBack.core.dto.WriterProfileDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.maven.doxia.logging.Log;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static ReBack.core.data.QWriterInformation.writerInformation;

@Repository
@RequiredArgsConstructor
public class WriterProfileRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<WriterInformation> getWriterProfile(String place, LocalDateTime start, LocalDateTime end) {

        return jpaQueryFactory
                .select(writerInformation)
                .from(writerInformation)
                .where(
                        writerInformation.availableStartTime.before(start),
                        writerInformation.availableFinishTime.after(end),
                        writerInformation.writerLecturePlace.contains(place)
                )
                .fetch();
    }
}
