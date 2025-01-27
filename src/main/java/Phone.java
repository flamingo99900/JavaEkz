import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "screen_size")
    private double screenSize;

    public Phone() {
    }

    public Phone(String manufacturer, String model, int releaseYear, double screenSize) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Производитель: %-10s | Модель: %-10s | Год выпуска: %d | Диагональ экрана: %.1f\"",
                id, manufacturer, model, releaseYear, screenSize
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}
