package com.example.VWorks.Controller;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment; // Import TextAlignment directly


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/PDF")
public class PDF {

    @GetMapping("create")
    public void createPDF(){
        String outputPath = "output.pdf";

        try {

            Color myColor = new DeviceRgb(255, 0, 0);
            PdfWriter writer = new PdfWriter(outputPath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument, PageSize.A4);


            Paragraph p = new Paragraph();
            p.add(new Text("Developed By : "));
            p.add(new Text("Mr.XXXXX").setFontColor(myColor));
            document.add(p);

            document.add(p);
            document.close();

            System.out.println("PDF created successfully!");

            boolean fileExists = new java.io.File("G:/Java/VWorks/output.pdf").exists();
            System.out.println(fileExists);
            if (fileExists) {
                openPDF();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void openPDF() {
        try {
            String filePath = "G:/Java/VWorks/output.pdf"; // Update with your file path

            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", "start", filePath);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/downloadPDF")
    public ResponseEntity<byte[]> downloadPDF() throws IOException {
        String filePath = "G:/Java/VWorks/output.pdf"; // Update with the actual file path

        Path pdfPath = Path.of(filePath);

        if (!Files.exists(pdfPath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = Files.readAllBytes(pdfPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf"); // Change the filename as needed

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
