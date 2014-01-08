import java.io.BufferedReader;
import java.lang.Math;
import java.util.ArrayList;
import java.io.*;

//import sun.security.util.Length;

import Jama.Matrix;
import Jama.util.*;

public class Performance{

	private static String fileAdress = "C:\\Users\\Gaùtch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";

//	private static String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/ProjetsEclypse/pfe-eclipse/HistoriqueZeroCoupons2";

	private static Matrix matrixValue;
	private static String[] className; // vector containing title like "German Government Debt..."
	private static String[] time;		// vector time of maturity
	private static String[] lArray;	
	private static double[] date;		


	public static int countLine(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	public static void FillMatrixValue(){
		try{
			String line;
			BufferedReader br1 = new BufferedReader( new FileReader(fileAdress+".txt"));



			//Count number of Cols and Rows to set a double [][] to set the matrix 
			line=br1.readLine();
			className=line.split(";");

			line=br1.readLine();
			time=line.split(";");
			int nbColonne=className.length+1;

			//Count number of line
			int nbLigne=countLine(fileAdress+".txt");
			System.out.println("nbColonne: "+nbColonne+" ; NbLigne: "+nbLigne+"\n");
			
			date=new double[nbLigne-1];

			//Set a vector of vector to create the Jama matrix
			double[][] value=new double [nbLigne][nbColonne];
			
			


			line="";
			int i=0;
			while((line = br1.readLine()) != null){
				//bug : ligne 0 != German...
				System.out.println("line "+i+": "+line);
				lArray=line.split(";");
				/*				System.out.println("number"+line);
				System.out.println(lArray[0]);
				System.out.println(lArray[lArray.length-1]);
				if(nbColonne==lArray.length){
					System.out.println("nbColonne : "+nbColonne+" / lArray.lenght : "+lArray.length);
					System.out.println("Oui");
				}
				else {
					System.out.println("nbColonne : "+nbColonne+" / lArray.lenght : "+lArray.length);
					System.out.println("Non");
				}
				 */
				System.out.println("--------------------");
				for(int j=0;j<nbColonne;j++){
					if(j==0){
						date[i]=Double.parseDouble(lArray[j]);
					}else{
						value[i][j-1]=Double.parseDouble(lArray[j]);
					}
				}

				i++;

			}
			for(int y=0;y<date.length;y++){
				System.out.println(date[y]);
			}

			matrixValue=new Matrix(value);
			
			for(int j=0;j<matrixValue.getRowDimension()-1;j++){
				for(int k=0;k<matrixValue.getColumnDimension()-1;k++){
					System.out.print(matrixValue.get(j, k)+" ");
				}
				System.out.println("\n");
			}
		}
		catch (Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}


	public static void main (String[] args){
		FillMatrixValue();
//		int countryBeginSearch = matrixSearch("countryTable", 0, "German");
//		
//		System.out.println(countryBeginSearch);
//
//		
//		int dateSearch = matrixSearch("dateTable", 0, "20081210");
//		System.out.println(dateSearch);
//		
//		int maturitySearch = matrixSearch("maturityTable",countryBeginSearch, "12/12");
//		System.out.println(maturitySearch);
//		
//
//		System.out.println(matrixValue.get(dateSearch, maturitySearch));
		
		System.out.println(Rendement("relatif", "German", "3/12", "20081210", 1));

		
		//System.out.println(matrixValue);
	}


	public static double Rendement(String rendementType, String country, String maturity, String date, int duration){
		double rendement=0;

		int countryBeginSearch = matrixSearch("countryTable", 0, country);
		int maturitySearch = matrixSearch("maturityTable", countryBeginSearch, maturity);
		int dateSearch = matrixSearch("dateTable", 0, date);
		
		
		if (rendementType.equals("relatif")){
			//Matrix.get(i,j);
		//	double date1 = 0;
			int date2 = dateSearch + duration;
			
			double valDate1 = matrixValue.get(dateSearch, maturitySearch);
			double valDate2 = matrixValue.get(date2, maturitySearch);

			rendement = (valDate2 / valDate1) - 1;
			//r(t+1)/r(t) -1;

		//	rendement = RendementRelatif(country, maturity, date);

		}

		else if (rendementType.equals("difference")){
			//r(t+1)-r(t);

//			rendement = RendementDifference(country, maturity, date);

		}

		else if (rendementType.equals("log")){
			//ln(r(t+1)/r(t));

			
			
//			rendement = RendementLogarithmique(country, maturity, date);

		}
		return rendement;


	}



	/*
	 * table = country , maturity , date 
	 * 
	 */
	public static int matrixSearch(String table, int indexStart, String wordSearch )  {

	

		if (table.equals("countryTable")){
			System.out.println("Je rentre dans country");
			for (int i = indexStart ; i< className.length ; i++){
				if (className[i].startsWith(wordSearch)){
					return i;
					
				}
			}
			

		}
		else if (table.equals("maturityTable")){
			for (int i= indexStart ; i< time.length ; i++){
				if (time[i].equals(wordSearch)){
					return i;
					
				}
			}

		}	

		else if (table.equals("dateTable")){
			for (int i= indexStart ; i< date.length ; i++){
				if (date[i]==Double.parseDouble(wordSearch)){
					return i;
					
				}
			}

		}

		//else
		return 0;
	}
	
	
}
