package helvidios.search.web.util;

import java.util.*;

public class Trie {

    private static class TrieNode{
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
    }

    private int size;
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void add(String word) {
        TrieNode current = root;
        for(char c : word.toCharArray()){
            if(!current.children.containsKey(c)){
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }
        current.isEndOfWord = true;

        size++;
    }

    public boolean containsWord(String word){
        if(word.isEmpty()) return false;
        TrieNode current = root;
        for(char c : word.toCharArray()){
            if(!current.children.containsKey(c)){
                return false;
            }
            current = current.children.get(c);
        }
        return current.isEndOfWord;
    }

    public boolean containsPrefix(String prefix){
        if(prefix.isEmpty()) return false;
        TrieNode current = root;
        for(char c : prefix.toCharArray()){
            if(!current.children.containsKey(c)){
                return false;
            }
        }
        return true;
    }

    public List<String> getByPrefix(String prefix) {
        if(!containsPrefix(prefix)) return Arrays.asList();
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        for(char c : prefix.toCharArray()){
            current = current.children.get(c);
        }
        getByPrefix(prefix, words, current);
        return words;
    }

    private void getByPrefix(String word, List<String> words, TrieNode node){
        if(node.isEndOfWord) words.add(word);
        for(char c : node.children.keySet()){
            getByPrefix(word + c, words, node.children.get(c));
        }
    }

    public int size() {
        return size;
    }
    
}