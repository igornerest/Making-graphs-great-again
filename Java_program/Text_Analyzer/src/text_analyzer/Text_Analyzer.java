package text_analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;

public class Text_Analyzer {

    public static void main(String[] args) throws Exception {
        String aux1 ="" , aux2=""; int i =0;
        try {
            PrintWriter p_edges = new PrintWriter("first_edges_tables.txt");

            p_edges.println("Source,Target,Weight");

            FileReader file = new FileReader("src/text_analyzer/Crippled_America_fulltext.txt");
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();

            while (line != null) {
                for (i = 0; i < line.length(); i++) {
                    if (i!=line.length()-1 && !java.util.Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ')', '(', '{', '}', '[', ']', 'º', 'ª', '|', ',', ' ', '-', '\n', '.', '?', '!', '-', ';', '/', ':', '$', '&', '%', '#', '@', '£', '¢', '*', '=', '+', '§').contains(line.charAt(i))){ 
                        if (line.charAt(i)!='�')
                            aux1 = aux1 + line.charAt(i);
                    }else if (!java.util.Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ')', '(', '{', '}', '[', ']', 'º', 'ª', '|', ',', ' ', '-', '\n', '.', '?', '!', '-', ';', '/', ':', '$', '&', '%', '#', '@', '£', '¢', '*', '=', '+', '§').contains(line.charAt(i - 1))) {
                        if (i==line.length()-1 && !java.util.Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ')', '(', '{', '}', '[', ']', 'º', 'ª', '|', ',', ' ', '-', '\n', '.', '?', '!', '-', ';', '/', ':', '$', '&', '%', '#', '@', '£', '¢', '*', '=', '+', '§').contains(line.charAt(i)))
                            aux1=aux1+line.charAt(i);
                            
                        if (!aux2.equals("") && !aux1.equals(""))
                            p_edges.println((aux2+","+aux1+",1\n").toLowerCase());
                        
                        aux2 = aux1;
                        aux1 = ""; 
                    }
                }
                line = reader.readLine();
                aux2="";
                aux1="";  
            }
            p_edges.close();

        } catch (IOException e) {
            out.println("ERROR!");
        }

    }

}
