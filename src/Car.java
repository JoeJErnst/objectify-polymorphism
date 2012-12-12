import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;

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
