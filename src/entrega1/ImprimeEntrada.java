package entrega1;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(
	     urlPatterns={"/ImprimeEntrada"}
	     )

public class ImprimeEntrada extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// Image properties
	int qr_image_height = 200;
	int qr_image_width = 200;
	String IMAGE_FORMAT = "jpg";

	public ImprimeEntrada() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IMG_PATH = this.getServletContext().getRealPath("/Imagenes/qrcode.jpg");
		// Se crea el documento
		Document documento = new Document();
		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
		
		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		try {
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// Se abre el documento
		documento.open();
		
		// Se agregan datos al PDF
		try {
			documento.add(new Paragraph("Nombre: " + request.getParameter("nombre")));
			documento.add(new Paragraph("Nombre: " + request.getParameter("apellido")));
			documento.add(new Paragraph("Nombre: " + request.getParameter("email")));
			documento.add(new Paragraph("Nombre: " + request.getParameter("dni")));
			Image foto;
			switch(request.getParameter("opcion")) {
				case "Laminga":
					foto = Image.getInstance(this.getServletContext().getRealPath("/Imagenes/laminga.jpg"));
					foto.scaleToFit(200, 200);
					foto.setAlignment(Chunk.ALIGN_MIDDLE);
					documento.add(foto);
					break;
				case "Las Taradas":
					foto = Image.getInstance(this.getServletContext().getRealPath("/Imagenes/lastaradas.jpg"));
					foto.scaleToFit(200, 200);
					foto.setAlignment(Chunk.ALIGN_MIDDLE);
					documento.add(foto);
					break;
				case "Los Musicletas":
					foto = Image.getInstance(this.getServletContext().getRealPath("/Imagenes/losmusicletas.jpg"));
					foto.scaleToFit(200, 200);
					foto.setAlignment(Chunk.ALIGN_MIDDLE);
					documento.add(foto);
					break;
				case "Okupas":
					foto = Image.getInstance(this.getServletContext().getRealPath("/Imagenes/okupas.jpg"));
					foto.scaleToFit(200, 200);
					foto.setAlignment(Chunk.ALIGN_MIDDLE);
					documento.add(foto);
					break;
			}

			// Mensaje para el codigo QR
			String data = "Entrada para " + request.getParameter("nombre") + " " + 
					request.getParameter("apellido") + " " + 
					request.getParameter("dni") + " " + 
					"Felicitaciones!! Ud. ha ganado el premio: ";

			// Se crea el codigo QR
	        try {
	        	File qrCode = new File(IMG_PATH);
	        	this.generateQR(qrCode, data);
	        	System.out.println("Texto del codigo QR: " + this.decoder(qrCode));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        foto = Image.getInstance(IMG_PATH);
			foto.scaleToFit(200, 200);
			foto.setAlignment(Chunk.ALIGN_MIDDLE);
			documento.add(foto);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// Se cierra el documento
		documento.close();
		//response.setContentType("application/pdf");
		//response.setContentLength(bytes.length);
		//response.setHeader( "Content-disposition", "inline; filename=fichero.pdf");
		//response.getOutputStream().write(bytes);
	}
	
	// Genera el codigo QR
	public File generateQR(File qrCode, String data) throws Exception {
		BitMatrix matrix = null;
		Writer writer = new QRCodeWriter();

		try {

			matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);

		} catch (WriterException e) {
			e.printStackTrace(System.err);
		}

		// Create buffered image to draw to
		BufferedImage image = new BufferedImage(qr_image_width, qr_image_height, BufferedImage.TYPE_INT_RGB);

		// Iterate through the matrix and draw the pixels to the image
		for (int y = 0; y < qr_image_height; y++) {
			for (int x = 0; x < qr_image_width; x++) {
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
			}
		}

		// Write the image to a file
		ImageIO.write(image, IMAGE_FORMAT, qrCode);
		return (qrCode);
	}
	
	// Decodifica el codigo QR para poder verlo en la consola
	public String decoder(File file) throws Exception {
		 
        FileInputStream inputStream = new FileInputStream(file);
 
        BufferedImage image = ImageIO.read(inputStream);
 
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
 
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
 
        // decode the barcode
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return new String(result.getText());
    }

}