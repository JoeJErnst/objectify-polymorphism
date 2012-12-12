import com.google.appengine.labs.repackaged.org.json.JSONObject;

/**
 * This class is the root of our Java object hierarchy, but not the root of our Objectify hierarchy.
 *
 * This would contain things that are common to ALL entities in our application; things like a toJSON() method, for example.
 */
public class Foundation {
    /**
     * @return A JSON representation of this entity.
     */
    public String toJSON() {
        return new JSONObject(this).toString();
    }
}
