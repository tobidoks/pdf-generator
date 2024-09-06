 PDF generation project:  PDF Generation Service This repository contains a Spring Boot-based application that generates PDF documents (e.g., payslips) from HTML content using the Flying Saucer PDF library.
 
Features
HTML to PDF Conversion: Converts HTML templates into PDF documents.
Lombok Integration: Simplifies code by reducing boilerplate code for getter, setter, and constructors.
File Saving: Saves the generated PDFs to a predefined directory.
REST API: Provides an API endpoint to trigger PDF generation.
Cross-Origin Support: Cross-Origin Resource Sharing (CORS) enabled to allow requests from different domains

Project Structure
PdfService: Interface defining methods for converting HTML content into PDF.
PdfServiceImpl: Implements the PdfService and handles the core logic of PDF creation, file storage, and error handling.
PdfController: REST controller that provides an API endpoint to generate PDF files and stream them as downloadable files.

Dependencies
Spring Boot (Thymeleaf, Web)
Flying Saucer PDF (openpdf)
Lombok
SLF4J (Logging)

Getting Started
Prerequisites
Java 11
Maven

Setup
Clone the repository:
bash
git clone https://github.com/tobidoks/pdf-generator

Navigate to the project directory:
bash
cd pdf-generator

Configuration
Thymeleaf Configuration
The application uses Thymeleaf as the template engine. The configuration for Thymeleaf is as follows:

application.properties
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML

These settings ensure that HTML templates located in the templates/ directory are processed with UTF-8 encoding, and caching is disabled during development for quick changes.

PDF Save File Path
Define where the generated PDFs will be saved by setting the file path in application.properties:

application.properties
pdf.file.path=C://Users//HP//Desktop//generated-pdfs//
This property allows you to specify a custom directory for storing the generated PDFs.

Running the Application
Run the Spring Boot application:

bash
mvn spring-boot:run

Use the following endpoint to generate a payslip PDF:

POST /api/v1/payslip/generate
The generated PDF will be downloaded as an attachment.

Sample Request
bash
curl -X POST http://localhost:8080/api/v1/payslip/generate
