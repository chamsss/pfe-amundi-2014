import java.io.BufferedReader;
import java.lang.Math;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

import java.util.ArrayList;
import java.io.*;

import Jama.Matrix;
import Jama.EigenvalueDecomposition;
import Jama.util.*;

public class Performance {

	//private static String fileAdress = "C:\\Users\\Gaï¿½tch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";

	//private static String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/ProjetsEclypse/pfe-eclipse/HistoriqueZeroCoupons2";
	private static String fileAdress = "C:/Users/Alexandra/workspace/pfe-eclipse/HistoriqueZeroCoupons2";
	
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

		//Print matrix values
		for(int j=0;j<a.getRowDimension();j++){
			for(int k=0;k<a.getColumnDimension();k++){
				System.out.print(a.get(j, k)+" ");
			}
			System.out.println("\n");
		}


		Matrix b = Rendement2(a, "relatif", 2);
		System.out.println("Sous matrice :");
		//Print matrix values
		for(int j=0;j<b.getRowDimension();j++){
			for(int k=0;k<b.getColumnDimension();k++){
				System.out.print(b.get(j, k)+" ");
			}
			System.out.println("\n");
		}
		
		Matrix vcv = calculVCV(b);
		
		/*Matrix vcv=new Matrix(3,3);
		vcv.set(0, 0, 1);
		vcv.set(0, 1, 0);
		vcv.set(0, 2, 1);
		vcv.set(1, 0, 0);
		vcv.set(1, 1, 1);
		vcv.set(1, 2, 1);
		vcv.set(2, 0, 0);
		vcv.set(2, 1, 0);
		vcv.set(2, 2, 1);*/
		
		//Affichage matrice
	 	System.out.println("VCV : ");
	 	for(int j=0;j<vcv.getRowDimension();j++){
			for(int k=0;k<vcv.getColumnDimension();k++){
				System.out.print(vcv.get(j, k)+" ");
			}
			System.out.println("\n");
		}
		
		
		//Valeurs propres
		double[] values = eigenValues(vcv);
		
		//Print valeurs propres
		System.out.println("Valeurs propres : ");
		if (values==null) {
			System.out.println("matrice VCV pas carrée");
		}
		
		else {
			for (int i = 0 ; i < values.length ; i++) {
				System.out.println(values[i]);
			}
		}
		
		//Vecteurs propres
		Matrix vectors = eigenVectors(vcv);
		//Print valeurs propres
		System.out.println("Vecteurs propres : ");
		if (vectors==null) {
			System.out.println("matrice VCV pas carrée");
		}
				
		else {
			for(int j=0;j<vectors.getRowDimension();j++){
				for(int k=0;k<vectors.getColumnDimension();k++){
					System.out.print(vectors.get(j, k)+" ");
				}
				System.out.println("\n");
			}
			
		}
		
		//Définie positive
		System.out.println(" VCV déf positive : " + defPositive(vcv));
		
		//Matrice de corrélation
		Matrix cor = correlationMatrix(vcv);
		
		//Print matrix correlation values
		System.out.println("Matrice de corrélation : ");
				for(int j=0;j<cor.getRowDimension();j++){
					for(int k=0;k<cor.getColumnDimension();k++){
						System.out.print(cor.get(j, k)+" ");
					}
					System.out.println("\n");
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
			System.out.println("Durï¿½e supï¿½rieur ï¿½ la plage de donnï¿½e");
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
					for(int i=nbRows-1;i>0;i-=duration){
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
				System.out.println("donnï¿½e ï¿½ l'indice " + k + " = " + indiceCol[k]);

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

			return matrixValue.getMatrix(indiceRow, indiceCol);

		}
		

	//Calcul matrice VCV
	public static Matrix calculVCV (Matrix mat) {
		
	 	int nbRows = mat.getRowDimension();
	 	int nbColumns = mat.getColumnDimension();
	 	
	 	Matrix VCV = new Matrix(nbColumns,nbColumns);
	 	
	 	//Création liste qui va contenir les vecteurs (tab) colonne de mat
	 	ArrayList<double[]> liste = new ArrayList();
	 	
	 	//Remplissage liste par les vecteurs colonnes de la matrice
	 	for (int j = 0 ; j < nbColumns ; j++){
	 		//Vecteurs de mat
	 		double[] vecteur = new double[nbRows];
	 		
	 		for (int i = 0 ; i < nbRows ; i++){
	 			vecteur[i] = mat.get(i, j);
	 		}
	 		liste.add(vecteur);
	 	}
	 		 	
	 	Covariance cov = new Covariance();
	 	
	 	//Calcul des covariances et remplissage matrice VCV
	 	for (int k = 0 ; k < nbColumns ; k++){
	 		for (int l = 0 ; l < nbColumns ; l++){
	 			VCV.set(l, k, cov.covariance(liste.get(k), liste.get(l)));
	 		}
	 	}
	 	
	 	return VCV;
	}
	
	//Calcul valeurs propres
	public static double[] eigenValues(Matrix mat){
		
		//On vérifie que la matrice soit bien carrée
		if (mat.getRowDimension() == mat.getColumnDimension()){
			return mat.eig().getRealEigenvalues();
		}
		
		else return null;
		
	}
	
	//Calcul vecteurs propres
	public static Matrix eigenVectors(Matrix mat){
		
		//On vérifie que la matrice soit bien carrée
		if (mat.getRowDimension() == mat.getColumnDimension()){
			EigenvalueDecomposition m = new EigenvalueDecomposition(mat);
			return m.getV();
		}
		
		else return null;
	}
	
	//Vérifie si VCV déf positive
	public static boolean defPositive(Matrix mat){
		
		double[] values = eigenValues(mat);
		
		if (values==null){
			return false;
		}
		
		else {
			int i = 0;
			while(i < values.length && values[i] > 0){
				i++;
			}

			if(i==values.length){
				return true;
			}
			
			else return false;
		}
	}
	
	//Calcul matrice de corrélation
	public static Matrix correlationMatrix(Matrix mat) {
		Matrix cor = mat;
		for (int i = 0 ; i < mat.getRowDimension() ; i++){
			for (int j = 0 ; j< mat.getColumnDimension() ; j++) {
				cor.set(i, j, cor.get(i, j)/Math.sqrt((cor.get(i, i)*cor.get(j, j))));
			}
		}
		return cor;
	}
}