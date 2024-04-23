package domain.security.common;

import lombok.*;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;


@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class AccessToken {

    private String value;

    private RefreshToken refreshToken;

    private Date expiration;

    private String tokenType = JwtConstants.BEARER_TYPE;

    private Set<String> scope;

    private Map<String, Object> additionalInformation = Collections.emptyMap();

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L).intValue() : 0;
    }

    public void setExpiresIn(int delta) {
        setExpiration(new Date(System.currentTimeMillis() + delta));
    }

    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }
}