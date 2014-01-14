import java.io.BufferedReader;
import java.lang.Math;
import java.io.*;

import Jama.Matrix;

public class Performance {

	//private static String fileAdress = "C:\\Users\\Gaï¿½tch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";
	//private static String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/ProjetsEclypse/pfe-eclipse/HistoriqueZeroCoupons2";
	//private static String fileAdress = "C:/Users/Alexandra/workspace/pfe-eclipse/HistoriqueZeroCoupons2";
	private static String fileAdress = "C:/Users/Alexandra/Mes Documents/MAM5/PFE/ExplicationVCVRiskMetrics";
	
	private static Matrix matrixValue;
	private static String[] className; // vector containing title like "German Government Debt..."
	private static String[] time;		// vector time of maturity
	private static String[] lArray;	
	private static double[] date;		
	
	//count the number of line in a textfile
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

	//Fill Matrix value from textfile values
	public static void FillMatrixValue(){
		try{
			String line;
			BufferedReader br1 = new BufferedReader( new FileReader(fileAdress+".txt"));
			
			line=br1.readLine();
			
			//Vérifie si lignes nulles au début
			while (line.isEmpty()){
				line=br1.readLine();
			}
			
			//Count number of Cols and Rows to set a double [][] to set the matrix 
			className=line.split(";");

			line=br1.readLine();
			time=line.split(";");

			int nbColonne=className.length+1;

			//Count number of line
			int nbLigne=countLine(fileAdress+".txt");
			date=new double[nbLigne-1];

			//Set a vector of vector to create the Jama matrix
			double[][] value=new double [nbLigne][nbColonne];

			line="";
			int i=0;
			while((line = br1.readLine()) != null){
				//bug : ligne 0 != German...
				lArray=line.split(";");
				for(int j=0;j<nbColonne;j++){

					if(j==0){
						date[i]=Double.parseDouble(lArray[j]);
						
					}else{
						value[i][j-1]=Double.parseDouble(lArray[j]);
					}
				}

				i++;

			}
			br1.close();
			matrixValue=new Matrix(value);
		}
		catch (Exception e){
			System.out.println("Error: "+e.getMessage());
		}
		
	}


	//Compute Yield Matrix of chosen submatrix "mat"
	// rendementType : type of yield (relative, log,...)
	//duration : yield duration in days (daily,weekly, ...)
	public static Matrix Rendement2(Matrix mat,String rendementType, int duration){

		Matrix rendement=null;
		
		int nbCols=mat.getColumnDimension();
		int nbRows=mat.getRowDimension();
		double[][] value;
		value=new double[(nbRows-1)/duration][nbCols];
		
		
		if(duration>nbRows){
//			System.out.println("Durï¿½e supï¿½rieur ï¿½ la plage de donnï¿½e");
		}else{

			//rendement relatif
			if (rendementType.equals("relatif")){
				
				//for each cols
				for(int j=0;j<nbCols;j++){
					//compute yield depending on the duration.
					int cptRows=0;
					//for each rows
					for(int i=0;i+duration<nbRows;i+=duration){
						double valDate1 = mat.get(i, j);
						double valDate2 = mat.get(i+duration, j);
						value[cptRows][j] = (valDate2/valDate1)-1;
						cptRows++;
					}
				}
				//r(t+1)/r(t) -1;
			}
			else if (rendementType.equals("difference")){
				//for each cols
				for(int j=0;j<nbCols;j++){
					int cptRows=0;
					//compute performance depending on the duration.
					for(int i=0;i+duration<nbRows;i+=duration){
						double valDate1 = mat.get(i, j);
						double valDate2 = mat.get(i+duration, j);
						value[cptRows][j]= valDate2-valDate1;
						cptRows++;
					}
				}
				//r(t+1)-r(t);
			}
			
			else if (rendementType.equals("log")){
				for(int j=0;j<nbCols;j++){
					int cptRows=0;
					//compute performance depending on the duration.
					for(int i=0;i+duration<nbRows;i+=duration){
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


		/* table = country , maturity , date 
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
		 * 1 annï¿½e = 260 jours
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

//				System.out.println("TableCountry i : " + tableCountry[i]);
				int countryBeginSearch = matrixSearch("countryTable", 0, tableCountry[i]);
//				System.out.println("countryBeginSearch = " + countryBeginSearch );

				for (int j = 0 ; j < tableDuration.length ; j++){
//					System.out.println("tableDuration i : " + tableDuration[j]);

					int maturitySearch = matrixSearch("maturityTable", countryBeginSearch, tableDuration[j]);
//					System.out.println("maturitySearch = " + maturitySearch );

					indiceCol[compteur] = maturitySearch;

					compteur++;
					//subMatrix.set(i,j, matrixValue.get(i, j));				
				}


			}
			for(int k = 0; k < indiceCol.length; k++)
			{
//				System.out.println("donnï¿½e ï¿½ l'indice " + k + " = " + indiceCol[k]);

			} 




			int jourOuvre = nbYear * 260;
			int nbRow = matrixValue.getRowDimension();
			//System.out.println("jourOuvre "  + jourOuvre);
			//System.out.println("nbRow " + nbRow);
			int[] indiceRow =  new int[jourOuvre];
			int j = 0;

			for (int i=nbRow-jourOuvre; i<nbRow; i++){
//				System.out.println(i);
				indiceRow[j]=i-1;
				j++;
			}

			return matrixValue.getMatrix(indiceRow, indiceCol);

		}
}