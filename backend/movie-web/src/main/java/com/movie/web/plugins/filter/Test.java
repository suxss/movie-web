package com.movie.web.plugins.filter;

import com.movie.web.plugins.filter.proto.BloomFilterProtos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Test {

    private static final String STORE_PATH = "bloom_filter";

    public static void main(String[] args) throws IOException {

        // Serialize to bloom_filter to disk.
        {
            BloomFilter bloomFilter = new BloomFilter(10, 0.05);
            System.out.println(bloomFilter.toString());
            bloomFilter.addString("Hello World");
            bloomFilter.addLong(2L);
            bloomFilter.addLong(1);
            bloomFilter.add("ni".getBytes(StandardCharsets.UTF_8));
            BloomFilterProtos.BloomFilter.Builder builder = BloomFilterProtos.BloomFilter.newBuilder();
            BloomFilterIO.serialize(builder, bloomFilter);
            builder.build().writeTo(new FileOutputStream(STORE_PATH));
        }


        // read from bloom_filter
        {
            BloomFilterProtos.BloomFilter bloomFilterProtos =
                    BloomFilterProtos.BloomFilter.parseFrom(new FileInputStream(STORE_PATH));
            BloomFilter bloomFilter =  BloomFilterIO.deserialize(bloomFilterProtos);
            System.out.println(bloomFilter.testString("Hello World")); // true
            System.out.println(bloomFilter.testLong(2)); // true
            System.out.println(bloomFilter.testLong(2L)); // true
            System.out.println(bloomFilter.testLong(1)); // true
            System.out.println(bloomFilter.testLong(1L)); // true
            System.out.println(bloomFilter.test("ni".getBytes(StandardCharsets.UTF_8))); // true
            System.out.println(bloomFilter.test("hao".getBytes(StandardCharsets.UTF_8))); // false
            System.out.println(bloomFilter.testString("hello world")); // false
            System.out.println(bloomFilter.testLong(3)); // false
        }
    }
}

