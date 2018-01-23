package main;

import java.util.Hashtable;

public class SenticNetSingleton {
	   private static SenticNetSingleton sing = new SenticNetSingleton();
	    private Hashtable<String, String> hashTablo;
	    
	    
		private SenticNetSingleton() 
	        {
	                hashTablo = new Hashtable<String,String>();
		}
	        public static SenticNetSingleton getInstance()
	        {
	            return sing;
	                    
	        }
	        public void put(String Key, String Value)
	        {
	            hashTablo.put(Key, Value);
	        }
	        public int getSize()
	        {
	            return hashTablo.size();
	            
	        }
	        public String get(String key)
	        {
	            return hashTablo.get(key);
	           
	        }
	        public boolean containsKey(String key)
	        {
	            return(hashTablo.containsKey(key));
	            
	        }
}
