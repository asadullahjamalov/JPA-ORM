package base;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "people_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_person_detail"
    )
    @SequenceGenerator(
            name = "seq_person_detail",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(name = "person_email")
    private String personEmail;

    @Column(name = "person_phone_number")
    private String personPhoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "personDetail")
    private Person person;


}

