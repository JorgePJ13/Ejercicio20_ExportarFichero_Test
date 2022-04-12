package modelo.negocio;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import modelo.entidad.Coche;

public class CochesToPdf {
	public void generarFichero(List<Coche> lista) throws Exception{
				
		try (PDDocument doc = new PDDocument()) {

			PDPage myPage = new PDPage();
			doc.addPage(myPage);

			try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

				printTitlePdf(cont);
				
				printVehiclesPdf(cont,lista);

				cont.endText();		
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw ioe;
			}
			
			doc.save("src/main/resources/coches.pdf");
			System.out.println("Fichero pdf creado en src/main/resources/coches.pdf");
			System.out.println("Refresque el proyecto en caso de que no aparezca");
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Printa los coches de la base de datos en el pdf
	 * @param cont
	 * @throws IOException	 */
	private void printVehiclesPdf(PDPageContentStream cont, List<Coche> coches) 
			throws IOException {
				for (int i = 0; i < coches.size(); i++) {
			String carLine = coches.get(i).getMarca() + " " + coches.get(i).getModelo() + " " +
							coches.get(i).getKilometros() + " " + coches.get(i).getMatricula();
			cont.showText(carLine);
			cont.newLine();
		}
	}

	/**
	 * Printa el nombre de los atributos del titulo en el pdf
	 * @param cont
	 * @throws IOException
	 */
	private void printTitlePdf(PDPageContentStream cont) throws IOException {
		
		String[] headers = {"Marca", "Modelo", "Km's", "Matricula"};
		
		cont.beginText();

		cont.setFont(PDType1Font.TIMES_ROMAN, 12);
		cont.setLeading(14.5f);

		cont.newLineAtOffset(25, 700);
		String headersLine = "";
		for (String h: headers) headersLine += h + " ";
		cont.showText(headersLine);

		cont.newLine();
	}
}
