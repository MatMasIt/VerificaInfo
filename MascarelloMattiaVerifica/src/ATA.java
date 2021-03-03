import java.util.GregorianCalendar;

public class ATA extends Persona{
	private InquadramentoATA inquadramento;
	public ATA(String surname, String name, String fiscalCode, GregorianCalendar birthDate,InquadramentoATA inquadramento) throws TooOldException {
		super(surname, name, fiscalCode, birthDate);
		this.inquadramento=inquadramento;
	}
	public static InquadramentoATA InquadramentoATAFromString(String ata) {
		InquadramentoATA iq=null;
		if(ata.equals("InquadramentoATA.SEGRETERIA")) {
			iq=InquadramentoATA.SEGRETERIA;
		}
		else if(ata.equals("ASSISTENTE_TECNICO")) {
			iq=InquadramentoATA.ASSISTENTE_TECNICO;
			
		}
		else if(ata.equals("OPERATORE")) {
			iq=InquadramentoATA.OPERATORE;
			
		}
		return iq;
	}
	public static String InquadramentoATAToString(InquadramentoATA IQ) {
		String s=null;
		if(IQ.equals(InquadramentoATA.SEGRETERIA)) {
			s="SEGRETERIA";
		}
		else if(IQ.equals(InquadramentoATA.ASSISTENTE_TECNICO)) {
			s="ASSISTENTE_TECNICO";
		}
		else if(IQ.equals(InquadramentoATA.OPERATORE)) {
			s="OPERATORE";
		}
		return s;
	}
	public String toCsv() {
		return super.toCsv("ATA")+","+ATA.InquadramentoATAToString(inquadramento);
	}
	public InquadramentoATA getInquadramento() {
		return inquadramento;
	}
	public void setInquadramento(InquadramentoATA inquadramento) {
		this.inquadramento = inquadramento;
	}
	
	
}
