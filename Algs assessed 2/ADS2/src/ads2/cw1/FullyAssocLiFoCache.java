package ads2.cw1;

/**
 * Created by wim on 28/11/2017.
 * The public interface of this class is provided by Cache
 * All other methods are private. 
 * You must implement/complete all these methods
 * You are allow to create helper methods to do this, put them at the end of the class 
 */
import ads2.cw1.Cache;

import java.util.Stack;
import java.util.HashMap;
import java.util.Set;

class FullyAssocLiFoCache implements Cache {

    final private static boolean VERBOSE = true;

    final private int CACHE_SZ;
    final private int CACHELINE_SZ;
    final private int CL_MASK;
    final private int CL_SHIFT;
    final private int CACHE_NB_LINES;

    // WV: because the cache replacement policy is "Last In First Out" you only need to know the "Last Used" location
    // "Last Used" means accessed for either read or write
    // The helper functions below contain all needed assignments to last_used_loc so I recommend you use these.

    private int last_used_loc;
    // WV: Your other data structures here
    // Hint: You need 4 data structures
    // - One for the cache storage
    private int[][] cache_storage;    
    // - One to manage locations in the cache
    private Stack<Integer> location_stack; // use stack?   
    // And because the cache is Fully Associative:
    // - One to translate between memory addresses and cache locations
    private HashMap<Integer,Integer> address_to_cache_loc = new HashMap<Integer,Integer>();
    // - One to translate between cache locations and memory addresses  
    private HashMap<Integer, Integer> cache_loc_to_address = new HashMap<Integer, Integer>();
    


    FullyAssocLiFoCache(int cacheSize, int cacheLineSize) {

        CACHE_SZ =  cacheSize;
        CACHELINE_SZ = cacheLineSize;
        CL_MASK = CACHELINE_SZ - 1;
        Double cls = Math.log(CACHELINE_SZ)/Math.log(2); 
        CL_SHIFT = cls.intValue(); //largest power of two that fits in nb CACHELINE_SZ

        last_used_loc = CACHE_SZ/CACHELINE_SZ - 1; // init last_used_loc
        
        CACHE_NB_LINES = CACHE_SZ/CACHELINE_SZ;
        if (VERBOSE) System.out.println("cache size is: "+CACHE_SZ);
        if (VERBOSE) System.out.println("cache line size is: "+CACHELINE_SZ);
        if (VERBOSE) System.out.println("cache nb of lines is: "+CACHE_NB_LINES);
        cache_storage = new int[CACHE_NB_LINES][CACHELINE_SZ]; // init cache_storage      
        location_stack = new Stack<Integer>(); 	// init location_stack 
        for(int i = 0; i<CACHE_NB_LINES; i++) {
        	location_stack.push(i);				
        }
        // init for HashMaps?
    }

    public void flush(int[] ram, Status status) {
        if (VERBOSE) System.out.println("Flushing cache");
        // remove all data from cache and write it to memory
        // write data to memory
        for(int i = 0; i < CACHE_NB_LINES; i++) { //for each line
        	int baseAddress = cache_loc_to_address.get(base_address_of_line(i)); //baseAddress = base ram address for that line
        	for(int j = 0; j < CACHELINE_SZ; j++) {  //for each word in the line
        		ram[baseAddress+j] = cache_storage[i][j];
        	}
        }

        //reset location stack //set all locations to be empty // this resets the cache to being empty
        for(int i = 0; i < CACHE_NB_LINES; i++) {
        	location_stack.push(i);	
        }
        // reset hashmaps
        address_to_cache_loc.clear();
        cache_loc_to_address.clear();
        // reset last_used_loc
        last_used_loc = CACHE_SZ/CACHELINE_SZ - 1;
        //set status
        status.setFlushed(true);
        status.setFreeLocations(this.CACHE_SZ);
        if (VERBOSE) System.out.println("Finished flushing cache");
    }

    
    public int read(int address,int[] ram,Status status) {
        return read_data_from_cache( ram, address, status);
    }

    public void write(int address,int data, int[] ram,Status status) {
        write_data_to_cache(ram, address, data, status);
    }

    // The next two methods are the most important ones as they implement read() and write()
    // Both methods modify the status object that is provided as argument

    private void write_data_to_cache(int[] ram, int address, int data, Status status){ //address in memory
        status.setReadWrite(false); // i.e. a write
        status.setAddress(address);
        status.setData(data);
        status.setEvicted(false);
        status.setHitOrMiss(true);
        status.setFlushed(false);
        // The cache policy is write-back, so the writes are always to the cache. 
        // The update policy is write allocate: on a write miss, a cache line is loaded to cache, followed by a write operation. 
        	if (VERBOSE) System.out.println("Attempting to write data to cache");
        
        if(address_to_cache_loc.get(address) == null ) { //address is not in cache
        	status.setHitOrMiss(false);
        		if (VERBOSE) System.out.println("Could not find address in cache");
        	//find free space
        	if(location_stack.isEmpty()) { //no free locations in cache so evict last used location to make space
        			if (VERBOSE) System.out.println("Could not find empty location in cache to load memory into");
        			if (VERBOSE) System.out.println("Emptying out a location");
        		status.setEvicted(true);
        		status.setEvictedCacheLoc(last_used_loc);
        		status.setEvictedCacheLineAddr(cache_line_address(cache_loc_to_address.get(base_address_of_line(last_used_loc))));
        		evict_last_used();       		
        			if (VERBOSE) System.out.println("Last used location freed!");
        	}	
        	//now the stack defo has a free location
        	int freeLoc = location_stack.pop();
        	//load line to cache in free space
        		if (VERBOSE) System.out.println("Loading new line to cache in free space");
        	load_line_into(freeLoc, address, ram);    	
        }
        //NEED TO SET LAST USED LOC
    	int offset = address % CACHELINE_SZ;
        	if (VERBOSE) System.out.println("Place to write to is in cache.");
        	if (VERBOSE) System.out.println("Place to write to is (in terms of total cache size): " + address_to_cache_loc.get(address));
        int cacheLoc = address_to_cache_loc.get(address);
        	if (VERBOSE) System.out.println("Line to write to is : " + (cache_line_of(cacheLoc)));
    	int cacheLine = cache_line_of(cacheLoc);
    		if (VERBOSE) System.out.println("Location in cache to write to : " + cacheLine + " : "+ offset);
    	cache_storage[cacheLine][offset] = data;
    		if (VERBOSE) System.out.println("Free locations left : " + location_stack.size());
    	status.setFreeLocations(location_stack.size());
    	last_used_loc = cacheLine;
    		if (VERBOSE) System.out.println("Write done");

    }
        
    private int read_data_from_cache(int[] ram,int address, Status status){
        status.setReadWrite(true); // i.e. a read
        status.setAddress(address);
        status.setEvicted(false);
        status.setHitOrMiss(true); // i.e. a hit
        status.setFlushed(false);
        // Reads are always to the cache. On a read miss you need to fetch a cache line from the DRAM
        // If the data is not yet in the cache (read miss),fetch it from the DRAM
        // Get the data from the cache
        
        
        //if address is not in cache then fetch it
        if(address_to_cache_loc.get(address) == null) {
        	status.setHitOrMiss(false);
        	if(location_stack.isEmpty()) {
        		status.setEvicted(true);
        		status.setEvictedCacheLoc(last_used_loc);
        		status.setEvictedCacheLineAddr(cache_line_address(cache_loc_to_address.get(base_address_of_line(last_used_loc))));
        		evict_last_used();    		
        	}
        	
        	//now we have a free location
        	int freeLoc = location_stack.pop();
        	load_line_into(freeLoc, address, ram);
        }

        //read the cache data       
        int offset = address % CACHELINE_SZ; //offset on the cache line
        int cacheLoc = address_to_cache_loc.get(address);
        int cacheLine = cache_line_of(cacheLoc);
        int data = cache_storage[cacheLine][offset];
        status.setData(data);
        status.setFreeLocations(location_stack.size());
        last_used_loc = cacheLine;
        return data;
    }

    
 // Given a main memory address, return the corresponding cache line address
    private int cache_line_address(int address) {
        return address>>CL_SHIFT;
    }
    
    // Given a cache loc returns the corresponding cache line
    private int cache_line_of(int cacheLoc) {
        return cacheLoc>>CL_SHIFT;
    }
    
    // Given a cache line nb return the cache address
    private int base_address_of_line(int cacheLine) {
    	return cacheLine*CACHELINE_SZ;
    }
    
    // evicts last used line in cache (from last_used_loc) and updates the location stack and hashmaps
    void evict_last_used() {
    	//evict last used location from cache_storage and hashmaps
		for (int i = 0; i < CACHELINE_SZ; i++) {
			cache_loc_to_address.remove(base_address_of_line(last_used_loc)+i);
			address_to_cache_loc.remove(cache_loc_to_address.get(base_address_of_line(last_used_loc)+i)); 
		}		
		//update stack
		location_stack.push(last_used_loc);
    };
    
    
    //loads a new line into the cache at the freeLoc line that contains address data
    void load_line_into(int freeLoc, int address, int[] ram) {
    	int baseRamAddress = base_address_of_line(cache_line_address(address));
    	for(int i=0; i<CACHELINE_SZ; i++) {
    		// compute ram address to get into cache
    		int ramAddress = baseRamAddress+i;
    		// write data to cache
    		cache_storage[freeLoc][i] = ram[baseRamAddress+i];
    		
    		//update hashmaps
    		int cacheLoc = base_address_of_line(freeLoc)+i;
    		address_to_cache_loc.put(ramAddress, cacheLoc);
    		cache_loc_to_address.put(cacheLoc, ramAddress);
    	}
    }
    
    void load_line_into_prints(int freeLoc, int address, int[] ram) {
    	int baseRamAddress = cache_line_address(address)*CACHELINE_SZ;
    	for(int i=0; i<CACHELINE_SZ; i++) {
    		if (VERBOSE) System.out.println("");
    		if (VERBOSE) System.out.println("cache line size is: "+CACHELINE_SZ);
    		if (VERBOSE) System.out.println("address given we want to load dat from is: "+address);
    		if (VERBOSE) System.out.println("iteration is: "+i);	
    		int ramAddress = baseRamAddress+i;
    		if (VERBOSE) System.out.println("place we are trying to access in ram is: "+ramAddress);
    		if (VERBOSE) System.out.println("size of ram is: "+ram.length);
    		if (VERBOSE) System.out.println("cache line writing to is: "+freeLoc);
    		if (VERBOSE) System.out.println("cache storage nb lines is: "+cache_storage.length);
    		if (VERBOSE) System.out.println("cache storage line size is: "+cache_storage[freeLoc].length);
    		cache_storage[freeLoc][i] = ram[ramAddress];
    		if (VERBOSE) System.out.println("Loaded in "+i+"st/nd/rd/th word of the line");
    		
    		//update hashmaps
    		int cacheLoc = freeLoc*CACHELINE_SZ+i;
    		if (VERBOSE) System.out.println("Cache loc in which ram data was put: "+cacheLoc);
    		address_to_cache_loc.put(ramAddress, cacheLoc);
    		cache_loc_to_address.put(cacheLoc, ramAddress);
    		if (VERBOSE) System.out.println("Updated hashmaps for it "+i);
    	}
    }
    
    
    /**
    // You might want to use the following methods as helpers
    // but it is not mandatory, you may write your own as well
    
    // On read miss, fetch a cache line    
    private void read_from_mem_on_miss(int[] ram,int address){
        int[] cache_line = new int[CACHELINE_SZ];
        int loc;
        // Your code here
         // ...

        last_used_loc=loc;
   }

    // On write, modify a cache line
    private void update_cache_entry(int address, int data){
        int loc;
         // Your code here
         // ...

        last_used_loc=loc;
       }

    // When we fetch a cache entry, we also update the last used location
    private int fetch_cache_entry(int address){
        int[] cache_line;
        int loc;
         // Your code here
         // ...
        last_used_loc=loc;
        return cache_line[cache_line_address(address)];
    }

    // Should return the next free location in the cache
    private int get_next_free_location(){
         // Your code here
         // ...
        
    }

    // Given a cache location, evict the cache line stored there
    private void evict_location(int loc){
         // Your code here
         // ...
        
    }

    private boolean cache_is_full(){
         // Your code here
         // ...
        
    }

    // When evicting a cache line, write its contents back to main memory
    private void write_to_mem_on_evict(int[] ram, int loc){

        int evicted_cl_address;
        int[] cache_line;
        if (VERBOSE) System.out.println("Cache line to RAM: ");
        // Your code here
         // ...
        

        evict_location(loc);
    }

    // Test if a main memory address is in a cache line stored in the cache
    // In other words, is the value for this memory address stored in the cache?
    private boolean address_in_cache_line(int address) {
        // Your code here
         // ...
        
    }
	**/
    
    /**
    // Given a main memory address, return the corresponding index into the cache line
    private int cache_entry_position(int address) {
        return address & CL_MASK;
    }
    // Given a cache line address, return the corresponding main memory address
    // This is the starting address of the cache line in main memory
    private int cache_line_start_mem_address(int cl_address) {
        return cl_address<<CL_SHIFT;
    }
    
    **/

}
