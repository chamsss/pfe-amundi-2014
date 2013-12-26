import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class TraitementFichier {

	// Ne pas mettre les extensions de fichiers
	private static String fileAdress = "C:\\Users\\Gaùtch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";



	public static void main (String[] args){


		traitementCsv2();
	}


	public static void traitementCsv(){ // Marche pour la premiere ligne

		try {

			BufferedReader br = new BufferedReader( new FileReader(fileAdress+".csv"));
			BufferedWriter bw = new BufferedWriter( new FileWriter(fileAdress+".txt")); 

			String line;
			String[] lArray;
			ArrayList al = new ArrayList();

			int j = 0 ;

			// On lit la premiere ligne
			if((line = br.readLine()) != null){

		//	while((line = br.readLine()) != null){

				lArray = line.split(";");
				for(int i=0; i< lArray.length; i++ ){
					if (!lArray[i].equals("")){ 
						al.add(lArray[i]); // On met dans une liste si différent de zero
					}
				}
			}

			for(int i=0; i< al.size(); i++ ){
				bw.write((String) al.get(i)+";");
			}
			bw.close(); 


		} catch (Exception e) {
			System.err.println(e.getMessage());
		}


	}


	public static void traitementCsv2(){ 

		try {

			BufferedReader br = new BufferedReader( new FileReader(fileAdress+".csv"));
			BufferedWriter bw = new BufferedWriter( new FileWriter(fileAdress+".txt")); 

			String line;
			String[] lArray;
			ArrayList al = new ArrayList();

			int j = 0 ;

			// On lit la premiere ligne
		//	if((line = br.readLine()) != null){

			while((line = br.readLine()) != null){

				bw.write((String) "\n");

				
				if(line.startsWith("Level")){
					System.out.println("Cette ligne commence par un Level ");
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers élements
								al.add(lArray[i]); 
								bw.write((String) al.get(i-1)+";");

						
					}		
					
				}
				
				else if(line.startsWith("0")){
					System.out.println("Cette ligne commence par un 0 ");
					lArray = line.split(";");
					for(int i=1; i< lArray.length; i++ ){ // On ne copie pas le premier élément
						al.add(lArray[i]); 
						bw.write((String) al.get(i-1)+";");

						
					}		
					
				}
				
				// Premiere ligne
				else if(line.startsWith("")){
					System.out.println("Cette ligne commence par un vide ");
				//	System.out.println(line);
					lArray = line.split(";");
					for(int i=2; i< lArray.length; i++ ){ // On ne copie pas les 2 premiers élements
						//if (!lArray[i].equals("")){ 
								al.add(lArray[i]); // On met dans une liste si différent de zero
						//		System.out.println(l);
								bw.write((String) al.get(i-1)+";");

					//	}
					}		
					
				}

			}
			
			
			System.out.println("al2");
			System.out.println(al);
			
			for(int i=0; i< al.size(); i++ ){
				bw.write((String) al.get(i)+";");
			}
			bw.close(); 


		} catch (Exception e) {
			System.err.println(e.getMessage());
		}


	}


}
