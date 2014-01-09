import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class TraitementFichier {

	// Ne pas mettre les extensions de fichiers
	//private static String fileAdress = "C:\\Users\\Ga�tch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";
	private static String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/ProjetsEclypse/pfe-eclipse/HistoriqueZeroCoupons2";



	public static void main (String[] args){

		//	String aa = "12M";
		//System.out.println(ConvertDate(aa) + ";");
		//		
		//		String aa = "0,11224";
		//	aa.replace('0', '2');


		

		//	traitementCsv();
	}


	public static void traitementCsv(){ 

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
					//					System.out.println("Cette ligne commence par un Level ");
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers �lements
						lArray[i]= ConvertDate(lArray[i]);
						bw.write((String) lArray[i] +";");


					}		

				}

				else if(line.startsWith("0")){
					//					System.out.println("Cette ligne commence par un 0 ");
					lArray = line.split(";");
					for(int i=1; i< lArray.length; i++ ){ // On ne copie pas le premier �l�ment
						bw.write((String) lArray[i] +";");


					}		

				}

				// Premiere ligne
				else if(line.startsWith("")){
					//					System.out.println("Cette ligne commence par un vide ");
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers �lements
						bw.write((String) lArray[i] +";");
					}		

				}

			}


			bw.close(); 


		} catch (Exception e) {
			System.err.println(e.getMessage());
		}


	}
	
	public static String ConvertDate(String dateM){
		String date = null;
		int length = dateM.length();
		date = dateM.substring(0, length-1);
		int dateInt = Integer.parseInt(date); 
		date += "/12";
		//		int dateInt = Integer.parseInt(date); 
		//		dateInt *= 12;
		//		date = Integer.toString(dateInt);
		return date;
	}

}
