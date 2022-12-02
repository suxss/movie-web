package com.movie.web.plugins.filter;

import com.movie.web.plugins.filter.proto.BloomFilterProtos;

public class BloomFilterIO {

    public static void serialize(BloomFilterProtos.BloomFilter.Builder builder, BloomFilter bloomFilter) {
        builder.clear();
        builder.setNumHashFunctions(bloomFilter.getNumHashFunctions());
        long[] bitset = bloomFilter.getBitSet();
        for (long l : bitset) {
            builder.addBitset(l);
        }
    }

    public static BloomFilter deserialize(BloomFilterProtos.BloomFilter bloomFilterProtos) {
        if (bloomFilterProtos == null) {
            return null;
        }

        int numFuncs = bloomFilterProtos.getNumHashFunctions();
        long[] bitset = new long[bloomFilterProtos.getBitsetCount()];
        for (int i=0; i<bitset.length; i++) {
            bitset[i] = bloomFilterProtos.getBitset(i);
        }
        return new BloomFilter(bitset, numFuncs);
    }
}
