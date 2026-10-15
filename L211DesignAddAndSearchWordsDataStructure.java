import java.util.List;

class WordDictionary {

    TreeNode root = new TreeNode();

    public WordDictionary() {

    }

    public void addWord(String word) {
        int n = word.length();
        TreeNode current = root;
        int i=0;
        while(i<n){
            TreeNode next = current.children[word.charAt(i) - 'a'];
            if (next != null) {
                current = next;
            } else {
                break;
            }
            i++;
        }
        if (i == n) {
            current.isEndOfWord = true;
        } else {
            while (i < n) {
                TreeNode next = new TreeNode();
                current.children[word.charAt(i) - 'a'] = next;
                current = next;
                i++;
            }
            current.isEndOfWord = true;
        }

    }

    public boolean search(String word) {
        return search(word, root, 0);
    }
    
    private boolean search(String word, TreeNode node,  int index) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }
        if (word.charAt(index) == '.') {
            for (int i = 0; i < node.children.length; i++) {
                TreeNode child = node.children[i];
                if (child != null && search(word, child, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            TreeNode child = node.children[word.charAt(index) - 'a'];
            if (child != null) {
                return search(word, child, index + 1);
            } else {
                return false;
            }
        }
    }
}

class TreeNode {
    public boolean isEndOfWord;
    public TreeNode[] children = new TreeNode[26];

    public TreeNode() {

    }
    
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */