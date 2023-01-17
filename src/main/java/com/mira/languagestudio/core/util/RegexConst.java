package com.mira.languagestudio.core.util;

public class RegexConst {
    public static final String IDENTIFIER = "^[a-zA-Z_][a-zA-Z0-9_-]*$";
    public static final String NUMBER = "\\d+(\\.\\d+)?";
    public static final String STRING = "^\".*\"$";
    public static final String CHAR = "^'.*'$";
    public static final String BOOLEAN = "^(true|false)$";
    public static final String NULL = "^null$";
    public static final String TYPE = "^(int|double|float|char|boolean|string|void)$";
    public static final String OPERATOR = "^(\\+|-|\\*|/|\\^|\\%|\\+\\+|\\-\\-|\\=|\\=\\=|\\!\\=|\\<|\\>|\\<\\=|\\>\\=|\\&\\&|\\|\\||\\!|\\~|\\&|\\||\\^|\\<\\<|\\>\\>|\\>\\>\\>|\\<\\<\\<|\\>\\>\\>\\>|\\,|\\:|\\;|\\?|\\.|\\[|\\]|\\(|\\)|\\{|\\}|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\-|\\+|\\=|\\||\\<|\\>|\\/|\\.|\\,|\\;|\\:|\\?|\\!|\\~|\\`|\\'|\\\"|\\s)$";
    public static final String KEYWORD = "^(if|else|while|for|do|break|continue|return|switch|case|default|try|catch|finally|throw|throws|import|package|class|interface)$";
}
