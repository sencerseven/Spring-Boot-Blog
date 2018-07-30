package com.sencerseven.blog.functions;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class BlogHelpersImpl implements BlogHelpers {
    @Override
    public String toSlug(String input) {

        if (input != null) {
            String norm = Normalizer.normalize(input, Normalizer.Form.NFD);
            norm = norm.toUpperCase(Locale.ENGLISH);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(norm).replaceAll("").replace(" ", "-").toLowerCase(Locale.ENGLISH);

        }
        return null;
    }
}
