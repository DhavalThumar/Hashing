/***
 *    Java Program to Implement Hash Tables Chaining with List Heads for Storing Contact number 
 *    Programmer : Thumar Dhaval (1401091)
 *                 Patel Roky (1401092)
 *    Institute  : School Of Enginnering and Applied Science (SEAS), Ahmedabad University
 *    Contect    : dhaval.thumar@iet.ahduni.edu.in
 *                 roky.patel@iet.ahduni.edu.in
 *
**/
 
import java.util.Scanner;

/* Class HashEntry */
class HashEntry 
{
    String key; 
    int value;
    HashEntry next;
 
    /* Constructor */
    HashEntry(String key, int value) 
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
 
/* Class HashTable */
class HashTable
{
    private int table_size;	// Variable table_size is for internal storage purpose (Hashtable size)
    private int size; 		// Variable size is for total number of contact store in database
    private HashEntry[] table;  // Making array objects of HashEntry class 
 
    /* Constructor */
    public HashTable(int ts) 
    {
        size = 0;	 	// initialize size with 0 contact store in database
        table_size = ts; 	// Assigning Hash table size to table_size 
        table = new HashEntry[table_size]; // Initalize Hash table
        for (int i = 0; i < table_size; i++)	
            table[i] = null;	// make array empty
    } 

    /* Function to get number of key-value pairs */
    public int getSize()	
    {
        return size;		// returning size of total contact saved 
    }

    /* Function to clear hash table */
    public void Empty()
    {
        for (int i = 0; i < table_size; i++)
            table[i] = null;
    }

    /* Function myHash which gives a hash value for a given string */
    private int myHash(String x )
    {
        int hashVal = x.hashCode( ); /* hashCode is inbuilt function provided by java library that will manage and store all the data randomly and efficientily
					This method returns a hash code value for this object */	
        hashVal %= table_size;
        if (hashVal < 0)	// if hashVal has -ve value than we will increase hashVal with table size
            hashVal += table_size;
        return hashVal;		/* Returning hashVal 
				   consider the eg. if table_size is five than hashVal will be 1 to 4 */	
    }

    /* Function to insert a key value pair */
    public void insert(String key, int value) 
    {
        int hash = (myHash( key ) % table_size);	// for the varification purpose we have done modulation operation with returning value from myHash function
	// System.out.println("myHash fn return value: "+myHash(key));	
	// System.out.println("hash value: "+hash);
        if (table[hash] == null)	// if the table[hash] is null than make a new entry in hash table
            table[hash] = new HashEntry(key, value);
        else 
        {
            HashEntry entry = table[hash];
	    // Here we have implemented consept of linked list or generally say chain
            while (entry.next != null && !entry.key.equals(key)) // if the next node is not empty or both the key is not same than...
	    {            
    		entry = entry.next; 
	    }

            if (entry.key.equals(key)) // if the key value is same than update new value in table
                entry.value = value;
            else
                entry.next = new HashEntry(key, value);
        }
        size++; // incresing size for conting purpose
    }

    /* Function to get value of a key */
    public int get(String key) 
    {
        int hash = (myHash( key ) % table_size);	// for the varification purpose we have done modulation operation with returning value from myHash function
        if (table[hash] == null)	// if table[hash] is empty than return nothing found 
            return -1;
        else 
        {
            HashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key))
            {            
    		entry = entry.next; 
	    }
	   
	   if (entry == null)
                return -1;
            else
                return entry.value;	// returning corresponding value associate with key
        }
    }
    
 
    public void remove(String key) 
    {
        int hash = (myHash( key ) % table_size);	// for the varification purpose we have done modulation operation with returning value from myHash function	
        
	if (table[hash] != null) 
        {
            HashEntry prevEntry = null;
            HashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) 
            {
                prevEntry = entry;	// assinging current entry to previous entry
                entry = entry.next;	// assinging next entry to current entry
            }
            if (entry.key.equals(key)) 
            {
                if (prevEntry == null)
                    table[hash] = entry.next;
                else
                    prevEntry.next = entry.next;
                size--;
            }
        }
    }

    public boolean exists(int value) // boolean function for checking if there are any value is exists or not
    {
	return true;
    } 
    
    /* Function to print hash table */
    public void printHashTable()	// this function is only for proggrammer purpose to see how the data is store in hash table
    {
        for (int i = 0; i < table_size; i++)
        {
            System.out.print("\nData "+ (i + 1) +" : ");
            HashEntry entry = table[i];
            while (entry != null)
            {
                System.out.print(entry.value +" ");
                entry = entry.next;
            }            
        }
    }
}
 
/* Main Class Dictionary */
public class Dictionary
{
    public static void main(String[] args)
    {
	int size=5; /*bucket size or Hash table size is intially set as 5 
	              at the backhend side user does not know how mach size of Hashtable is generated 
		      for the general purpose we took size=5 othewise we can ask for the size of table 
                      from user like shown below*/

	Scanner scan = new Scanner(System.in);

        // System.out.println("Enter size");
        // HashTable ht = new HashTable(scan.nextInt() );

	// Make object of HashTable, Random
 	HashTable ht = new HashTable(size);
	// Random randomGenerator = new Random();
	
        char ch;
        // Perform HashTable operations  
        do    
        {
	    System.out.println("\n*****************************************");
            System.out.println("*  Phone number Dictionary Operations   *");
            System.out.println("*  1. Insert a number                   *");
            System.out.println("*  2. Remove a number                   *");
            System.out.println("*  3. Search                            *");            
            System.out.println("*  4. Clear                             *");
	    System.out.println("*  5. Size                              *");
            System.out.println("*****************************************");
 	    System.out.print("Enter your choice from above:");	
            int choice = scan.nextInt(); 
            
	    switch (choice)
            {
            case 1 : // Insert
                System.out.print("Enter a Name:");
		String name=scan.next();
		System.out.print("Enter a Contact Number:");
		int num=scan.nextInt();
                ht.insert(name, num); // Inseting number into dictionary (key,value)
                break;                          
            
            case 2 : // Remove                
                System.out.print("Enter a name to remove from Dictionary:");  
		if (ht.get( name2 ) == -1)
		{
		System.out.println("Contect doesn't found, Please search for another number");
		}
		else
		{                
		ht.remove( scan.next() ); // Removing number from Dictionary
		System.out.println("Contect is removed sucessfully");		
		}
                break;                                    
	    
            case 3 : // Search
	        System.out.print("Enter name to search:");
		String name2=scan.next();
		
		if (ht.get( name2 ) == -1)
		{
		System.out.println("Contect doesn't found, Please search for another number");
		}
		else
		{
		System.out.println("Number of "+ name2 +" is  "+ ht.get( name2 )); // Associated value with serching name 	
		}
	        break;                                   
            	

	    case 4 : // Clear Dictionary
                ht.Empty();	//clear Dictionary
                System.out.println("Phonebook is Cleared\n");
                break;
            
	    case 5 : // Size of Database
                System.out.println("Total contact number in your Phonebook is = "+ ht.getSize() );
                break;  
	       
            default : // Wrong choice
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /* Display hash table */
            ht.printHashTable();// printing hashtable only for the how data is stored in backhand side
 				   
            System.out.print("\nDo you want to continue (Type y or n): ");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}
/***
 *END OF THE PROGRAM
 *If you found any error please mail us
**/

