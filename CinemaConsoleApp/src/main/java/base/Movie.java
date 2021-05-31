package base;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_movie"
    )
    @SequenceGenerator(
            name = "seq_movie",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Override
    public String toString() {
        return "id=" + id +
                "| movieName=" + movieName +
                "| genre=" + this.genre.getGenreName();

    }
}
