package com.movie.web.plugins.filter;

import com.movie.web.plugins.filter.proto.BloomFilterProtos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MovieFilter {
    private BloomFilter bloomFilter;
    private String path = "bloom_filter";
    public MovieFilter(long expectedEntries, double fpp){
        bloomFilter = new BloomFilter(expectedEntries, fpp);
    }

    public MovieFilter(String path) throws IOException {
        this.path = path;
        BloomFilterProtos.BloomFilter bloomFilterProtos =
        BloomFilterProtos.BloomFilter.parseFrom(new FileInputStream(path));
        bloomFilter =  BloomFilterIO.deserialize(bloomFilterProtos);
    }

    public void add(String s){
        bloomFilter.addString(s);
    }

    public boolean test(String s) {
        return bloomFilter.testString(s);
    }

    public void save() throws IOException {
        BloomFilterProtos.BloomFilter.Builder builder = BloomFilterProtos.BloomFilter.newBuilder();
        BloomFilterIO.serialize(builder, bloomFilter);
        builder.build().writeTo(new FileOutputStream(path));
    }

    public void setPath(String path) {
        this.path = path;
    }
}
