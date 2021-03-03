import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Finestra extends JFrame implements ActionListener{
	private DefaultTableModel dft;
	private JTable table;
	private JComboBox choose;
	private JMenuBar jmb;
	private JMenu file, addEdit;
	private JMenu obj,csv;
	private JMenuItem loadObj,loadCsv,saveObj,saveCsv;
	private JScrollPane jsp;
	private static Object[] tableHeaders = {"Cognome","Nome","Codice fiscale","Data di nascita"};
	private static String[] comboOpts = {"Alunno","Docente","ATA"};
	private Elenco el;
	private void initComponents() {
		jmb= new JMenuBar();
		file = new JMenu("File");
		
		obj = new JMenu("Oggetto");
		csv = new JMenu("CSV");
		
		loadObj = new JMenuItem("Carica");
		loadCsv = new JMenuItem("Carica");
		saveObj = new JMenuItem("Salva");
		saveCsv = new JMenuItem("Salva");
		

		loadObj.addActionListener(this);
		loadCsv.addActionListener(this);
		saveObj.addActionListener(this);
		saveCsv.addActionListener(this);
		
		addEdit= new JMenu("Aggiungi/Modifica");
		
		obj.add(loadObj);
		obj.add(saveObj);
		csv.add(loadCsv);
		csv.add(saveCsv);
		
		file.add(obj);
		file.add(csv);
		
		jmb.add(file);
		jmb.add(addEdit);
		this.setJMenuBar(jmb);
	
		
		dft = new DefaultTableModel();
		dft.setColumnIdentifiers(Finestra.tableHeaders);
		table= new JTable(dft);
		jsp= new JScrollPane(table);
		
		choose=  new JComboBox(Finestra.comboOpts);
		
		this.setLayout(new GridLayout(2,1));
		
		this.add(jsp);
		this.add(choose);
		
		choose.addActionListener(this);
		
	}
	public Finestra() {
		initComponents();
		el= new Elenco();
	}
	public static void main(String[] args){
		Finestra f = new Finestra();
		f.setSize(800,600);
		f.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(loadObj)) {
			 JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
						el.load(chooser.getSelectedFile(), FileMode.OBJECT);
					} catch (ClassNotFoundException | IOException | TooOldException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
		}
		else if(e.getSource().equals(loadCsv)) {
			 JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
						el.load(chooser.getSelectedFile(), FileMode.CSV);
					} catch (ClassNotFoundException | IOException | TooOldException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
		}
		else if(e.getSource().equals(saveObj)) {
			 JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showSaveDialog(this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							el.write(chooser.getSelectedFile(), FileMode.OBJECT);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
			    }
		}
		else if(e.getSource().equals(saveCsv)) {
			 JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showSaveDialog(this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							el.write(chooser.getSelectedFile(), FileMode.CSV);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    }
		}
		else if (e.getSource().equals(choose)) {
			dft.setRowCount(0);
			for(int i=0;i<el.size();i++) {
				if(choose.getSelectedIndex()==0) {
						if(!(el.get(i) instanceof Alunno)) break;
				}
				if(choose.getSelectedIndex()==1) {
					if(!(el.get(i) instanceof Docente)) break;
				}
				if(choose.getSelectedIndex()==2) {
					if(!(el.get(i) instanceof ATA)) break;
				}
				Object[] col= {el.get(i).getName(),el.get(i).getSurname(),Persona.GregorianCalendarToString(el.get(i).getBirthDate()),el.get(i).getFiscalCode()};
				dft.addColumn(col);		
			}
		}
		else if(e.getSource().equals(addEdit)) {
			Object[] possibleValues = { "Docente", "Alunno", "Third" };
			Object selectedValue = JOptionPane.showInputDialog(null,
			"Choose one", "Input",
			JOptionPane.INFORMATION_MESSAGE, null,
			possibleValues, possibleValues[0]);
			AddEditDialog a= new AddEditDialog(this,true,0);
		}
	}
	public void updateTab() {
		dft.setRowCount(0);
		for(int i=0;i<el.size();i++) {
			Object[] col= {el.get(i).getName(),el.get(i).getSurname(),Persona.GregorianCalendarToString(el.get(i).getBirthDate()),el.get(i).getFiscalCode()};
			dft.addColumn(col);		
		}
	}
	public Elenco getEl() {
		return el;
	}
	public void setEl(Elenco el) {
		this.el = el;
	}

}
