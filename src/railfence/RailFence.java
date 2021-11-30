/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railfence;

/**
 *
 * @author minht
 */
public class RailFence {
    public static String Encryption(String plainText, int key)
    {
        StringBuilder result = new StringBuilder();
        
        int routing = 2 * key - 2;
        
        for (int i = 0; i < key; i++) 
        {
            for (int j = 0; j * routing + i < plainText.length(); j++) 
            {
                if(i == 0 || i == key - 1)
                {
                    result.append(plainText.charAt(j * routing + i));
                }
                else
                {
                    result.append(plainText.charAt(j * routing + i));
                                               
                    if((j + 1) * routing - i < plainText.length())
                    {
                        result.append(plainText.charAt((j + 1) * routing - i));
                    }
                }
            }
        }   
        
        return result.toString();
    }
    
    public static String Decryption(String cipherText, int key)
    {
        StringBuilder result = new StringBuilder();
        
        int lengthOfCipher = cipherText.length();
        
        int routing = 2 * key - 2;
        
        int routed = lengthOfCipher / routing;
        
        int surplus = lengthOfCipher % routing;
        
        int lengthSubString[] = new int[key];
        
        for (int i = 0; i < key; i++) 
        {
            if(i == 0 || i == key -1)
            {
                lengthSubString[i] = routed;
            }
            else
            {
                lengthSubString[i] = 2 * routed;
            }
        }
        
        boolean upToDown = true;
        
        int indexForAddSubplus = 0;
        
        for (int i = 0; i < surplus; i++) 
        {
           if(upToDown)
           {
               lengthSubString[indexForAddSubplus++]++;
               
               if(indexForAddSubplus == key)
               {
                   upToDown = false;
                   indexForAddSubplus -= 2;    
               }
           }
           else
           {
               lengthSubString[indexForAddSubplus--]++;;
           }
        }
        
        String splitString[] = new String[key];
        
        int indexSubString = 0;
        
        for (int i = 0; i < key; i++) 
        {
            splitString[i] = cipherText.substring(indexSubString, indexSubString + lengthSubString[i]);
            
            indexSubString += lengthSubString[i];
        }
        
        upToDown = true;
        
        int indexForAppend = 0;
        
        for(int i = 0; i < lengthOfCipher; i++)
        {
            result.append(splitString[indexForAppend].charAt(0));
            
            splitString[indexForAppend] = splitString[indexForAppend].substring(1);
            
            if(upToDown)
            {                 
                indexForAppend++;
                
                if(indexForAppend == key)
                {
                    upToDown = false;
                    
                    indexForAppend -= 2;
                }
            }
            else
            {       
                indexForAppend--;
                
                if(indexForAppend == -1)
                {
                    upToDown = true;
                    
                    indexForAppend = 1;
                }
            }
        }
        
        return result.toString();
    }
}
