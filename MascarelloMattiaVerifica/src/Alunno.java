import java.util.GregorianCalendar;

public class Alunno extends Persona{
	private int classe;
	private String sezione;
	public Alunno(String surname, String name, String fiscalCode, GregorianCalendar birthDate,int classe, String sezione) throws TooOldException {
		super(surname, name, fiscalCode, birthDate);
		this.classe=classe;
		this.sezione=sezione;
	}
	public String toCsv() {
		return super.toCsv("Alunno")+","+String.valueOf(classe)+","+sezione;
	}
	public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
}
