package com.ecommerce.controller;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import com.ecommerce.entity.Usuario;
import com.ecommerce.repository.IUsuarioRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReportesController {
	
	@Autowired
	private IUsuarioRepository repoUser;
	@GetMapping("/report/cargar")
	public String cargaPagReportes(Model model, HttpSession session) {
		//enviamos los atributos a la página
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("lstUsuarios", repoUser.findAll());
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/reportes";
	}
	@Autowired DataSource dataSource;
	@Autowired private ResourceLoader resourceLoader;
	
	@GetMapping("/report/listado")
	public void generarPDFListado(HttpServletResponse response) {
			response.setHeader("Content-Disposition", "inline;");
			response.setContentType("application/pdf");
			System.out.println();
			try {
				String ru = resourceLoader.getResource("classpath:reportes/usuariosLista.jasper").getURI().getPath(); 				
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection()); 
			OutputStream outStream = response.getOutputStream(); 
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		}
	
	@GetMapping("report/grafico")
	public void generarPDFGrafico(HttpServletResponse response, @ModelAttribute Usuario usuario) {
		
			response.setHeader("Content-Disposition", "inline;");
			
			response.setContentType("application/pdf");
			//proceso de combinar: jasper+data:
			try {
				//obtener el recurso jasper:
				String ru = resourceLoader.getResource("classpath:reportes/ordenes.jasper").getURI().getPath(); 
				
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection()); 
			//genera archivo
			OutputStream outStream = response.getOutputStream(); 
			//permite exportar el archivo
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		}

}
