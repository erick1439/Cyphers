import java.util.*;
import java.io.*;

public class RailFence
{
  public static void main(String [] args) throws Exception
  {
    // Makes sure the correct number of arguments is inserted.
    if (args.length < 2 || args.length > 2)
    {
      System.out.println("Proper syntax: java Vigenere <text.txt> <RailFence key number>");
      return;
    }

    // Statements that read the text file.
    StringBuilder text = new StringBuilder();
    Scanner textReader = new Scanner(new File(args[0]));
  
    while (textReader.hasNextLine())
      text.append(textReader.nextLine());

    textReader.close();

    // Statement that stores the key
    int key = Integer.parseInt(args[1]);

    // Cypher
    String plainText = text.toString();
    List<StringBuilder> list = new ArrayList<>();

    for (int i = 0; i < key; i++)
      list.add(new StringBuilder());

    if (key < 0)
    {
      System.out.println("Invalid Key");
      return;
    }

    else if (key == 0 || key == 1)
    {
      printOutput(1, plainText, plainText);
      return;
    }

    int i = 0, val = 1;
    boolean flag = true;

    while (i < plainText.length())
    {
      if (flag)
      {
        list.get(val - 1).append(plainText.charAt(i));
        val++;

        if (val == key)
          flag = false;
      }
      
      else
      {
        list.get(val - 1).append(plainText.charAt(i));
        val--;

        if (val == 1)
          flag = true;
      }

      i++;
    }

    StringBuilder result = new StringBuilder(plainText.length());

    for (int j = 0; j < key; j++)
      result.append(list.get(j).toString());

    printOutput(key, plainText, result.toString()); 
  }

  // Output: 
  public static void printOutput(int key, String plainText, String result)  
  {
    System.out.println("\nRail Fence Key: " +  key);

    System.out.println("\n\nPlaintext:\n");

    for (int j = 0; j < plainText.length(); j++)
    {
      System.out.print(plainText.charAt(j));

      if ((j + 1) % 80 == 0 && (j + 1) != 0)
        System.out.println();
    }

    System.out.println("\n\n\nCiphertext:\n");

    for (int j = 0; j < result.length(); j++)
    {
      System.out.print(result.charAt(j));

      if ((j + 1) % 80 == 0 && (j + 1) != 0)
        System.out.println();
    }

    System.out.println();
  }
}