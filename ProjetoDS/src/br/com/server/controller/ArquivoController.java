package br.com.server.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.server.model.ArquivoModel;

@Controller
public class ArquivoController {

	@RequestMapping("/enviarArquivo")
	public String exibirForm() {
		return "form";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, Model model) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		ArquivoModel arquivo = new ArquivoModel(multipartFile);
		arquivo.processarArquivoOcorrenciaPalavras();
		
		model.addAttribute("pathArquivo", arquivo.getPathArquivoExcel());
		
		return "download";
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
    public HttpEntity<byte[]> download(HttpServletRequest request) throws IOException {
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String pathArquivo = multipartRequest.getParameter("pathArquivo");

        byte[] arquivo = Files.readAllBytes(Paths.get(pathArquivo));

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"resultado-processamento-"+pathArquivo);

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        return entity;
    }
	
}
