
import java.util.*;

import sun.nio.ch.EPollSelectorProvider;

/**
 * A class for Symbol Table
 */
public class SymTable {

    public LinkedList<HashMap<String,SymInfo>> list;
    /**
     * Create a Symbol Table with one empty scope
     */
    public SymTable() {
        list= new LinkedList<HashMap<String,SymInfo>>();
        list.addFirst(new HashMap<String,SymInfo>());
    }

    /**
     * Add a declaration (i.e. a pair [name,sym]) in the inner scope
     */
    public void addDecl(String name, SymInfo sym) throws DuplicateSymException, EmptySymTableException {
        if(this.list.isEmpty())
        {
            throw new EmptySymTableException();
        }
        else if(name.equals(null) || sym.equals(null)) 
        {
            throw new NullPointerException();        
        }
        else if(this.list.get(0).containsKey(name))
        {
            throw new DuplicateSymException();
        }
        else
        {
            this.list.get(0).put(name,sym);
        }
    }

    /**
     * Add a new inner scope
     */
    public void addScope() {
       HashMap<String,SymInfo> map= new HashMap<String,SymInfo>();
       this.list.addFirst(map);
    }

    /**
     * Lookup for 'name' in the inner scope
     */
    public SymInfo lookupLocal(String name) throws EmptySymTableException {
       if(!this.list.isEmpty())
       {
           if(list.get(0).containsKey(name))
           {
               return list.get(0).get(name);
           }
           else return null;
       }
       else throw new EmptySymTableException();
    }

    /**
     * Lookup for 'name' sequentially in all scopes from inner to outer
     */
    public SymInfo lookupGlobal(String name)  throws EmptySymTableException {
        if(!this.list.isEmpty())
        {
            for(int i=0;i<this.list.size();i++)
            {
                if(list.get(i).containsKey(name))
                {
                    return list.get(i).get(name);
                }
            }
            return null;
        }
        else throw new EmptySymTableException();
    }

    /**
     * Remove the inner scope
     */
    public void removeScope() throws EmptySymTableException {
        if(!this.list.isEmpty())
        {
            this.list.removeFirst();
        }
        else throw new EmptySymTableException();
    }

    /**
     * Print the Symbol Table on System.out
     */
    public void print() {
        System.out.print("\nSym Table\n");
        for(int i=0;i<this.list.size();i++)
        {
            System.out.print("{");
            int j=0;
            for(String key:this.list.get(i).keySet())
            {
                   System.out.print(key + "="+ this.list.get(i).get(key).toString());
                   j++;
                   if(j!=this.list.get(i).size())
                   {
                     System.out.print(", ");
                   }
            }
            System.out.print("} \n");
        }
        System.out.print("\n");
        
    }
    
}
