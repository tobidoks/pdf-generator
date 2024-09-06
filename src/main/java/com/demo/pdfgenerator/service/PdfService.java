package com.demo.pdfgenerator.service;

import com.lowagie.text.DocumentException;
import java.io.ByteArrayInputStream;

public interface PdfService {
    ByteArrayInputStream convertPayslipToPdf(String htmlContent) throws DocumentException;
}
