import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class test {

	private static String fileAdress = "C:\\Users\\Gaùtch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons.csv";

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

			BufferedReader br = new BufferedReader( new FileReader(new File(fileAdress)));


			String line;
			String[] lArray;
		    ArrayList al = new ArrayList();
		    
			while((line = br.readLine()) != null){
				
				 lArray = line.split(";");	
				 al.add(lArray[1]);
			}
			al.remove(0); // We delete "name"
			System.out.println(al);
			
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		

	}
		
		
	

}
