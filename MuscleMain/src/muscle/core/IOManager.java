package muscle.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class IOManager {

	private File save;
	private File items;
	private final String delim = ",";
	private ItemManager im;
	private ProfileManager pm;
	
	public IOManager(String saveLoc, String itemLoc) {
		save = new File(saveLoc);
		items = new File(itemLoc);
		this.im = new ItemManager();
		this.pm = new ProfileManager();
	}
	
	public void readItems() {
		if(items.exists() && !items.isDirectory() && items.canRead()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(items));
				String line;
				while ((line = br.readLine()) != null) {
					String[] content = line.split(delim);
					im.addMuscleAct(content[0], content[1]);
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void readProfile() {
		if(save.exists() && !save.isDirectory() && save.canRead()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(save));
				String line;
				while ((line = br.readLine()) != null) {
					String[] content = line.split(delim);
					DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
					Date d = df.parse(content[0]);
					pm.addLog(new Log(d, new Muscle(content[1])));
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateProfile(Muscle m) {
		if(save.exists() && !save.isDirectory() && save.canRead()) {
			try {
				String line;
				
				Date d = new Date();
				
				line = d.toString()+delim+m.toString();
				
				Writer out = new BufferedWriter(new FileWriter(save, true));
				out.append(line+"\n");
				
				out.close();
				//add this in GUIManager instead of here
				//this.pm.addLog(new Log(d, m));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}