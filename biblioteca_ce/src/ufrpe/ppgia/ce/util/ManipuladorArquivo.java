package ufrpe.ppgia.ce.util;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
public class ManipuladorArquivo {
 
    public static String leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "", conteudo = "";
        
        while (true) {
            if (linha != null) {
                conteudo += linha;
 
            } else
                break;
            
            linha = buffRead.readLine();
        }
        buffRead.close();
        
        return conteudo;
    }
 
    public static void escritor(String path, String conteudo) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        
        buffWrite.append(conteudo);
        buffWrite.close();
    }
 
}