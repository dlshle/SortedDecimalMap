package cs143;

/**
 * This interface provides a means of identifying objects that contain
 * a non-negative whole-number sorting key.
 * 
 * @author Linda Zuvich
 */
public interface DecimalSortable {

    /**
     * Provides access to the non-negative integer field that acts as the 
     * sorting key.
     * 
     * @return the value of the key 
     */
    int getKey();
    
}
