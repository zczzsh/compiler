
/**
 * A class for Word
 */
public class Word {

	private String word;
	private Info info;
	/**
	 * Builds a Word object with the actual
	 * word (string) 'word' and the information 'info'
	 */
	public Word(String word, Info info) {
		this.word=word;
		this.info=info;
	}
	
	/**
	 * Returns the actual word (string)
	 * of this Word
	 */
	public String getWord() {
       return this.word;
	}
	
	/**
	 * Returns the information (Info)
	 * of this Word
	 */
	public Info getInfo() {
       return this.info;
	}
	
	/**
	 * Returns a String representation
	 * of this Word
	 * (for testing/debugging only)
	 */
	public String toString() {
	   String result= this.word + " "+ this.info.toString();
	   return result;
	}
}
