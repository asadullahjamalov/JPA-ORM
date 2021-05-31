package base;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_genre"
    )
    @SequenceGenerator(
            name = "seq_genre",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(name = "genre_name")
    private String genreName;

    @OneToMany(mappedBy = "genre")
    private List<Movie> movieList;

    @Override
    public String toString() {
        return "id=" + id +
                "| genreName=" + genreName;

    }
}

