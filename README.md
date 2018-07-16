# projetods

O projeto implementa o processamento de arquivos, extraindo o texto e contando a ocorrência das palavras.
São aceitos os tipos de arquivos pdf, doc/docx, além de imagens jpg e tif.
Ao final do processamento, retorna para download um arquivo Excel contendo a lista de palavras e frequencia no texto.

Para leitura de documentos Doc e Docx, assim como a geração do arquivo Excel,
foi feita a utilização de Apache POI, Java API para documentos Microsoft.
A leitura de arquivos PDF é feita utilizando Apache PDFBox.
Para a leitura de arquivos de imagem, é feita solicitação HTTP para a API Google Cloud Vision.
A API faz o reconhecimento do texto e retorna um JSON.





