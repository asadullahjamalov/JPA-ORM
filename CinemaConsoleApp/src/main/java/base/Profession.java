package base;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "professions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profession {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_profession"
    )
    @SequenceGenerator(
            name = "seq_profession",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(name = "profession_name")
    private String professionName;


    @OneToMany(mappedBy = "profession")
    private List<Person> personList;


    @Override
    public String toString() {
        return "id=" + id +
                "| professionName=" + professionName;

    }
}
