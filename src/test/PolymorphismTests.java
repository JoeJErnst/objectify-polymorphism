import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.cmd.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PolymorphismTests {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    protected final Logger logger = Logger.getLogger(PolymorphismTests.class.getName());

    @Before
    public void setup() {
        helper.setUp();

        // Create a Car
        Car car = new Car();
        car.setLicensePlate("IMA-CAR");
        OfyService.ofy().save().entity(car).now();

        // Create a Motorcycle
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setLicensePlate("IMA-BIKE");
        OfyService.ofy().save().entity(motorcycle).now();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void getFourWheeledVehicle() {
        Vehicle vehicle = OfyService.ofy().load().type(Vehicle.class).filter("numWheels", 4).first().get();

        assertTrue(vehicle instanceof Car);
        assertEquals(vehicle.getLicensePlate(), "IMA-CAR");

        logger.info(vehicle.toJSON());
    }

    @Test
    public void getCar() {
        Car car = OfyService.ofy().load().type(Car.class).id("IMA-CAR").get();

        assertTrue(car instanceof Car);
        assertEquals(car.getLicensePlate(), "IMA-CAR");

        logger.info(car.toJSON());
    }

    @Test
    public void getFourCylinderVehicles() {
        Query<Vehicle> query = OfyService.ofy().load().type(Vehicle.class).filter("numCylinders", 4);

        // View the output and you'll see that both a Car and a Motorcycle came back in the Query
        for(Vehicle vehicle : query) {
            logger.info(vehicle.toJSON());
        }
    }

    @Test
    public void getFourDoorVehicles() {
        Query<Vehicle> query = OfyService.ofy().load().type(Vehicle.class).filter("numDoors", 4);

        // Since numDoors is only defined on Car, that's all we should get back in this query
        for(Vehicle vehicle : query) {
            assertTrue(vehicle instanceof Car);
        }
    }

    /**
     * Here's something that WON'T work unless you put @EntitySubclass(index=true) on the Car entity,
     * because Entity Subclasses are not indexed by default.  This can be confusing because numDoors had the @Index
     * annotation, but it won't be returned in this query because the underlying descriminator is NOT indexed
     * (unless you use @EntitySubclass(index=true)
     */
    @Test
    public void getFourDoorCars() {
        Query<Car> query = OfyService.ofy().load().type(Car.class).filter("numDoors", 4);

        for(Car car : query) {
            // You won't see this line logged, because the query won't return any results.
            logger.info(car.toJSON());
        }

    }
}
