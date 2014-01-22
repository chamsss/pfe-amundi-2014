import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Permet de convertir un fichier CSV en fichier txt 
 * afin de pouvoir manipuler les données
 */

public class TraitementFichier {
	
	private String fileAdress;
	
	
	public TraitementFichier(String fileAdress) {
		this.fileAdress = fileAdress;
	}

	
	public void traitementCsv(){ 

		try {

			BufferedReader br = new BufferedReader( new FileReader(fileAdress+".csv"));
			BufferedWriter bw = new BufferedWriter( new FileWriter(fileAdress+".txt")); 

			String line;
			String[] lArray;


			while((line = br.readLine()) != null){

				bw.write((String) "\n");

				line = line.replace(',', '.');
				System.out.println(line);

				if(line.startsWith("Level")){ // Date avec 1M 2M etc...
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers ï¿½lements
						lArray[i]= ConvertDate(lArray[i]);
						bw.write((String) lArray[i] +";");
					}		
				}

				else if(line.startsWith("0")){
					lArray = line.split(";");
					for(int i=1; i< lArray.length; i++ ){ // On ne copie pas le premier ï¿½lï¿½ment
						bw.write((String) lArray[i] +";");
					}		
				}

				// Premiere ligne
				else if(line.startsWith("")){
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers ï¿½lements
						bw.write((String) lArray[i] +";");
					}		

				}

			}

			bw.close();
			br.close();


		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	
	/*
	 * Permet de convertir une date de la forme 1M en 1/12
	 * 	
	 */
	
	public String ConvertDate(String dateM){
		String date = null;
		int length = dateM.length();
		date = dateM.substring(0, length-1);
		date += "/12";
		
		return date;
	}

}
