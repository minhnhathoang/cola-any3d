package domain.security.common;

import util.Strings;

public class JwtConstants {

    public static String BEARER_TYPE = "Bearer";

    public static final String BEARER_PREFIX = BEARER_TYPE + Strings.SPACE;

    public static final String AUTHORITIES_KEY = "authorities";
}