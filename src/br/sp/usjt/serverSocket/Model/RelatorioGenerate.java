package br.sp.usjt.serverSocket.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class RelatorioGenerate {

    String cabecalho =
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head>";

    public RelatorioGenerate(){
    }

    public Map<Integer, Integer> contador(){


            return null;
    }

    public File mount(String nomeRelatorio, Map<Integer, Integer> map){
        StringBuffer inputBuffer = new StringBuffer();

        String line;

        File result = null;

        inputBuffer.append(cabecalho);

        line = "<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "      window.onload = function() {\n" +
                "        var chart = new CanvasJS.Chart(\"chartContainer\", {\n" +
                "          title: {\n" +
                "            text: \"" + nomeRelatorio + "\"\n" +
                "          },\n" +
                "          data: [\n" +
                "            {\n" +
                "              type: \"column\",\n" +
                "              dataPoints: [ \n";

        inputBuffer.append(line);

        int cont = 1;

        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            if(cont < map.entrySet().size()) {
                inputBuffer.append("{ label: \"HTTP Code: " + pair.getKey() + "\", y: " + pair.getValue() + "},\n");
                cont++;
            }else{
                inputBuffer.append("{ label: \"HTTP Code: " + pair.getKey() + "\", y: " + pair.getValue() + "}\n");
            }
        }

        inputBuffer.append("]\n" +
                "            }\n" +
                "          ]\n" +
                "        });\n" +
                "        chart.render();\n" +
                "      };\n" +
                "    </script>");


        inputBuffer.append(" </head>\n" +
                "  <body>\n" +
                "    <div id=\"chartContainer\" style=\"height: 300px; width: 100%;\"></div>\n" +
                "  </body>\n" +
                "</html>\n");

        FileOutputStream fileOut = null;

        try {
            result = new File("Relatorios.html");

            fileOut = new FileOutputStream(result);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException ex){

        }

        return result;

    }
}