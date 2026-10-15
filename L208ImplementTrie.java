import java.util.HashMap;
import java.util.Map;

class Trie {

    private TreeNode root;

    public Trie() {
        this.root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode current = root;
        int n = word.length();
        int i = 0;
        // go through the tree and stop either when the next char is not found or when
        // we find the whold word
        while (i < n) {
            TreeNode next = current.children[word.charAt(i) - 'a'];
            if (next == null) {
                break;
            } else {
                current = next;
            }
            i++;
        }
        // If we found the whole word. we just tag it as a word if it's not already the
        // case.
        if (i == n) {
            current.setIsEndOfWord(true);
            return;
        }
        // If we don't find some characters of the word, we insert them and tag the last
        // one as the word ending character
        while (i < n) {
            TreeNode newNode = new TreeNode(word.charAt(i));
            current.addChild(newNode);
            current = newNode;
            i++;
        }
        current.setIsEndOfWord(true);
    }

    // Go through the tree, until you either reach the last character (true if this
        // last character is a word ending char)or don't find an intermediate character
        // (false)
    public boolean search(String word) {
        TreeNode lastNodeForWord = getLastNodeForPrefix(word);
        return lastNodeForWord != null && lastNodeForWord.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        // Same like search but return true if you find all chars even if they don't
        // form a word.
        TreeNode lastNodeForWord = getLastNodeForPrefix(prefix);
        return lastNodeForWord != null;
    }

    private TreeNode getLastNodeForPrefix(String prefix) {
        TreeNode current = this.root;
        int n = prefix.length();
        for (int i = 0; i < n; i++) {
            TreeNode next = current.children[prefix.charAt(i) - 'a'];
            if (next != null) {
                current = next;
            } else {
                return null;
            }
        }
        return current;
    }
}

class TreeNode {
    public Character character;
    //Using a fixed array of size 26 is better than using a Hashmap
    public TreeNode[] children = new TreeNode[26];
    //public Map<Character, TreeNode> children = new HashMap();
    public boolean isEndOfWord;

    public TreeNode(Character c) {
        this.character = c;

    }

    public TreeNode() {
    }

    public void addChild(TreeNode child) {
        this.children[child.character - 'a'] = child;
        //this.children.put(child.character, child);
    }

    public void setIsEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}