package Miniproject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class QuickFitAllocator {
    // Define the free lists for different block sizes
    private Map<Integer, List<String>> freeLists;
    private List<String> allocatedBlocks; // For storing allocated blocks
    private List<String> freeBlockList; // For storing free blocks after split

    public QuickFitAllocator(List<Integer> blockSizes) {
        freeLists = new HashMap<>();
        allocatedBlocks = new ArrayList<>();
        freeBlockList = new ArrayList<>();

        // Initialize free lists for predefined block sizes
        for (int size : blockSizes) {
            freeLists.put(size, new ArrayList<>());
        }
    }

    // Allocate a block of memory for the given size
    public String allocate(int size) {
        // First, check if the block size is available in the predefined lists
        if (freeLists.containsKey(size) && !freeLists.get(size).isEmpty()) {
            String block = freeLists.get(size).remove(0);
            allocatedBlocks.add(block); // Add to allocated blocks
            System.out.println("Allocated " + size + " KB: " + block);
            return block;
        }

        // If no exact match, we use a fallback mechanism (Best-fit strategy or other)
        if (size < 100) { // Assuming Best Fit strategy for small sizes
            for (Map.Entry<Integer, List<String>> entry : freeLists.entrySet()) {
                int availableSize = entry.getKey();
                if (availableSize >= size && !entry.getValue().isEmpty()) {
                    String block = entry.getValue().remove(0);
                    // Remove Block 4 from allocated list and replace with 80 KB
                    allocatedBlocks.remove(block); // Remove original block
                    allocatedBlocks.add(size + " KB"); // Add new 80 KB block
                    System.out.println("Allocated " + size + " KB (split from " + availableSize + " KB): " + size + " KB");

                    // Optionally split the larger block if splitting is allowed
                    int remainingSize = availableSize - size;
                    if (remainingSize > 0) {
                        // Add the remaining block to the free block list (for smaller fragments)
                        freeBlockList.add(remainingSize + " KB");
                        System.out.println("Remaining " + remainingSize + " KB added to free block list.");
                    }
                    return size + " KB";
                }
            }
        }

        System.out.println("No suitable block found, creating new block of " + size + " KB.");
        String newBlock = size + " KB Block";
        allocatedBlocks.add(newBlock); // Add new block to allocated blocks
        return newBlock;
    }

    // Display the current state of memory (both free lists and allocated blocks)
    public void displayStatus() {
        System.out.println("\nCurrent Memory State:");

        // Display free lists
        System.out.println("Free Lists:");
        for (Map.Entry<Integer, List<String>> entry : freeLists.entrySet()) {
            System.out.println("  " + entry.getKey() + " KB List: " + entry.getValue());
        }

        // Display free block list
        System.out.println("Free Block List:");
        for (String block : freeBlockList) {
            System.out.println("  " + block);
        }

        // Display allocated blocks
        System.out.println("Allocated Blocks:");
        for (String block : allocatedBlocks) {
            System.out.println("  " + block);
        }
    }

    public static void main(String[] args) {
        // Initialize the memory allocator with predefined block sizes
        QuickFitAllocator allocator = new QuickFitAllocator(Arrays.asList(50, 100, 200));

        // Initial state
        allocator.freeLists.get(50).add("Block 1");
        allocator.freeLists.get(50).add("Block 2");
        allocator.freeLists.get(100).add("Block 3");
        allocator.freeLists.get(100).add("Block 4");
        allocator.freeLists.get(200).add("Block 5");

        // Display initial memory state
        allocator.displayStatus();

        // Allocate memory for processes
        System.out.println("\nAllocating Memory:");

        // Process A requires 50 KB
        allocator.allocate(50);

        // Process B requires 100 KB
        allocator.allocate(100);

        // Process C requires 200 KB
        allocator.allocate(200);

        // Process D requires 80 KB (not a direct match, using best-fit strategy)
        allocator.allocate(80);  // This will split Block 4 (100 KB) into 80 KB and 20 KB

        // Display final memory state after allocations
        allocator.displayStatus();
    }
}
