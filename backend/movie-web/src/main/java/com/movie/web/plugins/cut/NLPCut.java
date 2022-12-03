package com.movie.web.plugins.cut;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

public class NLPCut {
    public static void main(String[] args) {
        String s = "肖申克的救赎";
        System.out.println(addPlus(s));
    }
    public static String addPlus(String s) {
        NLPTokenizer.ANALYZER.enableCustomDictionary(false);
        String result="";
        List<Term> terms = NLPTokenizer.segment(s);
        for (Term term :
                terms) {
            result += " +" + term.word;
        }
        return result.substring(1);
    }
}
