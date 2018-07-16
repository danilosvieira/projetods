package br.com.server.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class ArquivoModel {
	
	private MultipartFile arquivoUpload;
	private String pathArquivoExcel;

	public ArquivoModel(MultipartFile arquivo) {
		this.arquivoUpload = arquivo;
	}
	
	public void processarArquivoOcorrenciaPalavras () {
		String extensao = FilenameUtils.getExtension(this.arquivoUpload.getOriginalFilename());
		String texto = "";
		
		try {
			// De acordo com o tipo do arquivo, direciona ao método específico para a obtensão do texto.
			if (extensao.equals("doc")) {
				texto = this.extrairTextoArquivoDoc();
			} else if (extensao.equals("docx")) {
				texto = this.extrairTextoArquivoDocx();
			} else if (extensao.equals("pdf")) {
				texto = this.extrairTextoArquivoPdf();
			} else if (extensao.equals("jpg") || extensao.equals("tif")) {
				texto = this.extrairTextoArquivoImagem();
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!texto.equals("")) {
			//Retorna Map contendo todas as palavras com respectiva frequencia no texto.
			Map<String,Integer> mapPalavras = this.contabilizarFrequenciaPalavras(texto);
			
			//Percorre o Map para gerar o arquivo excel.
			this.gerarArquivoExcel(mapPalavras);
		}
		
	}
	
	private Map<String,Integer> contabilizarFrequenciaPalavras(String texto) {
		Map<String,Integer> mapPalavras = new HashMap<String, Integer>(); 
		
		 // Converte todo o texto para minúsculo
        String minusculo = texto.toLowerCase();
         
        //Aplica a expressão regular
        Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
        Matcher m = p.matcher(minusculo);
        
        while(m.find())
        {
          String palavra = m.group(); //pega uma palavra/token   
          Integer frequencia = mapPalavras.get(palavra); //verifica se  a palavra já está no mapa    
             
            if (frequencia != null) { 
            	//Encontrando a palavra, atualiza a frequencia incrementando 1.
                mapPalavras.put(palavra, frequencia + 1);
            } else { 
            	// Não encontrando a palavra, insere a palavra com valor de frequencia 1.
                mapPalavras.put(palavra, 1);
            }
        }

		return mapPalavras;
	}
	
	//Utilização de Apache POI, Java API para documentos Microsoft
	private void gerarArquivoExcel(Map<String,Integer> mapPalavras) {
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Palavras");
        
        String fileName = this.arquivoUpload.getOriginalFilename()+".xls";
        
        int rownum = 0;
        
        for (Map.Entry<String, Integer> palavrasEntry : mapPalavras.entrySet()) {
        	Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            
            Cell cellPalavra = row.createCell(cellnum++);
            cellPalavra.setCellValue(palavrasEntry.getKey());
            
            Cell cellFrequencia = row.createCell(cellnum++);
            cellFrequencia.setCellValue(palavrasEntry.getValue());
        }
        
        try {
        	File file = new File(fileName);
        	
            FileOutputStream arquivoGerado = new FileOutputStream(file);
            workbook.write(arquivoGerado);
            arquivoGerado.close();
            
            this.pathArquivoExcel = fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Utilizado Apache POI, Java API para documentos Microsoft
	private String extrairTextoArquivoDoc() throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(this.arquivoUpload.getOriginalFilename()));
		WordExtractor extractor = new WordExtractor(fs);
		return extractor.getText();
	}
	
	//Utilizado Apache POI, Java API para documentos Microsoft
	private String extrairTextoArquivoDocx() throws FileNotFoundException, IOException {
		XWPFDocument docx = new XWPFDocument(new FileInputStream(this.arquivoUpload.getOriginalFilename()));
	    XWPFWordExtractor we = new XWPFWordExtractor(docx);
		   
		return we.getText();
	}
	
	//Utilizado Apache PDFBox para leitura do arquivo PDF
	private String extrairTextoArquivoPdf() throws FileNotFoundException, IOException {
		 
		PDFParser parser;
		File file;
		
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		   
		file = new File(this.arquivoUpload.getOriginalFilename());
		parser = new PDFParser(new RandomAccessFile(file,"r")); 
		  
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(10);
		   
		return pdfStripper.getText(pdDoc);

	}
	
	public String getPathArquivoExcel() {
		return pathArquivoExcel;
	}

	@SuppressWarnings("unused")
	private String extrairTextoArquivoImagem() throws IOException {
		String texto = "";
		
		//Solicitação HTTP para a API Google Cloud Vision, que faz a analise de imagens, podendo faszer detecção de texto.
		String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate?";
		String API_KEY = "key=AIzaSyCasqKugf3S97qxgmpkBf4cUMLMgutsrB0";
		
		File file = new File(this.arquivoUpload.getOriginalFilename());
		
		String imageData = this.encoder(file);
			
		URL serverUrl = new URL(TARGET_URL + API_KEY);
		URLConnection urlConnection = serverUrl.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
		
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		
		httpConnection.setDoOutput(true);
		
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
		httpRequestBodyWriter.write
		 ("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\""
		 +"}], \"image\": { \"content\":"
		 +" \""+ imageData +"\"}}]}");
		httpRequestBodyWriter.close();
		
		String response = httpConnection.getResponseMessage();

		Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
		String resp = "";
		while (httpResponseScanner.hasNext()) {
		   String line = httpResponseScanner.nextLine();
		   resp += line;
		}
		
		JSONObject jsonOne = new JSONObject(resp);
		
		JSONArray responses = jsonOne.getJSONArray("responses");
		
		JSONObject jsonTwo = (JSONObject) responses.get(0);
		
		JSONObject fullTextAnnotation = jsonTwo.getJSONObject("fullTextAnnotation");
		
		texto = fullTextAnnotation.getString("text");
				
		httpResponseScanner.close();
		
		return texto;
	}
	
	public static String encoder(File file) {
		String base64Image = "";
		
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}


}
