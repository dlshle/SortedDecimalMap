package cs143;

/**
 * This domain class represents a single product stored in a warehouse.
 * 
 * @author Linda Zuvich
 */
public class Product implements DecimalSortable {
    
    /**
     * product IDs are no longer than 8 digits long
     */
    public static final int ID_LENGTH = 8; 
    
    //private fields
    private int productId;
    private String productName;
    private int isle;
    private int shelf;
    
    /**
     * Simple constructor. 
     * 
     * @param productId the unique ID of the project 
     */
    public Product(int productId) {
        this.productId = productId;
    }

    /**
     * Full constructor.
     * 
     * @param productId the unique ID of the product
     * @param productName the product name
     * @param isle the isle in the warehouse where this product is stored
     * @param shelf the shelf on which this item is stored
     */
    public Product(int productId, String productName, int isle, int shelf) {
        this.productId = productId;
        this.productName = productName;
        this.isle = isle;
        this.shelf = shelf;
    }

    /**
     * Provides access to the unique ID of this product.
     * 
     * @return the unique ID of this product 
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Allows the ID of this product to be set.
     * 
     * @param productId the unique ID of this product 
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Provides access to the product name.
     * 
     * @return the product name 
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Allows the product name to be set.
     * 
     * @param productName the product name 
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Provides access to the isle in the warehouse where this product is stored.
     * 
     * @return the isle number
     */
    public int getIsle() {
        return isle;
    }

    /**
     * Allows the isle where this product is stored to be set.
     * 
     * @param isle the isle number
     */
    public void setIsle(int isle) {
        this.isle = isle;
    }

    /**
     * Provides access to the shelf on which this item is stored
     * 
     * @return the shelf number
     */
    public int getShelf() {
        return shelf;
    }

    /**
     * Allows the shelf on which this item is stored to be set
     * 
     * @param shelf the shelf number 
     */
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    /**
     * Provides access to the non-negative whole-number sorting key for
     * this object. Implements the abstract method from the DecimalSortable
     * interface.
     * 
     * @return the sorting key
     */
    @Override
    public int getKey() {
        return productId;
    }
    
    /**
     * Provides a default string representation of this Product object.
     * 
     * @return the string representation 
     */
    @Override
    public String toString() {
        return String.valueOf(productId);
    }

    /**
     * Provides a unique hash code for this Product object.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.productId;
        return hash;
    }

    /**
     * Provides a means of determining if another Product object is 
     * equivalent to this one, based on their product IDs.
     * 
     * @param obj the other object
     * @return true if they have the same product IDs, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }
    
}
