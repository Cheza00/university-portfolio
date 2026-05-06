package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.PlayerModel;
import view.GamePanel;
import view.NewProfileDialog;

/**
 * Class to save and load the values in the DataStorage class
 * @see DataStorage
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class SaveLoad {
	GamePanel gamePanel = GamePanel.getInstance();
	
	/**
	 * Method to save the data
	 */
	public void save() {
		try {	
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			DataStorage ds = new DataStorage();
			
			ds.level = PlayerModel.getPlayerLevel();
			ds.nickname = NewProfileDialog.nickname;
			ds.profilePath = NewProfileDialog.selectedImagePath;
			
			oos.writeObject(ds);
		}
		catch(Exception e) {
			System.out.println("Save Exception");
		}
	}
	
	/**
	 * Method to load the stored data
	 */
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			DataStorage ds = (DataStorage)ois.readObject();
			
			PlayerModel.setPlayerLevel(ds.level);
			NewProfileDialog.nickname = ds.nickname;
			NewProfileDialog.selectedImagePath = ds.profilePath;
		}
		catch(Exception e) {
			System.out.println("Load Exception");
		}
	}
}
