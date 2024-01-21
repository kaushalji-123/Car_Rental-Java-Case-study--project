
package Exception;

@SuppressWarnings("serial")
public class CarNotFoundException extends Exception {
    public CarNotFoundException(String message) {
        super(message);
    }
}