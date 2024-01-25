// PaintManager.java
import java.util.HashMap;
import java.util.Map;

public class PaintManager {
    private final Map<Integer, Paint> paintOptions;

    public PaintManager() {
        paintOptions = new HashMap<>();
        initializePaintOptions();
    }

    private void initializePaintOptions() {
        int key = 1;
        // Color Red
        paintOptions.put(key++, new Paint("Red", 2,  12.00));
        //paintOptions.put(key++, new Paint("Red", 10,  48.00));
        //paintOptions.put(key++, new Paint("Red", 25,  96.00));
        // Color Blue
        paintOptions.put(key++, new Paint("Blue", 2,  10.00));
        //paintOptions.put(key++, new Paint("Blue", 10,  40.00));
        //paintOptions.put(key++, new Paint("Blue", 25,  90.00));
        // Color Yellow
        paintOptions.put(key++, new Paint("Yellow", 2,  10.00));
        //paintOptions.put(key++, new Paint("Yellow", 10,  40.00));
        //paintOptions.put(key++, new Paint("Yellow", 25,  90.00));
        // Color White
        paintOptions.put(key++, new Paint("White", 2,  8.00));
        //paintOptions.put(key++, new Paint("White", 10,  32.00));
        //paintOptions.put(key++, new Paint("White", 25,  64.00));
        // Color Black
        paintOptions.put(key++, new Paint("Black", 2,  15.00));
        //paintOptions.put(key++, new Paint("Black", 10,  60.00));
        //paintOptions.put(key++, new Paint("Black", 25,  120.00));
    }

    public Map<Integer, Paint> getPaintOptions() {
        return paintOptions;
    }
}
