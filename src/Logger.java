import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Logger {


    String nombre_archivo;
    Path archivo;

    Logger(String nombre_archivo){
        this.nombre_archivo = nombre_archivo;
        archivo = Paths.get(nombre_archivo);

        try{
            PrintWriter writer = new PrintWriter(nombre_archivo, "UTF-8");
            writer.close();
        } catch (IOException e) {
            System.out.println("No pude crear archivo");
        }
    }

    void printStringToFile(String stoprint){
        List<String> linea = Arrays.asList(stoprint);
        try{
            Files.write(archivo, linea, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println("No pude escribir archivo");
        }
    }

    void printMatrixToFile(Matrix matrix){
        printStringToFile(matrix.toString());
    }



}
