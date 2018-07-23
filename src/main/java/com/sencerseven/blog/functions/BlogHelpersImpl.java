package com.sencerseven.blog.functions;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Service
public class BlogHelpersImpl implements BlogHelpers {
    @Override
    public String toSlug(String input) {

        if (input != null) {
            String norm = Normalizer.normalize(input, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(norm).replaceAll("").replace(" ", "-").toLowerCase();

        }
        return null;
    }
}
