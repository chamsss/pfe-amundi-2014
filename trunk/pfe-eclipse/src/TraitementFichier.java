import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class TraitementFichier {

	// Ne pas mettre les extensions de fichiers
	private static String fileAdress = "C:\\Users\\Gaùtch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";


	public static void main (String[] args){


		traitementCsv();

//		try {
//
//			BufferedReader br = new BufferedReader( new FileReader(new File(fileAdress)));
//
//
//			String line;
//			int i=0;
//			String[] lArray;
//			while((line = br.readLine()) != null){
//				 lArray = line.split(";");	
//				System.out.println(lArray[i]);
//				i++;
//			}
//		} catch (Exception e) {
//			e.getMessage();
//		}
//		


	}
	
	public static void traitementCsv(){
		
		try {

			BufferedReader br = new BufferedReader( new FileReader(fileAdress+".csv"));
			BufferedWriter bw = new BufferedWriter( new FileWriter(fileAdress+".txt")); 
			
			String line;
			String[] lArray;
		    ArrayList al = new ArrayList();
		    
		    int j = 2 ;
			while((line = br.readLine()) != null){
				
				 lArray = line.split(";");	
				 
				 al.add(lArray[j]);
				 j++;
			}
			
			//al.remove(0); // We delete "name"
			
			for(int i=0; i< al.size(); i++ ){
				System.out.println(al.get(i));
				bw.write((String) al.get(i)+";");


				
			}
//			bw.newLine();
//		//	bw.write();
//			bw.newLine();
//			String s = "tout le monde 2";
//			bw.write(s);
			bw.close(); 
			
			System.out.println(al);
			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		

	}
		
		
	

}
