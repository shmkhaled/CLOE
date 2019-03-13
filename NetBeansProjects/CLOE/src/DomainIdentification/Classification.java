/*
 @author Shimaa Ibrahim
 */

package DomainIdentification;

import java.util.*;
import jdk.nashorn.internal.parser.TokenType;
/**
 *
 * @author shimaa
 */
public class Classification {
    public static void Classification_text_to_Domain_training(ArrayList<String> words_1, ArrayList<String> words_2, ArrayList<String> words_3)
    {
        double textToD1[] = Text2Domain(words_1, words_2, words_3, 1);
        double textToD2[] = Text2Domain(words_1, words_2, words_3, 2);
        double textToD3[] = Text2Domain(words_1, words_2, words_3, 3);
        //for(int i = 0; i< 3;i++)
            //System.out.println("Text "+ textToD1[i] +" "+ textToD2[i] +" "+ textToD3[i]);
        double C_Text1 = Membership_Classification(textToD1);
        double C_Text2 = Membership_Classification(textToD2);
        double C_Text3 = Membership_Classification(textToD3);
        //System.out.println("Membership of Domain 1 is "+ C_Text1);
        //System.out.println("Membership of Domain 2 is "+ C_Text2);
        //System.out.println("Membership of Domain 3 is "+ C_Text3);
        
        double Max_D = Double.max(C_Text1, C_Text2);
        Max_D = Double.max(Max_D, C_Text3);
        double Min_D = Double.min(C_Text1, C_Text2);
        Min_D = Double.min(Min_D, C_Text3);
        double Classification_D1 = Normalize(C_Text1, Max_D, Min_D);
        double Classification_D2 = Normalize(C_Text2, Max_D, Min_D);
        double Classification_D3 = Normalize(C_Text3, Max_D, Min_D);
        //System.out.println("After normalization");
        //System.out.println("Membership of Domain 1 is "+ Classification_D1);
        //System.out.println("Membership of Domain 2 is "+ Classification_D2);
        //System.out.println("Membership of Domain 3 is "+ Classification_D3);

    }
    public static int freq (ArrayList<String> words, String w){
       int count = 0;
        for (int i=0;i<words.size();i++) {
            
            if(words.get(i).equals(w))
            {                    
                count ++;
                   
             }
        }
        return count; 
   }
   public static double Membership_Classification (double [] w_mem_d)
   {
       double C_W = 0;
       for (int i=0;i<w_mem_d.length;i++)
       {
           C_W += w_mem_d[i];
       }
       return C_W;
   }
   public static double Normalize (double x, double max, double min)
   {
       x = (x - min)/(max - min);
       return x;
   }
   public static double[] columnSum(double [][] array){
        double temp[] = new double[array[0].length];

        for (int i = 0; i < array[0].length; i++){
            double sum = 0;

            for (int j = 0; j < array.length; j++){
                sum += array[j][i];

            }
            temp[i] = Math.round(sum*100.0)/100.0;
            //System.out.println("Domain: " + i + " is: "+sum);
        }
            return temp;
    }
   
   public static double[] Text2Domain (ArrayList<String> words_1, ArrayList<String> words_2, ArrayList<String> words_3, int domainNumber)
   {
       //remove repeated values
       ArrayList<String> words_unique = new ArrayList<String>();
       Set<String> w_d;
       if(domainNumber == 1)
           w_d = new LinkedHashSet<String>(words_1);
       else if (domainNumber == 2)
           w_d = new LinkedHashSet<String>(words_2);
       else 
           w_d = new LinkedHashSet<String>(words_3);
       words_unique.addAll(w_d);
       //System.out.println(words_unique); 
       double words_membership_d[][] = new double[words_unique.size()][3];
        for (int i=0;i<words_unique.size();i++)
        {
            words_membership_d[i][0] = Math.round((double)(freq(words_1, words_unique.get(i))) / (freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
            words_membership_d[i][1] = Math.round((double)(freq(words_2, words_unique.get(i))) / (freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
            words_membership_d[i][2] = Math.round((double)(freq(words_3, words_unique.get(i))) / (freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
        }
        //System.out.println("#########################################");
        //System.out.println("Words Membership for domain "+ domainNumber + '\n' +Arrays.deepToString(words_membership_d).replace("], ", "]\n"));
        double text_D[] = columnSum(words_membership_d);
        return text_D;
   }
   
   public static String Classification_text_to_Domain_testing(ArrayList<String> words_1, ArrayList<String> words_2, ArrayList<String> words_3, ArrayList<String> words_test)
   {
       double textToD1[] = Text2Domain(words_1, words_2, words_3, 1);
       double textToD2[] = Text2Domain(words_1, words_2, words_3, 2);
       double textToD3[] = Text2Domain(words_1, words_2, words_3, 3);
       
       ArrayList<String> words_unique = new ArrayList<String>();
       Set<String> w_d = new LinkedHashSet<String>(words_test);
       words_unique.addAll(w_d);
       //System.out.println(words_unique); 
       double words_membership_d[][] = new double[words_unique.size()][3];
        for (int i=0;i<words_unique.size();i++)
        {
            words_membership_d[i][0] = Math.round((double)(freq(words_1, words_unique.get(i))) / (freq(words_test, words_unique.get(i)) + freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
            words_membership_d[i][1] = Math.round((double)(freq(words_2, words_unique.get(i))) / (freq(words_test, words_unique.get(i)) + freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
            words_membership_d[i][2] = Math.round((double)(freq(words_3, words_unique.get(i))) / (freq(words_test, words_unique.get(i)) + freq(words_1, words_unique.get(i)) + freq(words_2, words_unique.get(i)) + freq(words_3, words_unique.get(i)))*100.0)/100.0;
        }
        //System.out.println("#########################################");
        //System.out.println("Testing" + '\n' +Arrays.deepToString(words_membership_d).replace("], ", "]\n"));
        double text_D[] = columnSum(words_membership_d);
        for(int i = 0; i< text_D.length ;i++)
            System.out.println("Classification for the tested text for domain " + i + " before normallization "+text_D[i]);
        double Max_domainMembership = Double.max(text_D[0], text_D[1]);
        Max_domainMembership = Double.max(Max_domainMembership, text_D[2]);
        double Min_domainMembership = Double.min(text_D[0], text_D[1]);
        Min_domainMembership = Double.min(Min_domainMembership, text_D[2]);
        double classificationForDomain1 = Normalize(text_D[0], Max_domainMembership, Min_domainMembership);
        System.out.println("Classification for the tested text for domain 1 after normallization "+classificationForDomain1);
        double classificationForDomain2 = Normalize(text_D[1], Max_domainMembership, Min_domainMembership);
        System.out.println("Classification for the tested text for domain 2 after normallization "+classificationForDomain2);
        double classificationForDomain3 = Normalize(text_D[2], Max_domainMembership, Min_domainMembership);
        System.out.println("Classification for the tested text for domain 3 after normallization "+classificationForDomain3);
        
        double dominantClassification = Double.max(classificationForDomain1, classificationForDomain2);
        dominantClassification = Double.max(dominantClassification, classificationForDomain3);
        
        if(dominantClassification == classificationForDomain1)
            return "Tourism";
        else if(dominantClassification == classificationForDomain2)
            return "Economy";
        else if(dominantClassification == classificationForDomain3)
            return "Information Technology";
        else
            return "Undefined domain";
   }
}
