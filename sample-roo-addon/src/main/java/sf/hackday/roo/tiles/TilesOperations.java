package sf.hackday.roo.tiles;

/**
 * Interface of commands that are available via the Roo shell.
 *
 * @since 1.1.1
 */
public interface TilesOperations {

    /**
     * Indicate of the add tiles command should be available
     * 
     * @return true if it should be available, otherwise false
     */
    boolean isAddTilesCommandAvailable();
    
    void addTiles();
    /**
     * @param propertyName to obtain (required)
     * @return a message that will ultimately be displayed on the shell
     */
    String getProperty(String propertyName);
}