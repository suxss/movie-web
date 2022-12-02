package com.movie.web.plugins.filter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BloomFilter {
    public static final double DEFAULT_FPP = 0.05;
    private final BitSet bitSet;
    private final int numBits;
    private final int numHashFunctions;

    static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public BloomFilter(long expectedEntries) {
        this(expectedEntries, DEFAULT_FPP);
    }

    public BloomFilter(long expectedEntries, double fpp) {
        expectedEntries = Math.max(expectedEntries, 1);
        checkArgument(fpp > 0.0 && fpp < 1.0, "False positive probability should be > 0.0 & < 1.0");
        int nb = optimalNumOfBits(expectedEntries, fpp);
        // make 'm' multiple of 64
        this.numBits = nb + (Long.SIZE - (nb % Long.SIZE));
        this.numHashFunctions = optimalNumOfHashFunctions(expectedEntries, numBits);
        this.bitSet = new BitSet(numBits);
    }

    /**
     * A constructor to support rebuilding the BloomFilter from a serialized representation.
     *
     * @param bits     the serialized bits
     * @param numFuncs the number of functions used
     */
    public BloomFilter(long[] bits, int numFuncs) {
        super();
        bitSet = new BitSet(bits);
        this.numBits = (int) bitSet.bitSize();
        numHashFunctions = numFuncs;
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    static int optimalNumOfBits(long n, double p) {
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == getClass() &&
                numBits == ((BloomFilter) other).numBits &&
                numHashFunctions == ((BloomFilter) other).numHashFunctions &&
                bitSet.equals(((BloomFilter) other).bitSet);
    }

    @Override
    public int hashCode() {
        return bitSet.hashCode() + numHashFunctions * 5;
    }

    public void add(byte[] val) {
        addBytes(val, 0, val == null ? 0 : val.length);
    }

    public void addBytes(byte[] val, int offset, int length) {
        // We use the trick mentioned in "Less Hashing, Same Performance: Building a Better Bloom Filter"
        // by Kirsch et.al. From abstract 'only two hash functions are necessary to effectively
        // implement a Bloom filter without any loss in the asymptotic false positive probability'

        // Lets split up 64-bit hashcode into two 32-bit hash codes and employ the technique mentioned
        // in the above paper
        long hash64 = val == null ? Murmur3.NULL_HASHCODE :
                Murmur3.hash64(val, offset, length);
        addHash(hash64);
    }

    private void addHash(long hash64) {
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        for (int i = 1; i <= numHashFunctions; i++) {
            int combinedHash = hash1 + (i * hash2);
            // hashcode should be positive, flip all the bits if it's negative
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            int pos = combinedHash % numBits;
            bitSet.set(pos);
        }
    }

    public void addString(String val) {
        if (val == null) {
            add(null);
        } else {
            add(val.getBytes(StandardCharsets.UTF_8));
        }
    }

    public void addLong(long val) {
        addHash(getLongHash(val));
    }

    public void addDouble(double val) {
        addLong(Double.doubleToLongBits(val));
    }

    public boolean test(byte[] val) {
        return testBytes(val, 0, val == null ? 0 : val.length);
    }

    public boolean testBytes(byte[] val, int offset, int length) {
        long hash64 = val == null ? Murmur3.NULL_HASHCODE :
                Murmur3.hash64(val, offset, length);
        return testHash(hash64);
    }

    private boolean testHash(long hash64) {
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        for (int i = 1; i <= numHashFunctions; i++) {
            int combinedHash = hash1 + (i * hash2);
            // hashcode should be positive, flip all the bits if it's negative
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            int pos = combinedHash % numBits;
            if (!bitSet.get(pos)) {
                return false;
            }
        }
        return true;
    }

    public boolean testString(String val) {
        if (val == null) {
            return test(null);
        } else {
            return test(val.getBytes(StandardCharsets.UTF_8));
        }
    }

    public boolean testLong(long val) {
        return testHash(getLongHash(val));
    }

    // Thomas Wang's integer hash function
    // http://web.archive.org/web/20071223173210/http://www.concentric.net/~Ttwang/tech/inthash.htm
    private long getLongHash(long key) {
        key = (~key) + (key << 21); // key = (key << 21) - key - 1;
        key = key ^ (key >> 24);
        key = (key + (key << 3)) + (key << 8); // key * 265
        key = key ^ (key >> 14);
        key = (key + (key << 2)) + (key << 4); // key * 21
        key = key ^ (key >> 28);
        key = key + (key << 31);
        return key;
    }

    public boolean testDouble(double val) {
        return testLong(Double.doubleToLongBits(val));
    }

    public long sizeInBytes() {
        return getBitSize() / 8;
    }

    public int getBitSize() {
        return bitSet.getData().length * Long.SIZE;
    }

    public int getNumHashFunctions() {
        return numHashFunctions;
    }

    public long[] getBitSet() {
        return bitSet.getData();
    }

    @Override
    public String toString() {
        return String.format("numBits: %d, numHashFunctions: %d", numBits, numHashFunctions);
    }

    /**
     * Merge the specified bloom filter with current bloom filter.
     *
     * @param that - bloom filter to merge
     */
    public void merge(BloomFilter that) {
        if (this != that && this.numBits == that.numBits && this.numHashFunctions == that.numHashFunctions) {
            this.bitSet.putAll(that.bitSet);
        } else {
            throw new IllegalArgumentException("BloomFilters are not compatible for merging." +
                    " this - " + this.toString() + " that - " + that.toString());
        }
    }

    public void reset() {
        this.bitSet.clear();
    }

    /**
     * Bare metal bit set implementation. For performance reasons, this implementation does not check
     * for index bounds nor expand the bit set size if the specified index is greater than the size.
     */
    public static class BitSet {
        private final long[] data;

        public BitSet(long bits) {
            this(new long[(int) Math.ceil((double) bits / (double) Long.SIZE)]);
        }

        /**
         * Deserialize long array as bit set.
         *
         * @param data - bit array
         */
        public BitSet(long[] data) {
            assert data.length > 0 : "data length is zero!";
            this.data = data;
        }

        /**
         * Sets the bit at specified index.
         *
         * @param index - position
         */
        public void set(int index) {
            data[index >>> 6] |= (1L << index);
        }

        /**
         * Returns true if the bit is set in the specified index.
         *
         * @param index - position
         * @return - value at the bit position
         */
        public boolean get(int index) {
            return (data[index >>> 6] & (1L << index)) != 0;
        }

        /**
         * Number of bits
         */
        public long bitSize() {
            return (long) data.length * Long.SIZE;
        }

        public long[] getData() {
            return data;
        }

        /**
         * Combines the two BitArrays using bitwise OR.
         */
        public void putAll(BitSet array) {
            assert data.length == array.data.length :
                    "BitArrays must be of equal length (" + data.length + "!= " + array.data.length + ")";
            for (int i = 0; i < data.length; i++) {
                data[i] |= array.data[i];
            }
        }

        /**
         * Clear the bit set.
         */
        public void clear() {
            Arrays.fill(data, 0);
        }

        @Override
        public boolean equals(Object other) {
            return other != null &&
                    other.getClass() == getClass() &&
                    Arrays.equals(data, ((BitSet) other).data);
        }

        @Override
        public int hashCode() {
            int result = 0;
            for (long l : data) {
                result = (int) (result * 13 + l);
            }
            return result;
        }
    }
}
