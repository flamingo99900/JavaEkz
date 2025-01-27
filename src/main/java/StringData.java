import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class StringData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String string1;
    private String string2;
    private String result;
}
