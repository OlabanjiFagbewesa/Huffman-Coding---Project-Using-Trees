package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.TabExpander;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);


        //stores sorted arrayList of CharFreq objects:
        ArrayList<CharFreq> listChar = new ArrayList<CharFreq>(); //holds list representing 128 of the ASCII characters.


        sortedCharFreqList = new ArrayList(); // instance variable arrayList initialized.
        int [] ASCIIValues = new int[128]; // 128 Values in array.
        double counter = 0; //counts the amount of values in the the file (how many chars there are).
        CharFreq tempCharacter = null; // probability would be 0 for now.
        Double probability;


        while (StdIn.hasNextChar()){ // while there are still remaing chars in the input file.
            char character = StdIn.readChar(); // gets character from the input file.

            // System.out.println((int)character); // prints out the decimal index values (a=97).

            ASCIIValues[(int)character] += 1; // All the array values are zero for each index, so what this line does is when it
            // ^ does the same thing as iterating.
            
            // detects a character, the character's decimal index will increase by 1 (to count amount of occurences of a specific char).
            // so this would make a = 4 when running input1.txt.
            counter++; // can use the counter from the while loop check the amount of total characters to get the tot
            
            // if (ASCIIValues[character]==1 || ASCIIValues[character]==0){ // 
                

        }

        for (int i =0; i<ASCIIValues.length; i++){
            if (ASCIIValues[i]>=1){ // only adding a char to array list if we have already seen the specific char from the text file.
                probability = ASCIIValues[i]/counter; 
                sortedCharFreqList.add(new CharFreq((char)i, probability));

            }
        }

        if (sortedCharFreqList.size()==1){ 
            // edge case (since the program doesn't work when there is only 1 distinct character):

            // in this case, we will wrap around to ASCII value 0 if the ASCII value is 127.
            if ((int) sortedCharFreqList.get(0).getCharacter()==127){ //(int) gets ASCII value
                // this gets the specific character at the 0 index, we use 0 index because we already know that the size is 0 so
                // it only has 1 element (or index) 0, then it gets the actual character using .getCharacter:

                // now, simply wrap around by creating a new object and reference (or point to) the char at ASCII value 0, then
                // the probability will be 0 because that's what the directions say: 
                sortedCharFreqList.add(new CharFreq((char)0, 0));
            }
            // in this case, add a specific character probOcc 0 to your ArrayList (1 more ASCII value):
            else if((int) sortedCharFreqList.get(0).getCharacter()<127){

                //adding ASCII value +1 to get the next ASCII value.
                int nextValue = (int)sortedCharFreqList.get(0).getCharacter()+1;

                
                //then we add the char of the next ASCII value to the arrayList, then put probability to be 0.
                sortedCharFreqList.add(new CharFreq((char)nextValue, 0));
            }

        }

        Collections.sort(sortedCharFreqList);

        //now we get the probability:

    

        // makes sure to add a different character when there is only 1 distinct character:

        // must add different character with probOcc 0 to your ArrayList.



	/* Your code goes here */
    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */

    Queue <TreeNode> source = new Queue<TreeNode>(); // represents the source node from the video
    Queue <TreeNode> target = new Queue<TreeNode>(); // represents the target node from the video


    for (int i =0; i< sortedCharFreqList.size(); i++){ // gets every value from sortedCharFreqList
        source.enqueue(new TreeNode(sortedCharFreqList.get(i),null,null));  // we are looping through the arrayList
        // and enqueing the elements into source.
    }
    TreeNode min1 = null; // min1 and min2 represent the two nodes we are dequeuing from source or target (since we have to dequeue twice
    // each time).
    TreeNode min2 = null;
    while (!source.isEmpty() || !(target.size()==1)){ // while loop runs while the source queue isn't empty or the target size has just 1
    // node inside of it.
         //enqueue the nodes into source in increasing probability;
        // if source first element or node is less than first element from target we dequeue from source.

        // make sure source.peek isn't null:

        // if 2 certains characters have the same probability, the program will give me an error (ASK CENTENO):
        if (source.isEmpty()){
            min1 = target.dequeue(); 
            min2 = target.dequeue(); 
        }
        else{
        if (target.isEmpty() || (source.peek().getData().getProbOcc()<=target.peek().getData().getProbOcc())){
            min1 = source.dequeue(); // if node probability from source is less than node probability of target, we dequeue from source.
            // (first dequeue)

        }
        else{
            min1 = target.dequeue(); // if not we dequeue from target.
        }
        
        // make sure target.peek isn't null:
        if (!(source.isEmpty()) && ((target.isEmpty()) || source.peek().getData().getProbOcc()<=target.peek().getData().getProbOcc())){
            min2 = source.dequeue(); // if source has size 1, we'd have an empty list going through the second if statement so
            // source must not be empty when we get to second dequeue.

        }
        else{
            min2 = target.dequeue(); // we deque from source otherwise.
        }
    }
        target.enqueue(new TreeNode(new CharFreq(null, min1.getData().getProbOcc()+min2.getData().getProbOcc()),min1, min2 ));
        // we enqueue the data from the nodes we dequeued into target, and min1 is left and min2 is right because that's the setup of
        // the binary tree.

        // repeat until source is empty and target queue has only 1 node.
    }
    // theres only one node in target after this, min1 and min2 just represent the children of the root node (highest node in binary tree).

    huffmanRoot=target.dequeue(); // huffmanRoot is just a reference to the root of the tree
    // it gets the root from the target LL.



    // while (ptr.getData()!=target.peek()){

    // }

    // something wrong with method 2 (input 6 and input 2.txt)

    



    }

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {

	/* Your code goes here */

    encodings = new String[128];
    TreeNode ptr = huffmanRoot; //ptr that starts at the root
    TreeNode root = huffmanRoot;
    int amountOfTimesVisitingRoot = sortedCharFreqList.size();
    checkNode(ptr,encodings, ""); // passes in an empty String. (to later be manipulated).

    // for()
    //if theres a character, reassign yourself to the root
    // while (ptr!=null){
    //     // int compare = (int) ptr.getData().getCharacter().compareTo(huffmanRoot.getData().getCharacter());
    //     checkNode(ptr, root);

    //     // if (compare==0){
    //     //     // return encodings;
    //     // }
    //     // else if (compare<0){
    //     //     // ptr = ptr.getLeft();
    //     //     // encodings.substring(encodings.length-2,encodings.length) = "0";
    //     // }
    //     // else{
    //     //     // ptr = ptr.getRight();
    //     //     // encodings.substring(encodings.length-2,encodings.length) = "1";
    //     // }



    // }
    }
    private void checkNode (TreeNode ptr, String [] encodings, String pathName){
        //concat string
        if (ptr.getLeft()==null && ptr.getRight()==null){ // once they are both null we know we got to a character in the tree.
            encodings[ptr.getData().getCharacter()] = pathName;
            return;
            // return, returning blank allows you to end that method and go back to where you were at before because you have a method calling
            // another method.
        }
        checkNode(ptr.getLeft(),encodings, pathName+ "0"); // adds 1 or 0 based on whether we go left or right on the subtree.
        checkNode(ptr.getRight(), encodings, pathName + "1");



    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {

	/* Your code goes here */
    StdIn.setFile(fileName);
    String onesAndZeroes = new String();
    String newString = "";
    while (StdIn.hasNextChar()){
        char characterToBeUsed = StdIn.readChar();
        onesAndZeroes = encodings[(int)characterToBeUsed];
        newString+=onesAndZeroes; // make sure to concatenated.
    }
    writeBitString(encodedFile, newString);

    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

        String fileLength = readBitString(encodedFile); // gives us String in terms of ones and zeroes,`
        TreeNode ptr = huffmanRoot;

        for (int i =0;i<fileLength.length(); i++){
            if (fileLength.charAt(i)=='0'){
                ptr= ptr.getLeft();
            }
            if (fileLength.charAt(i)=='1'){ // if (onesAndZeroes.charAt(i)=='1')
                ptr=ptr.getRight();
            }
            if(ptr.getLeft()==null && ptr.getRight()==null){ // got to leaf node
                // decodedFile+=ptr.getData().getCharacter();
                StdOut.print(ptr.getData().getCharacter());
                ptr=huffmanRoot; // sets ptr back to huffmanRoot

            }
        }


        // while (ptr!=null){
        //     // if there are no nodes left (probably reached a leaf node or there's only 1 node in tree), concatenate everything from
        //     //onesAndZeroes into decodedfile.
        //     if (ptr.getLeft()==null && ptr.getRight()==null){
        //         emptyString+="3";
        //     }
        //     // if there are nodes on the left side of tree
        //     else if(ptr.getLeft()!=null){
        //         emptyString+="0";

        //     }
        //     else{
        //         //if there are nodes on the right side of tree
        //         emptyString+="1";
        //     }
        // }
        // sometimes it doesn't print, so do StdOut to test.

        // StdOut.print(decodedFile); // decoded file is what I have to print and make in terms of chars.


        //readBitString reads the encoded file we made in encodefile() method.
        // String emotyString = "";
        // for (int i =0;i<encodedFile.length(); i++){
        //     decodedFile += encodedFile;
        // }

        // // must use huffmanRoot.
        // TreeNode ptr = huffmanRoot;
        // StdOut.print(readBitString(encodedFile));
        

	/* Your code goes here */
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
