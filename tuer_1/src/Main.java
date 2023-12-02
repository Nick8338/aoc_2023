import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static BufferedReader bfr;
    private static char[] zahl=new char[]{0,0};
    private static boolean zahl0Found;

    public static void main(String[] args) throws IOException{
        File file = new File("D:\\VS Studio Projekte\\Java\\AoC 2023\\tuer_1\\lib\\input_1.txt");
        bfr = new BufferedReader(new FileReader(file));
        if (file.exists()){
            System.out.println(findCalibrationValue());
        }
    }

    private static int findCalibrationValue() throws IOException{
        int calibrationValue = 0;
        String test = bfr.readLine();
        char[] string;
        while(test!=null){
            string = test.toCharArray();
            zahl[0]=0;
            zahl[1]=0;
            zahl0Found=false;
            for(int i=0; i<string.length; i++) {
                if(string[i]>47&&string[i]<58){
                    giveZahlValue(string[i]);
                }
                switch (string[i]) {
                    case 'o':   //one
                        if(isSubStrPossible(i, string, "one")){
                           giveZahlValue('1');
                        }
                        break;
                    case 't':   //two, three
                        if(isSubStrPossible(i, string, "two")){
                            giveZahlValue('2');
                        }
                        else if(isSubStrPossible(i, string, "three")){
                            giveZahlValue('3');
                        }
                        break;
                    case 'f':   //four, five
                        if(isSubStrPossible(i, string, "four")){
                            giveZahlValue('4');
                        }
                        else if(isSubStrPossible(i, string, "five")){
                            giveZahlValue('5');
                        }
                        break;
                    case 's':   //six, seven
                        if(isSubStrPossible(i, string, "six")){
                            giveZahlValue('6');
                        }
                        else if(isSubStrPossible(i, string, "seven")){
                            giveZahlValue('7');
                        }
                        break;
                    case 'e':   //eight
                        if(isSubStrPossible(i, string, "eight")){
                            giveZahlValue('8');
                        }
                        break;
                    case 'n':   //nine
                        if(isSubStrPossible(i, string, "nine")){
                            giveZahlValue('9');
                        }
                        break;
                }
            }
            calibrationValue = calibrationValue + Integer.parseInt(String.valueOf(zahl));
            test=bfr.readLine();
        }
        return calibrationValue;
    }

    private static boolean isSubStrPossible(int i, char[] string, String value){
        int valueLength=value.length();
        if (string.length-valueLength-i>=0 && String.valueOf(string).substring(i, i+valueLength).equals(value)){
            return true;
        }
        return false;
    }
    
    private static void giveZahlValue(char value){
        if(!zahl0Found){
            zahl[0]=value;
            zahl0Found=true;
        }
        zahl[1]=value;
    }
}
