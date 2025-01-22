import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int threadNum;
    private String message;
    private long time;
}
