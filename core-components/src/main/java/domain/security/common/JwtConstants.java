package domain.security.common;

import util.Strings;

public class JwtConstants {

    public static final String AUTHORITIES_KEY = "authorities";
    public static final String USER_ID_KEY = "user_id";

    public static final String BEARER_TYPE = "Bearer";
    public static final String BEARER_PREFIX = BEARER_TYPE + Strings.SPACE;
}