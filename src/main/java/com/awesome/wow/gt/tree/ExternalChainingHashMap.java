package com.awesome.wow.gt.tree;

import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

public class ExternalChainingHashMap<K, V> {
    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input value can't be null");
        }

        int index = Math.abs(key.hashCode() % table.length);
        boolean resized = false;
        if (((double) (size + 1) / table.length) > MAX_LOAD_FACTOR) {
            if (table[index] == null) {
                resized = true;
                resizeBackingTable(table.length * 2 + 1);
            } else {
                ExternalChainingMapEntry<K, V> duplicateKey = getEntry(key, index);
                if (duplicateKey == null) {
                    resized = true;
                    resizeBackingTable(table.length * 2 + 1);
                }
            }
        }

        int newIndex = resized ? Math.abs(key.hashCode() % table.length) : index;
        ExternalChainingMapEntry<K, V> entry = new ExternalChainingMapEntry<>(key, value);

        if (table[newIndex] == null) {
            table[newIndex] = entry;
            size++;
            return null;
        }

        // table[newIndex] != null
        ExternalChainingMapEntry<K, V> duplicateKey = getEntry(key, newIndex);

        V returnedValue = null;
        if (duplicateKey == null) {
            entry.setNext(table[index]);
            table[index] = entry;
            size++;
        } else {
            returnedValue = duplicateKey.getValue();
            duplicateKey.setValue(value);
        }
        return returnedValue;
    }

    @Nullable
    private ExternalChainingMapEntry<K, V> getEntry(K key, int index) {
        ExternalChainingMapEntry<K, V> p = table[index];
        while (p != null) {
            if (!p.getKey().equals(key)) {
                p = p.getNext();
                continue;
            }
            break;
        }
        return p;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Input value can't be null");
        }

        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            throw new NoSuchElementException("The key is not in the map");
        }

        ExternalChainingMapEntry<K, V> dummy = new ExternalChainingMapEntry<>(null, null);
        dummy.setNext(table[index]);
        ExternalChainingMapEntry<K, V> pointer = dummy;
        ExternalChainingMapEntry<K, V> entry = null;
        while (pointer != null && pointer.getNext() != null) {
            if (!pointer.getNext().getKey().equals(key)) {
                pointer = pointer.getNext();
                continue;
            }
            entry = pointer;
            break;
        }

        if (entry == null) {
            throw new NoSuchElementException("The key is not in the map");
        }

        ExternalChainingMapEntry<K, V> removed = entry.getNext();
        entry.setNext(entry.getNext().getNext());
        table[index] = dummy.getNext();
        size--;
        return removed.getValue();
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key The key to search for in the map. You may assume that the
     *            key is never null.
     * @return true if the key is contained within the map, false otherwise.
     */
    public boolean containsKey(K key) {
        int index = Math.abs(key.hashCode() % table.length);

        if (table[index] == null) {
            return false;
        }

        ExternalChainingMapEntry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.getNext();
        }

        return false;
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key The key to search for in the map. You may assume that the
     *            key is never null.
     * @return The value associated with the given key.
     * @throws java.util.NoSuchElementException If the key is not in the map.
     */
    public V get(K key) {
        int index = Math.abs(key.hashCode() % table.length);

        if (table[index] == null) {
            throw new NoSuchElementException("The key is not in the map");
        }

        ExternalChainingMapEntry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                break;
            }
            entry = entry.getNext();
        }

        if (entry == null) {
            throw new NoSuchElementException("The key is not in the map");
        } else {
            return entry.getValue();
        }
    }


    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        ExternalChainingMapEntry<K, V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }

            ExternalChainingMapEntry<K, V> pointer = table[i];
            while (pointer != null) {
                ExternalChainingMapEntry<K, V> entry = new ExternalChainingMapEntry<>(pointer.getKey(), pointer.getValue());
                int index = Math.abs(entry.getKey().hashCode() % newTable.length);

                if (newTable[index] == null) {
                    newTable[index] = entry;
                } else {
                    entry.setNext(newTable[index]);
                    newTable[index] = entry;
                }

                pointer = pointer.getNext();
            }
        }
        table = newTable;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
