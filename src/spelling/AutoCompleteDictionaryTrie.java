package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Di Li
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
		char[] wordc = word.toCharArray();
		int i = 0;
		TrieNode curr = root;
		while(i < wordc.length && curr.getChild(wordc[i]) != null) {
			curr = curr.getChild(wordc[i]);
			i++;
		}
		if (i == wordc.length) { // if the search found all the characters
			if (curr.endsWord()) {
				return false;
			} else {
				curr.setEndsWord(true);
				size++;
				return true;
			}
		} else {
			while (i < wordc.length) {
				curr = curr.insert(wordc[i]);
				i++;
			}
			curr.setEndsWord(true);
			size++;
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		s = s.toLowerCase();
		char[] chars = s.toCharArray();
		TrieNode node = root;
		for (char c : chars) {
			node = node.getChild(c);
			if (node == null) {
				return false;
			}
		}
		if (node.endsWord()) {
			return true;
		} else return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 prefix = prefix.toLowerCase();
    	 char[] prefixc = prefix.toCharArray();
    	 List<String> predicts = new LinkedList<String>();
    	 TrieNode curr = root;
    	 
    	 // see if the stem is in the trie
    	 for (char c : prefixc) {
    		 curr = curr.getChild(c);
    		 if (curr == null) {
    			return predicts; //if not, return the empty list of strings
    		 }
    	 }
    	 
    	 LinkedList <TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(curr);
    	 int num = 0;
    	 Set <Character> c;
    	 while (!queue.isEmpty() && num < numCompletions) {
    		 curr = queue.remove();
    		 if (curr.endsWord()) {
    			 predicts.add(curr.getText());
    			 num++;
    		 }
    		 c = curr.getValidNextCharacters();
    		 for (char cc : c) {
    			 queue.add(curr.getChild(cc));
    		 }
    	 }
         return predicts;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
// 	public static void main(String[] args) {
// 		AutoCompleteDictionaryTrie trie1 = new AutoCompleteDictionaryTrie();
// 		trie1.addWord("Hello");
// 		trie1.addWord("Helly");
// 		trie1.addWord("hellw");
// 		trie1.addWord("helo");
// 		trie1.addWord("helz");
// 		trie1.addWord("helos");
// 		//trie1.addWord("hell");
// 		//trie1.printTree();
// 		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();
//		smallDict.addWord("Hello");
//		smallDict.addWord("HElLo");
//		smallDict.addWord("help");
//		smallDict.addWord("he");
//		smallDict.addWord("hem");
//		smallDict.addWord("hot");
//		smallDict.addWord("hey");
//		smallDict.addWord("a");
//		smallDict.addWord("subsequent");
//		List<String> list = smallDict.predictCompletions("", 4);
//		for (String s : list) {
//			System.out.println(s);
//		}
//		System.out.println(list.size());
// 		//System.out.println(trie1.size());
// 	}

	
}