import com.googlecode.objectify.annotation.EntitySubclass;

@EntitySubclass
public class Motorcycle extends Vehicle {

    public Motorcycle() {
        numWheels = 2;
        numCylinders = 4;
    }
}
