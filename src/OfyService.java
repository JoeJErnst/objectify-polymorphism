import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
    static {
        // Register all of our Entities here.
        factory().register(Vehicle.class);
        factory().register(Car.class);
        factory().register(Motorcycle.class);

        // Notice that we didn't register the Foundation class because it is not part of the Objectify hierarchy.
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
