package model;

/**
 * This class contains useful arrays to keep track 
 * of the objects to manage. It uses the Singleton Pattern
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class ModelAssets {
	
	private static ModelAssets model;
	
	// I let them be public to let the controller use them more comfortably
	public SuperObject obj[];
	public Monster monster[];
	
	/**
	 * Public method to return the single instance of the class 
	 * or, if not present, to create it with the private constructor
	 * before returning it
	 * @return		the instance of the class
	 */
	public static ModelAssets getInstance() {
		if (model == null) model = new ModelAssets();
		return model;
	}
	
	// Private constructor
	private ModelAssets() {
		obj = new SuperObject[20]; 
		monster = new Monster[20];
	}
}
