package com.movie.web.plugins.cut;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

public class NLPCut {
    public static void main(String[] args) {
        String s = "肖申克的救赎";
        System.out.println(join(s, ""));
    }

    public static String join(String s, String seq) {
        NLPTokenizer.ANALYZER.enableCustomDictionary(false);
        String result = "";
        List<Term> terms = NLPTokenizer.segment(s);
        for (Term term :
                terms) {
            if (isStopWord(term.word)) continue;
            result += " " + seq + term.word;
        }
        return result.substring(1);
    }

    private static boolean isStopWord(String s) {
        String[] stop_words = {"的"};
        for (String word :
                stop_words) {
            if (word.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
