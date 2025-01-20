import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
}
