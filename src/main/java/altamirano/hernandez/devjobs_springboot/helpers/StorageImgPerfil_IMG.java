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
public class StorageImgPerfil_IMG {

    public Map<String, Object> almacenarImg(String nombreArchivo, MultipartFile archivo){
        Map<String, Object> json = new HashMap<>();

        //Comprobacion de llegada de archivo
        if (archivo.isEmpty()){
            json.put("error", "Archivo no subido de manera correcta");
            return json;
        }

        //Validacion de extensiones de archivo
        String extensionArchivoSubido = archivo.getContentType();
        List<String> extensionesValidas = Arrays.asList("image/jpeg", "image/png", "image/jpg");
        if (!extensionesValidas.contains(extensionArchivoSubido)){
            json.put("error", "Extension de archivo no valida. Solo se aceptan jpeg, png o jpg");
            return json;
        }

        //Almacenamiento de archivo
        try {
            String carpetaDestino = Paths.get("statics/uploads").toAbsolutePath().toString();
            File direccionDestino = new File(carpetaDestino);
            if (!direccionDestino.exists()){
                direccionDestino.mkdirs();
            }

            //Guardado de archivo
            String rutaDestinoArchivo = carpetaDestino + "/" + nombreArchivo;
            archivo.transferTo(new File(rutaDestinoArchivo));

            json.put("success", "Archivo guardado de manera correcta");
            return json;
        } catch (Exception e) {
            json.put("error", e.getMessage());
            return json;
        }
    }
}
