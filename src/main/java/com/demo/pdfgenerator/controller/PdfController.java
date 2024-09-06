package com.demo.pdfgenerator.controller;

import com.demo.pdfgenerator.service.PdfService;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/v1/payslip")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public void generatePayslipPdf(HttpServletResponse response) throws IOException, DocumentException {
        Resource templateResource = new ClassPathResource("templates/payslip.html");
        String htmlContent = Files.readString(templateResource.getFile().toPath());

        ByteArrayInputStream byteArrayInputStream = pdfService.convertPayslipToPdf(htmlContent);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=payslip.pdf");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}
