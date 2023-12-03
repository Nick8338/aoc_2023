import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static BufferedReader bfr;
    private static File file;
    private static int[] maxKugeln = {12,13,14}; // = [rote kugeln, grüne k., blaue k.]
    private static int sumGameIds = 0;
    
    public static void main(String[] args) throws IOException {
        file = new File("D:\\VS Studio Projekte\\Java\\AoC 2023\\tuer_2\\lib\\input_1.txt");
        if (file.exists()){
            bfr = new BufferedReader(new FileReader(file));
            getPossibleGames();
            System.out.println(sumGameIds);
        }
    }

    private static void getPossibleGames() throws IOException{
        String string = bfr.readLine();
        String[] pulls;
        boolean possible;
        while(string!=null){
            pulls=string.substring(string.indexOf(":")).split(";"); //alle Runden nach ":" in Array
            possible = true;
            for (String pull : pulls) {
                if(!isPullPossible(pull)){
                    possible = false;
                }
            }
            if(possible){
                sumGameIds += Integer.parseInt(string.substring(string.indexOf(' ')+1, string.indexOf(':')));
            }
            string = bfr.readLine();
        } 
    }

    private static boolean isPullPossible(String pull){
        String[] divByOneColour;
        boolean possible = false;
        
        divByOneColour = pull.split(",");
        possible = true;
        for(String divColour: divByOneColour){
            String colour = divColour.substring(divColour.lastIndexOf(' ')+1);
            int test = divColour.lastIndexOf(' ');
            int count = Integer.parseInt(divColour.substring(divColour.indexOf(' ')+1, test));
            if(colour.equals("red")){
                if(maxKugeln[0]<count){
                    possible = false;
                }
            }
            else if (colour.equals("green")){
                if(maxKugeln[1]<count){
                    possible = false;
                }
            }
            else if (colour.equals("blue")){
                if(maxKugeln[2]<count){
                    possible = false;
                }
            }
        }
        return possible;
    }
}
