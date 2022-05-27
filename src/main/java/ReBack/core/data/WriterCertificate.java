package ReBack.core.data;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriterCertificate {
    @SequenceGenerator(name = "writer_certificate_seq_generator",
            sequenceName = "writer_certificate_seq",
            initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "writer_certificate_seq")
    private Long writerCertificateCode;

    @Column(length=50, nullable = false)
    private String writerOwnCertificateName;

    @Column(nullable = false)
    private String writerCertificateImgPath;

    @Column
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name="writer_information_code")
    private WriterInformation writerInformation;

}
