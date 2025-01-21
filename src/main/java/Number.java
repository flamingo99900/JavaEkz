import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
}
