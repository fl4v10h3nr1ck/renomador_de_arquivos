package principal;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class Principal extends JDialog{

	
private static final long serialVersionUID = 1L;



private JTextArea resultado;





	public Principal(){
	
	super();
	
	this.setTitle("Renomeia Arquivo");
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(500 , 380);
	this.setLocationRelativeTo(null);
	this.setLayout(new GridBagLayout());
	this.setModal(true);
	this.getContentPane().setBackground(Color.WHITE);  

	this.adicionarComponentes();
	}

	
	
	
	
	public void adicionarComponentes(){
	
		
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(10, 2, 10, 2);
	
	this.add(new JLabel("Remove todo os caracteres especiais e acentuaзгo do nome do arquivo."), cons);
	
	JButton bt;
	this.add(bt = new JButton("Selecionar Arquivo"),cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(10, 2, 10, 2);
	this.add(new JScrollPane(this.resultado = new JTextArea()), cons);
	
	
		bt.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
	
		bt.setEnabled(false);	
		renomear();
		bt.setEnabled(true);	
		}});
	}



	
	
	
	
		private void renomear(){
					
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(true);	
		
		int returnVal = fc.showOpenDialog(this);

		    if (returnVal == JFileChooser.APPROVE_OPTION) {
			
		    File[] files = fc.getSelectedFiles();		
		    String resultado  = "";
		    
			    for(File aux: files){
			    
			    	if(aux!=null){
			    	
			    	String  nome= aux.getName();	
			    	String path= aux.getAbsolutePath().replace(nome, "");
			    	
			    	resultado += path+nome+"   ----->   ";  
			    	

			    	nome = this.substitui(nome);
			    	nome = this.removeInvalidos(nome);
			    	
			    	aux.renameTo(new File(path+nome)); 
			    	
			    	resultado += path+nome+"\n";  
			    	}	
			    }

			resultado += "\nFIM\n"; 
			this.resultado.setText(resultado);  
		    } 
		}

		
		
		
		
		
		
		private String removeInvalidos(String nome){
			
		if(nome== null)
		return "";
			
		String permitidos  = "abcdefghijlmnopqrstuvxzywkABCDEFGHIJLMNOPQRSTUVXZYWK0123456789.- ";
		String novo_nome  = "";
		char sbstituto  = '-';
		
			for(char letra: nome.toCharArray()){
				
			if(permitidos.contains(letra+""))
			novo_nome += letra;
			else
			novo_nome += sbstituto;
			}
			
		return novo_nome;
		}
		
		
		
		
		
		
		
		
		

		private String substitui(String nome){
			
		if(nome== null)
		return "";
					
		char[] acentuados   =  new char[]{'б', 'й', 'н', 'у', 'ъ', 'г', 'х', 'а', 'в', 'к', 'ф', 'ы', 'о', 'з', 'Б', 'Й', 'Н', 'У', 'Ъ', 'Г', 'Х', 'А', 'В', 'К', 'Ф', 'Ы', 'О', 'З'}; 	
		char[] equivalentes =  new char[]{'a', 'e', 'i', 'o', 'u', 'a', 'o', 'a', 'a', 'e', 'o', 'u', 'i', 'c', 'A', 'E', 'I', 'O', 'U', 'A', 'O', 'A', 'A', 'E', 'O', 'U', 'I', 'C'}; 	
		
		String novo_nome  = "";
		boolean controle;
		
			for(char letra: nome.toCharArray()){
			controle = false;
				for(int i = 0; i < acentuados.length; i++){
					
	
					if(letra == acentuados[i]){
					novo_nome += equivalentes[i];
					controle  = true;
					}
				}
				
			if(!controle)
			novo_nome += letra;
			}
				
			
		return novo_nome;
		}
		
		
		
		
		
		
		
		
}
	
	
	