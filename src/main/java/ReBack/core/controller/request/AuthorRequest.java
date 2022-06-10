package ReBack.core.controller.request;


import ReBack.core.data.Member;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AuthorRequest {

    private Member memberCode;
    private Long writerInformationCode;
    private String writerLecturePlace;
    private String availableStartTime;
    private String availableFinishTime;
    private String availableDay;

}
