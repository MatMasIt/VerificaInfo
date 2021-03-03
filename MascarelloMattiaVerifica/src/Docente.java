import java.util.GregorianCalendar;

public class Docente extends Persona{
	private String concorsoClass;
	public Docente(String surname, String name, String fiscalCode, GregorianCalendar birthDate, String concorsoClass) throws TooOldException {
		super(surname, name, fiscalCode, birthDate);
		this.concorsoClass=concorsoClass;
	}
	public String toCsv() {
		return super.toCsv("Docente")+","+concorsoClass;
	}
	public String getConcorsoClass() {
		return concorsoClass;
	}
	public void setConcorsoClass(String concorsoClass) {
		this.concorsoClass = concorsoClass;
	}
}
