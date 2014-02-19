import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class TraitementExcel {

	private String fileAdress;

	public int countLine(String filename) throws IOException {
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


	public TraitementExcel (String fileAdress) {
		this.fileAdress = fileAdress;
	}


	public Bond[] traitementCsv(){ 

		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");
			BufferedReader br = new BufferedReader( new FileReader(fileAdress+".csv"));
			
			String line;
			String[] lArray;
			int nbLigne=countLine(fileAdress+".csv");
			Bond[] test = new Bond[nbLigne];
			int k=0;
			while((line = br.readLine()) != null){
				line = line.replace(',', '.');
				//System.out.println(line);
				if(k>0){
					lArray = line.split(";");
					String description=lArray[0];
					String currency=lArray[2];
					double amount_outstanding=Double.parseDouble(lArray[4]);
					double price=Double.parseDouble(lArray[5]);
					double coupon=Double.parseDouble(lArray[6]);
					int freq=Integer.parseInt(lArray[7]);
					DateTime dt = formatter.parseDateTime(lArray[8]);
					double yield=Double.parseDouble(lArray[9]);
					double oas=Double.parseDouble(lArray[11]);
					String codeClass=lArray[16];

				//	test[k-1]= new Bond(description,currency,amount_outstanding,price,coupon,freq,date,yield,oas,codeClass);

				}		

				k++;
			}

			br.close();
			return test;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

}
