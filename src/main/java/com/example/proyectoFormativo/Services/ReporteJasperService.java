package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.DatoEstadisticoDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteJasperService {

    // Podrías inyectar tus repositorios acá
    public byte[] generarReporteEstadisticoPdf( List<DatoEstadisticoDto> lista, String nombre, Map<String, Object> params) {
        try {


            // 2. Cargar la plantilla .jrxml desde resources
            InputStream reporteStream =
                    getClass().getResourceAsStream(nombre);
            if (reporteStream == null) {
                throw new RuntimeException("No se encontró el reporte reporte_estadistico.jrxml");
            }

            // 3. Compilar el .jrxml a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);

            // 4. DataSource: usa la lista de beans
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            // 6. Llenar el reporte (combinar plantilla + datos + parámetros)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // 7. Exportar a PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (JRException e) {
            throw new RuntimeException("Error generando el reporte Jasper", e);
        }
    }
}
