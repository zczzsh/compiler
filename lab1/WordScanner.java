
import java.io.*;
import java.util.*;

/**
 * This class implements a word (string) scanner
 */
public class WordScanner {

	private FileReader input;
	private int wordpos;
	private int currentpos;
	private int wordline;
	private char[] wordstring;
	/**
	 * Builds a WordScanner object based on the given input
	 */
	public WordScanner(FileReader input) throws IOException {
		this.input=input;
		this.wordpos=1;
		this.currentpos=0;
		this.wordline=1;

		Scanner reader= new Scanner(this.input);
		String string="";
		String result="";
		while(reader.hasNextLine())
		{
			string=reader.nextLine();
			result = result + string +'\n';
		}
		this.wordstring = result.toCharArray();
		System.out.print(this.wordstring);
		System.out.println(this.wordstring.length);
	}
	
	/**
	 * Returns the next word from input
	 * Precond: there must be at least
	 * one word left in the input
	 * (i.e. hasNextWord() must evaluate to true)
	 */
	public Word nextWord() throws IOException {
	   
	   if(hasNextWord())
	   {
		   while(this.currentpos<this.wordstring.length && !isLetterOrisNumber(this.currentpos))
		   {
			   if(this.wordstring[this.currentpos]=='\n')
			   {
				   this.wordline++;
				   this.wordpos=1;
			   }
			   else
			   {
				   this.wordpos++;
			   }
			   this.currentpos++;
		   }
           
			Info info= new Info(this.wordpos,this.wordline);
			String result="";
			while(this.currentpos<this.wordstring.length && (isLetterOrisNumber(this.currentpos) ||QuoteInWord(this.currentpos)))
			{
				result= result+ this.wordstring[this.currentpos];
				this.currentpos++;
				this.wordpos++;
				if(this.wordstring[this.currentpos]=='\n')
				{
					this.wordpos=1;
					this.currentpos++;
					this.wordline++;
					break;
				}
			}
			Word resultword=new Word(result,info);
			
			while(this.currentpos<this.wordstring.length && !isLetterOrisNumber(this.currentpos))
			{
				if(this.wordstring[this.currentpos]=='\n')
				{
					this.wordline++;
					this.wordpos=1;
				}
				else
				{
					this.wordpos++;
				}
				this.currentpos++;
			}
		     
		   return resultword;
	   }
	   else return null;
	}
	
	/**
	 * Returns true if there is at least
	 * one word left in the input, false otherwise
	 */
	public boolean hasNextWord() {
		 return  this.currentpos<this.wordstring.length;
	}

	public boolean isLetterOrisNumber(int pos){
		return (
		Character.isLetter(this.wordstring[pos]) || 
		(this.wordstring[pos]>='0' && this.wordstring[pos]<='9')
		);
	}

	public boolean QuoteInWord(int pos){
		 return (this.wordstring[pos]=='\'' && Character.isLetter(wordstring[pos-1]) && Character.isLetter(wordstring[pos+1]));
	}
	
}
