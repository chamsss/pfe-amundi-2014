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
			//	System.out.println("nbColonne: "+nbColonne+" ; NbLigne: "+nbLigne+"\n");

			date=new double[nbLigne-1];

			//Set a vector of vector to create the Jama matrix
			double[][] value=new double [nbLigne][nbColonne];




			line="";
			int i=0;
			while((line = br1.readLine()) != null){
				//bug : ligne 0 != German...
				//			System.out.println("line "+i+": "+line);
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
				//	System.out.println("--------------------");
				for(int j=0;j<nbColonne;j++){
					if(j==0){
						date[i]=Double.parseDouble(lArray[j]);
					}else{
						value[i][j-1]=Double.parseDouble(lArray[j]);
					}
				}

				i++;

			}
			//			for(int y=0;y<date.length;y++){
			//				System.out.println(date[y]);
			//			}

			matrixValue=new Matrix(value);

			//			for(int j=0;j<matrixValue.getRowDimension()-1;j++){
			//				for(int k=0;k<matrixValue.getColumnDimension()-1;k++){
			//					System.out.print(matrixValue.get(j, k)+" ");
			//				}
			//				System.out.println("\n");
			//			}
		}
		catch (Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}


	public static void main (String[] args){
		FillMatrixValue();
		String[] tableCountry = {"German","French"};
		String[]  tableDuration= {"1/12","12/12"};
		Matrix a = extractSubMatrix(5, tableCountry, tableDuration);


		//		for(int j=0;j<a.getRowDimension()-1;j++){
		//			for(int k=0;k<a.getColumnDimension()-1;k++){
		//				System.out.print(a.get(j, k)+" ");
		//			}
		//			System.out.println("\n");
		//		}







		for(int j=0;j<a.getRowDimension();j++){
			for(int k=0;k<a.getColumnDimension();k++){
				System.out.print(a.get(j, k)+" ");
			}
			System.out.println("\n");
		}



		Matrix b = Rendement2(a, "relatif", 2);
		
		for(int j=0;j<b.getRowDimension();j++){
			for(int k=0;k<b.getColumnDimension();k++){
				System.out.print(b.get(j, k)+" ");
			}
			System.out.println("\n");
		}
		
	}


	public static Matrix Rendement2(Matrix mat,String rendementType, int duration){

		Matrix rendement=null;
		int nbCols=mat.getColumnDimension();
		int nbRows=mat.getRowDimension();
		double[][] value;
		if(duration==1){
			value=new double[nbRows/duration-1][nbCols];
		}
		else {
			value=new double[nbRows/duration][nbCols];
		}
		
		if(duration>nbRows){
			System.out.println("Durée supérieur à la plage de donnée");
		}else{

			//rendement relatif
			if (rendementType.equals("relatif")){
				//pour chaque colonne

				for(int j=0;j<nbCols;j++){
					//compute performance depending on the duration.
					int cptRows=0;

					for(int i=0;i+duration<nbRows;i+=duration){
						double valDate1 = mat.get(i, j);
						double valDate2 = mat.get(i+duration, j);
						if(valDate2!=0){
							value[cptRows][j]= (valDate2 / valDate1) - 1;
						}
						else{
							value[cptRows][j]= 0;
						}
						cptRows++;
					}
				}
				//r(t+1)/r(t) -1;
				//	rendement = RendementRelatif(country, maturity, date);
			}
			else if (rendementType.equals("difference")){
				for(int j=0;j<nbCols;j++){
					int cptRows=0;

					//compute performance depending on the duration.
					for(int i=nbRows-1;i>0;i-=duration){
						double valDate1 = mat.get(i, j);
						double valDate2 = mat.get(i+duration, j);
						value[cptRows][j]= valDate2-valDate1;
						cptRows++;
					}
				}
				//r(t+1)-r(t);
				//			rendement = RendementDifference(country, maturity, date);

			}
			else if (rendementType.equals("log")){
				for(int j=0;j<nbCols;j++){
					int cptRows=0;
					//compute performance depending on the duration.
					for(int i=nbRows-1;i>0;i-=duration){
						double valDate1 = mat.get(i, j);
						double valDate2 = mat.get(i+duration, j);
						if(valDate2!=0 && valDate1!=0) value[cptRows][j]= Math.log(valDate2/valDate1);
						cptRows++;
					}
				}
			}
		}
		rendement=new Matrix(value);
		return rendement;
	}

		//	public static double Rendement(String rendementType, String country, String maturity, String date, int duration){
		//		double rendement=0;
		//
		//		int countryBeginSearch = matrixSearch("countryTable", 0, country);
		//		int maturitySearch = matrixSearch("maturityTable", countryBeginSearch, maturity);
		//		int dateSearch = matrixSearch("dateTable", 0, date);
		//		
		//		
		//		if (rendementType.equals("relatif")){
		//			//Matrix.get(i,j);
		//		//	double date1 = 0;
		//			int date2 = dateSearch + duration;
		//			
		//			double valDate1 = matrixValue.get(dateSearch, maturitySearch);
		//			double valDate2 = matrixValue.get(date2, maturitySearch);
		//
		//			rendement = (valDate2 / valDate1) - 1;
		//			//r(t+1)/r(t) -1;
		//
		//		//	rendement = RendementRelatif(country, maturity, date);
		//
		//		}
		//
		//		else if (rendementType.equals("difference")){
		//			//r(t+1)-r(t);
		//
		////			rendement = RendementDifference(country, maturity, date);
		//
		//		}
		//
		//		else if (rendementType.equals("log")){
		//			//ln(r(t+1)/r(t));
		//
		//			
		//			
		////			rendement = RendementLogarithmique(country, maturity, date);
		//
		//		}
		//		return rendement;
		//
		//
		//	}



		/*
		 * table = country , maturity , date 
		 * 
		 */
		public static int matrixSearch(String table, int indexStart, String wordSearch )  {



			if (table.equals("countryTable")){
				//	System.out.println("Je rentre dans country");
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


		/*
		 * 1 année = 260 jours
		 * tableCountry = ["France", "German" etc... ]
		 * tableDuration = ["1/12", " 12/12" etc ... ]
		 * 
		 */
		public static Matrix extractSubMatrix(int nbYear, String[] tableCountry, String[] tableDuration){



			Matrix subMatrix = null ;

			int[] indiceCol = new int[tableCountry.length * tableDuration.length];
			//	    ArrayList indiceCol = new ArrayList();
			int compteur = 0; 

			for (int i = 0 ; i < tableCountry.length ; i++){

				System.out.println("TableCountry i : " + tableCountry[i]);
				int countryBeginSearch = matrixSearch("countryTable", 0, tableCountry[i]);
				System.out.println("countryBeginSearch = " + countryBeginSearch );

				for (int j = 0 ; j < tableDuration.length ; j++){
					System.out.println("tableDuration i : " + tableDuration[j]);

					int maturitySearch = matrixSearch("maturityTable", countryBeginSearch, tableDuration[j]);
					System.out.println("maturitySearch = " + maturitySearch );

					indiceCol[compteur] = maturitySearch;

					compteur++;
					//subMatrix.set(i,j, matrixValue.get(i, j));				
				}


			}
			for(int k = 0; k < indiceCol.length; k++)
			{
				System.out.println("donnée à l'indice " + k + " = " + indiceCol[k]);

			} 




			int jourOuvre = nbYear ;//* 260;
			int nbRow = matrixValue.getRowDimension();
			System.out.println("jourOuvre "  + jourOuvre);
			System.out.println("nbRow " + nbRow);
			int[] indiceRow =  new int[jourOuvre];
			int j = 0;

			for (int i=nbRow-jourOuvre; i<nbRow; i++){
				System.out.println(i);
				indiceRow[j]=i-1;
				j++;
			}

			//		for(int k = 0; k < indiceRow.length; k++)
			//	    {
			//	      System.out.println("donnée à l'indice " + k + " = " + indiceRow[k]);
			//	      
			//	    } 		
			//matrixValue.getMatrix(, arg1)
			return matrixValue.getMatrix(indiceRow, indiceCol);

			//	matrixValue; 


		}

	}
