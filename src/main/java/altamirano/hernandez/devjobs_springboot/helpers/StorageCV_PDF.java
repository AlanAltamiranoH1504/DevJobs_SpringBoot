package altamirano.hernandez.devjobs_springboot.helpers;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageCV_PDF {

    public Map<String, Object> almacenarPDF(String nombreArchivo, MultipartFile archivo){
        Map<String, Object> json = new HashMap<>();

        //Comprobacion de llegada de archivo
        if (archivo.isEmpty()) {
            json.put("error", "Archivo no subido de manera correcta");
            return json;
        }

        //Validacion de extensiones
        String extensionArchivoSubido = archivo.getContentType();
        List<String> extensionesPermitidas = Arrays.asList("application/pdf");
        if (!extensionesPermitidas.contains(extensionArchivoSubido)){
            json.put("error", "Extension de archivo valido. Solo se aceptan PDFs");
            return json;
        }

        //Almacenamiento de archivo
        try {
            String carpetaDestino = Paths.get("statics/uploads/cvs_interesados").toAbsolutePath().toString();
            File direccionDestino = new File(carpetaDestino);
            if (!direccionDestino.exists()){
                direccionDestino.mkdirs();
            }

            //Guardado de archivo
            String rutaDestinoArchivo = carpetaDestino + "/" + nombreArchivo;
            archivo.transferTo(new File(rutaDestinoArchivo));

            json.put("success", "Archivo PDF guardado correctamente");
            return json;
        } catch (Exception e) {
            json.put("error", e.getMessage());
            return json;
        }
    }
}
