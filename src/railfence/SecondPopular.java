/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railfence;

import java.util.Arrays;

/**
 *
 * @author minht
 */
public class SecondPopular 
{
    public static String SecondPopular(String inputString)
    {
        if(inputString.length() < 2)
        {
            return "NotFound";
        }

        String result = "";
        //create count array with default value is 0
        int couter[] = new int[256];
        Arrays.fill(couter, 0);
        
        //check all character in string
        for(int i = 0; i < inputString.length(); i++)
        {
            char c = inputString.charAt(i);

            couter[(int)c]++;
        }
        
        int first = 0, second = 0;

        for(int i = 0; i < 256; i++)
        {
            if(couter[i] > 0)
            {
                if(couter[i] > couter[first])
                {
                    second = first;
                    first = i;
                }
                else
                {
                    if(couter[i] != couter[first] && couter[i] > couter[second])
                    {
                        second = i;
                    }
                }
            }
        }

        if(couter[second] != 0)
        {
            result = String.valueOf((char)second) + String.valueOf(couter[second]);
        }
        else
        {
            result = "NotFound";
        }
       
        return result;
    }
}
