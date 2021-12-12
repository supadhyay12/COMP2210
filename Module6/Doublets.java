import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Shanti Upadhyay (spu0004@auburn.edu)
 */
public class Doublets implements WordLadderGame 
{

    TreeSet<String> lexicon;
    List<String> EmptyWordLadder = new ArrayList();

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) 
    {
        try 
        {
            
            lexicon = new TreeSet<String>();
            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) 
        {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }



    public int getHammingDistance(String str1, String str2)
    {
      if (str1.length() != str2.length())
      {
         return -1;
      }
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      int distance = 0;
      for (int i = 0; i < str1.length(); i++)
      {
         if (str1.charAt(i) != str2.charAt(i))
         {
            distance++;
         }
      }
      return distance;
    }
    
    public List<String> getMinLadder(String start, String finish) 
    {
      start = start.toLowerCase();
      finish = finish.toLowerCase();
      ArrayList<String> backwards = new ArrayList<String>();
      List<String> minLadder = new ArrayList<String>();
      if (start.equals(finish)) 
      {
         minLadder.add(start);
         return minLadder;
      }
      if (getHammingDistance(start, finish) == -1) 
      {
         return EmptyWordLadder;
      }
      if (isWord(start) && isWord(finish)) 
      {
         backwards = bfs(start, finish);
      }
      if (backwards.isEmpty()) 
      {
         return EmptyWordLadder;
      }
      for (int i = backwards.size() - 1; i >= 0; i--) 
      {
         minLadder.add(backwards.get(i));
      }
      return minLadder;
      }
      
    private ArrayList<String> bfs(String start, String finish) 
      {
         Deque<Node> queue = new ArrayDeque<Node>();
         HashSet<String> visited = new HashSet<String>();
         ArrayList<String> backwards = new ArrayList<String>();
         visited.add(start);
         queue.addLast(new Node(start, null));
         Node finishNode = new Node(finish, null);
         outerloop:
         while (!queue.isEmpty()) 
         {
            Node n = queue.removeFirst();
            String word = n.word;
            List<String> neighbors = getNeighbors(word);
            for (String neighbor : neighbors) 
            {
               if (!visited.contains(neighbor)) 
               {
                  visited.add(neighbor);
                  queue.addLast(new Node(neighbor, n));
                  if (neighbor.equals(finish)) 
                  {
                     finishNode.predecessor = n;
                     break outerloop;
                  }
               }
            }
         }
         if (finishNode.predecessor == null) 
         {
            return backwards;
         }
         Node m = finishNode;
         while (m != null) 
         {
            backwards.add(m.word);
            m = m.predecessor;
         }
         return backwards;
      }


   public List<String> getNeighbors(String word) 
   {
      List<String> neighbors = new ArrayList<String>();
      Iterator<String> itr = lexicon.iterator();
      while (itr.hasNext()) 
      {
         String word2 = itr.next();
         if (getHammingDistance(word, word2) == 1) 
         {
            neighbors.add(word2);
         }
      }
      return neighbors;
   }

   public int getWordCount() 
   {
      return lexicon.size();
   }

   public boolean isWord(String str) 
   {
      str = str.toLowerCase();
      if (lexicon.contains(str)) 
      {
         return true;
      }
      return false;
   }
   public boolean isWordLadder(List<String> sequence) 
   {
      String word1 = "";
      String word2 = "";
      if (sequence.isEmpty()) 
      {
         return false;
      }
      for (int i = 0; i < sequence.size() - 1; i ++) 
      {
         word1 = sequence.get(i);
         word2 = sequence.get(i + 1);
         if (!isWord(word1) || !isWord(word2)) 
         {
            return false;
         }
         if (getHammingDistance(word1, word2) != 1) 
         {
            return false;
         }
      }
   return true;
   }

   private class Node 
   {
      String word;
      Node predecessor;
      public Node(String s, Node pred) 
      {
         word = s;
         predecessor = pred;
      }
    }         

}
