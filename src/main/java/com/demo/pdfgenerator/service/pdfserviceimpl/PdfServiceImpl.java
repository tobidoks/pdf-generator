package com.demo.pdfgenerator.service.pdfserviceimpl;

import com.demo.pdfgenerator.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
    private static final Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);

    @Value("${pdf.file.path}")
    private String pdfFilePath;

    @Override
    public ByteArrayInputStream convertPayslipToPdf(String htmlContent) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();

            String pdfFileName = generateUniqueFileName();
            savePdfToFile(outputStream.toByteArray(), pdfFileName);

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException | com.lowagie.text.DocumentException e) {
            log.error("Error converting Payslip to PDF. HTML content: {}", htmlContent, e);
            throw new RuntimeException("Error converting Payslip to PDF", e);
        }
    }

    private String generateUniqueFileName() {
        return "payslip-" + System.currentTimeMillis() + ".pdf";
    }

    private void savePdfToFile(byte[] pdfData, String fileName) throws IOException {
        String filePath = pdfFilePath + fileName;

        try (OutputStream os = Files.newOutputStream(Paths.get(filePath))) {
            os.write(pdfData);
        }
    }
}
