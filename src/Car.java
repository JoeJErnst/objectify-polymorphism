import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

/**
 * Just because numDoors in Indexed doesn't mean you can query for Car entities by numDoors.  You CAN query for
 * {@link Vehicle} entities by numDoors, however.
 *
 * By changing the annotation to @EntitySubclass(index=true), then you could query for Cars by numDoors.
 * See the test.PolymorphismTests for examples.
 */
@EntitySubclass
public class Car extends Vehicle {

    @Index
    private int numDoors;

    public Car() {
        numWheels = 4;
        numCylinders = 4;
        numDoors = 4;
    }
}
