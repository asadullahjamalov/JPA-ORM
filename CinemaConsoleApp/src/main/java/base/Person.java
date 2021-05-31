package base;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_person"
    )
    @SequenceGenerator(
            name = "seq_person",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_surname")
    private String personSurname;

    @Column(name = "person_father_name")
    private String personFatherName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_detail_id", referencedColumnName = "id")
    private PersonDetail personDetail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @Override
    public String toString() {
        return "id=" + id +
                "| personName=" + personName +
                "| personSurname=" + personSurname +
                "| personFatherName=" + personFatherName +
                "| personEmail=" + personDetail.getPersonEmail()+
                "| personPhoneNumber=" + personDetail.getPersonPhoneNumber()+
                "| profession=" + profession.getProfessionName();
    }
}
