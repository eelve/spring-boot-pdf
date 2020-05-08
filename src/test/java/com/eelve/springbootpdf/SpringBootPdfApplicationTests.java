package com.eelve.springbootpdf;

import com.eelve.springbootpdf.service.Html2PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPdfApplicationTests {

	@Test
	public void html2pdf() throws Exception {
		Html2PdfService html2PdfService = new Html2PdfService();//未引入spring，手动实例化
		html2PdfService.excute("https://wkhtmltopdf.org");
	}
}
