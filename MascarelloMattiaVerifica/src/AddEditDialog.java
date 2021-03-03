import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddEditDialog extends JDialog implements ActionListener{
	private Finestra parentFrame;
	private int mode;
	private JLabel LName,LSurname,LFiscalCode,LDate;
	private JTextField name,surname,fiscalCode,date;
	private JButton add,cancel;
	
	//Alunno
	
	private JLabel LSezione,LClasse;
	private JTextField sezione,classe;
	
	//Docente
	
	private JLabel LConcorso;
	private JTextField concorso;
	
	//ATA
	
	private JLabel LType;
	private JComboBox type;

	public AddEditDialog(Finestra parentFrame,boolean modal,int mode) {
		/*
		 * Modes:
		 * 0 -> Alunno
		 * 1 -> Docente
		 * 2 -> ATA
		 */
		super(parentFrame,modal);
		this.parentFrame=parentFrame;
		this.mode=mode;
	}
	
	private void initComponents() {
		LName= new JLabel("Name");
		LSurname= new JLabel("Name");
		LFiscalCode= new JLabel("Name");
		LDate= new JLabel("Name");
		
		name= new JTextField(15);
		surname= new JTextField(15);
		fiscalCode= new JTextField(15);
		date= new JTextField(15);
		
		
		this.add(LName);
		this.add(name);
		this.add(LSurname);
		this.add(surname);
		this.add(LFiscalCode);
		this.add(fiscalCode);
		this.add(LDate);
		this.add(date);
		switch(this.mode){
			case 0:
				LSezione = new JLabel("Sezione");
				LClasse = new JLabel("Classe");
				sezione =  new JTextField(15);
				classe = new JTextField(15);
				this.add(LSezione);
				this.add(sezione);
				this.add(LClasse);
				this.add(classe);
				break;
			case 1:
				LConcorso = new JLabel("Concorso");
				concorso = new JTextField(15);
				this.add(LConcorso);
				this.add(concorso);
				break;
			case 2:
				LType = new JLabel("Concorso");
				String[] os= {"SEGRETERIA","ASSISTENTE_TECNICO","OPERATORE"};
				type = new JComboBox(os);
				this.add(LType);
				this.add(type);
				break;
		}
		add= new JButton("Add");
		cancel= new JButton("Annulla");
		
		this.add(add);
		this.add(cancel);
		
		add.addActionListener(this);
		cancel.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancel)) {
			this.dispose();
		}
		if(e.getSource().equals(add)) {
			Elenco el= this.parentFrame.getEl();
			switch(this.mode){
				case 0:
				try {
					el.add(new Alunno(name.getText(), surname.getText(),  fiscalCode.getText(), Persona.GregorianCalendarFromString(date.getText()), Integer.parseInt(classe.getText()), sezione.getText()));
				} catch (NumberFormatException | TooOldException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
					break;
				case 1:
				try {
					el.add(new Docente(name.getText(), surname.getText(),  fiscalCode.getText(), Persona.GregorianCalendarFromString(date.getText()), concorso.getText()));
				} catch (TooOldException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					break;
				case 2:

				try {
					el.add(new ATA(name.getText(), surname.getText(),  fiscalCode.getText(), Persona.GregorianCalendarFromString(date.getText()),ATA.InquadramentoATAFromString((String)type.getSelectedItem())));
				} catch (TooOldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					break;
			}
			this.dispose();
			this.parentFrame.updateTab();
	}

}
}
