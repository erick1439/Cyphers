import java.util.*;
import java.io.*;

public class Vigenere 
{
  public static void main(String [] args) throws Exception
  {
    // Makes sure the correct number of arguments is inserted.
    if (args.length < 2 || args.length > 2)
    {
      System.out.println("Proper syntax: java Vigenere <key.txt> <text.txt>");
      return;
    }

    // Statements that read the key file.
    StringBuilder key = new StringBuilder();
    Scanner textReader = new Scanner(new File(args[0]));
  
    while (textReader.hasNextLine())
      key.append(textReader.nextLine());

      textReader.close();
  
    // Statements that read the plain text file.
    StringBuilder plainText = new StringBuilder();
    Scanner keyReader = new Scanner(new File(args[1]));

    while (keyReader.hasNextLine())
      plainText.append(keyReader.nextLine());

    keyReader.close();

    // Statements that format the key and the plain text.
    String keyFormated = key.toString().toLowerCase().replaceAll("[^a-z]", "").replaceAll(" ", "").replaceAll("\r\n", "");
    String plainTextFormated = plainText.toString().toLowerCase().replaceAll("[^a-z]", "").replaceAll(" ", "").replaceAll("\r\n", "");

    // Padding
    String keyOutput = null;

    if (plainTextFormated.length() < 512)
    {
      // Plain-text padding
      int len = plainTextFormated.length();
      StringBuilder textPadding = new StringBuilder(plainTextFormated);

      while (len < 512)
      {
        textPadding.append('x');
        len++;
      }

      plainTextFormated = textPadding.toString();

      // Key Padding 
      int i = 0, overflow = 0;
      keyOutput = keyFormated;
      len = overflow = keyFormated.length();
      StringBuilder keyPadding = new StringBuilder(keyFormated);

      while (len < 512)
      {
        if (i >= overflow)
          i = 0;

        keyPadding.append(keyFormated.charAt(i));
        len++;
        i++;
      }

      keyFormated = keyPadding.toString();
    }

    // Cypher Operation
    StringBuilder cypher = new StringBuilder();

    for (int i = 0; i < plainTextFormated.length(); i++)
    {
      char c = (char)((((plainTextFormated.charAt(i) - 97) + (keyFormated.charAt(i) - 97)) % 26) + 97);
      cypher.append(c);
    }

    String result = cypher.toString();

    // Output
    System.out.println("\n\nVigenere Key:\n");

    for (int i = 0; i < keyOutput.length(); i++)
    {
      System.out.print(keyOutput.charAt(i));

      if ((i + 1) % 80 == 0 && (i + 1) != 0)
        System.out.println();
    }

    System.out.println("\n\n\nPlaintext:\n");

    for (int i = 0; i < plainTextFormated.length(); i++)
    {
      System.out.print(plainTextFormated.charAt(i));

      if ((i + 1) % 80 == 0 && (i + 1) != 0)
        System.out.println();
    }

    System.out.println("\n\n\nCiphertext:\n");

    for (int i = 0; i < result.length(); i++)
    {
      System.out.print(result.charAt(i));

      if ((i + 1) % 80 == 0 && (i + 1) != 0)
        System.out.println();
    }

    System.out.println();
  }
}