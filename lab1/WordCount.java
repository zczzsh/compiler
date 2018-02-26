
import java.util.*;

/**
 * A class for the WordCount data structure.
 * A WordCount object is a map which pairs a word (string)
 * with a list of information (Info)
 */
public class WordCount {

	private Map<String,Vector<Info>> wordMap; 
	/**
	 * Builds an empty WordCount
	 */
	public WordCount() {
       this.wordMap = new TreeMap<String,Vector<Info>>(); 
	}
	
	/**
	 * Adds the given 'info' in the list of
	 * Infos of the given word 'word'
	 */
	public void add(String word, Info info) {
	   if(this.wordMap.containsKey(word))
	   {
		   this.wordMap.get(word).addElement(info);   
	   }
	   else
	   {
		  Vector<Info> wordVector= new Vector<Info>();
		  wordVector.addElement(info);
		  this.wordMap.put(word,wordVector);
	   }
	}
	
	/**
	 * Returns an iterator over the informations of
	 * the given word 'word'. If 'word' has no information
	 * returns null
	 */
	public Iterator<Info> getListIterator(String word) {
		if (this.wordMap.containsKey(word)) 
		{
            return this.wordMap.get(word).iterator();
		} 
		else return null;
	}
	
	/**
	 * Displays the WordCount on System.out
	 */
	public void display() {
	   for(String word: this.wordMap.keySet())
	   {
		   System.out.printf("%14s",word+'('+this.wordMap.get(word).size()+""+"):");
		   for(int i=0;i<this.wordMap.get(word).size();i++)
		   {
			   System.out.printf(this.wordMap.get(word).get(i).toString());
		   }
		   System.out.println();
	   }
	}

}