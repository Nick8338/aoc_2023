import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static BufferedReader bfr;
    private static File file;
    private static int[] maxKugeln = {12,13,14}; // = [rote kugeln, grüne k., blaue k.]
    private static int[] minKugelnPerRound = {0,0,0};
    private static int sumGameIds = 0;
    private static int powerOfBalls = 0;
    
    public static void main(String[] args) throws IOException {
        file = new File("D:\\VS Studio Projekte\\Java\\AoC 2023\\tuer_2\\lib\\input_1.txt");
        if (file.exists()){
            bfr = new BufferedReader(new FileReader(file));
            getPossibleGames();
            System.out.println("Summe der GameIDs: "+ sumGameIds);
            System.out.println("Power der Balls: "+ powerOfBalls );
        }
    }

    private static void getPossibleGames() throws IOException{
        String string = bfr.readLine();
        String[] pulls;
        boolean possible;
        while(string!=null){
            pulls=string.substring(string.indexOf(":")).split(";"); //alle Runden nach ":" in Array
            possible = true;
            for (int i=0; i<minKugelnPerRound.length; i++){
                minKugelnPerRound[i]=0;
            }
            for (String pull : pulls) {
                if(!isPullPossible(pull)){
                    possible = false;
                }
            }
            if(possible){
                sumGameIds += Integer.parseInt(string.substring(string.indexOf(' ')+1, string.indexOf(':')));
            }
            powerOfBalls += minKugelnPerRound[0]*minKugelnPerRound[1]*minKugelnPerRound[2];
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
                if(minKugelnPerRound[0]<count){
                    minKugelnPerRound[0] = count;
                }
            }
            else if (colour.equals("green")){
                if(maxKugeln[1]<count){
                    possible = false;
                }
                if(minKugelnPerRound[1]<count){
                    minKugelnPerRound[1] = count;
                }
            }
            else if (colour.equals("blue")){
                if(maxKugeln[2]<count){
                    possible = false;
                }
                if(minKugelnPerRound[2]<count){
                    minKugelnPerRound[2] = count;
                }
            }
        }
        return possible;
    }
}
