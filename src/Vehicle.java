import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * This class is the root of our Objectify hierarchy (note the @Entity annotation).
 *
 * Our application may have many Objectify hierarchies that are completely unrelated (e.g. Vehicle, Fruit, Planet),
 * even though they share the same {@link Foundation} class as the root of their common Java object hierarchy.
 */

@Entity
public class Vehicle extends Foundation {
    @Id
    private String licensePlate;

    @Index
    int numWheels;

    @Index
    int numCylinders;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
