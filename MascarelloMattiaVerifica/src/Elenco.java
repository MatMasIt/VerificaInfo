import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Elenco extends ArrayList<Persona>{
	
	
	public Elenco() {
		super();
	}
	
	public void load(File f,FileMode fileMode) throws ClassNotFoundException, IOException, TooOldException {
		if(fileMode.equals(FileMode.OBJECT)) {
			FileInputStream fis= new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Elenco e = (Elenco) ois.readObject();
			this.clear();
			for(int i=0;i<e.size();i++) {
				this.add(e.get(i));
			}
			ois.close();
			fis.close();
		}
		else {
			FileReader fr= new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				String[] csvSplit = line.split(",");
				if(csvSplit[0].equals("ATA")) {
					this.add(new ATA(csvSplit[1], csvSplit[2], csvSplit[3],Persona.GregorianCalendarFromString(csvSplit[4]), ATA.InquadramentoATAFromString(csvSplit[5])));
				}
				else if(csvSplit[0].equals("Docente")) {
					this.add(new Docente(csvSplit[1], csvSplit[2], csvSplit[3],Persona.GregorianCalendarFromString(csvSplit[4]), csvSplit[5]));
					
				}
				else if(csvSplit[0].equals("Alunno")) {
					this.add(new Alunno(csvSplit[1], csvSplit[2], csvSplit[3],Persona.GregorianCalendarFromString(csvSplit[4]), Integer.parseInt(csvSplit[5]),csvSplit[6]));
					
				}
			}
			br.close();
			fr.close();
		}
	}
	public void write(File f, FileMode fileMode) throws IOException {
		if(fileMode.equals(FileMode.OBJECT)) {
			FileOutputStream fos= new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
		}
		else {
			FileWriter fw= new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<this.size();i++) {
				if(this.get(i) instanceof Docente) {
					Docente d = (Docente) this.get(i);
					bw.write(d.toCsv());
				}
				else if(this.get(i) instanceof Alunno) {
					Alunno a = (Alunno) this.get(i);
					bw.write(a.toCsv());
				}
				if(this.get(i) instanceof ATA) {
					ATA ata = (ATA) this.get(i);
					bw.write(ata.toCsv());
				}
			}
			bw.close();
			fw.close();
		}
		
	}

}
