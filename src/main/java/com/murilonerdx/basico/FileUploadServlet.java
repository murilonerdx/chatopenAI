package com.murilonerdx.basico;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verifica se o conteúdo do request é um formulário com upload de arquivo
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Configura o armazenamento temporário dos arquivos enviados
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(uploadDir);

        // Cria o parser para ler os itens do formulário
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Lê os itens do formulário
            List<FileItem> items = upload.parseRequest(request);
            // Exibe a página de sucesso
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>Ar");
            // Processa cada item do formulário
            for (FileItem item : items) {
                // Verifica se o item é um campo de formulário ou um arquivo
                if (item.isFormField()) {
                    // Processa o campo de formulário
                    processFormField(item);
                } else {
                    // Processa o arquivo enviado
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao processar o formulário de upload.", e);
        }
    }

        // Processa o campo de formulário
        private void processFormField(FileItem item){
            String name = item.getFieldName();
            String value = item.getString();
            // Aqui você pode processar o campo de formulário, como adicionar os dados a um banco de dados, etc.
            System.out.println(name + " = " + value);
        }

        // Processa o arquivo enviado
        private void processUploadedFile (FileItem item) throws Exception {
            // Obtém o nome e o tamanho do arquivo
            String fileName = item.getName();
            long fileSize = item.getSize();

            // Verifica se o arquivo foi enviado
            if (fileName == null || fileName.trim().isEmpty() || fileSize == 0) {
                return;
            }

            // Gera um nome único para o arquivo
            fileName = System.currentTimeMillis() + "_" + fileName;

            // Salva o arquivo no diretório de uploads
            File uploadedFile = new File(UPLOAD_DIRECTORY + File.separator + fileName);
            item.write(uploadedFile);

            // Aqui você pode processar o arquivo enviado, como adicionar os dados a um banco de dados, etc.
            System.out.println(fileName + " (" + fileSize + " bytes)");
        }
    }