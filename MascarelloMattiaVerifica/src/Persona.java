import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Persona {
	private String surname, name, fiscalCode;
	private GregorianCalendar birthDate;
	public Persona(String surname, String name, String fiscalCode, GregorianCalendar birthDate) throws TooOldException {
		if(birthDate.get(Calendar.YEAR)<1940) throw new TooOldException("Untente troppo anziano");
		this.surname = surname;
		this.name = name;
		this.fiscalCode = fiscalCode;
		this.birthDate = birthDate;
	}
	
	public static GregorianCalendar GregorianCalendarFromString(String ymd) {
		String[] spl = ymd.split("-");
		return new GregorianCalendar(Integer.parseInt(spl[0]),Integer.parseInt(spl[1]),Integer.parseInt(spl[2]));
	}
	public static String GregorianCalendarToString(GregorianCalendar gc) {
		return String.valueOf(gc.get(Calendar.YEAR))+"-"+String.valueOf(gc.get(Calendar.MONTH))+"-"+String.valueOf(gc.get(Calendar.DAY_OF_MONTH));
	}
	
	public String toCsv(String firstField) {
		return firstField+","+surname+","+name+","+fiscalCode+","+Persona.GregorianCalendarToString(birthDate);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}
}
