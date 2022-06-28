package com.shorturl.util;

import com.shorturl.model.URLEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class URLValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return URLEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        URLEntity urlEntity = (URLEntity) target;
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
        // String regex1 = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(urlEntity.getPrimaryUrl().toLowerCase());
        if (!matcher.find() || urlEntity.getPrimaryUrl().equals("")) {
            errors.rejectValue("primaryUrl", "", "Не валидный url, повторите попытку ввода :)");
        }
    }
}
