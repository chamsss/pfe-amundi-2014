import java.io.BufferedReader;
import java.lang.Math;
import java.util.ArrayList;
import java.io.*;

//import sun.security.util.Length;

import Jama.Matrix;
import Jama.util.*;

public class Performance{

	private static String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/ProjetsEclypse/pfe-eclipse/HistoriqueZeroCoupons2";

	static Matrix matrixValue;


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

			String[] className;
			String[] time;
			String[] lArray;

			//Count number of Cols and Rows to set a double [][] to set the matrix 
			line=br1.readLine();
			className=line.split(";");

			line=br1.readLine();
			time=line.split(";");
			int nbColonne=className.length+1;

			int nbLigne=countLine(fileAdress+".txt");
			System.out.println("nbColonne: "+nbColonne+" ; NbLigne: "+nbLigne+"\n");
			double[][] value=new double[nbColonne][nbLigne];


			line="";
			int i=0;
			while((line = br1.readLine()) != null){
				//bug : ligne 0 != German...
				System.out.println("line "+i+": "+line);
				line=line.replace(',','.');
				System.out.println(line);
				lArray=line.split(";");
				//System.out.println("number"+line);
				System.out.println(lArray[0]);
				System.out.println(lArray[lArray.length-1]);

//				if(nbColonne==lArray.length){
//					System.out.println("nbColonne : "+nbColonne+" / lArray.lenght : "+lArray.length);
//					System.out.println("Oui");
//				}
//				else {
//					System.out.println("nbColonne : "+nbColonne+" / lArray.lenght : "+lArray.length);
//					System.out.println("Non");
//				}
				System.out.println("--------------------");
				for(int j=0;j<nbColonne;j++){
					System.out.println("j:"+j);
					System.out.println(Double.parseDouble(lArray[j]));
					value[j][i]=Double.parseDouble(lArray[j]);
				}

				i++;

			}

			//matrixValue=new Matrix(double[][] value);

		}
		catch (Exception e){
			System.out.println("Error");
		}
	}


	public static void main (String[] args){
		FillMatrixValue();
		//System.out.println(matrixValue);
	}



	public double RendementRelatif(){
		double rendement=0;
		//r(t+1)/r(t) -1;
		return rendement;
	}

	public double RendementDifference(){
		double rendement=0;
		//r(t+1)-r(t);

		return rendement;
	}

	public double RendementLogarithmique(){
		double rendement=0;
		//ln(r(t+1)/r(t));

		return rendement;
	}
}
