package biz.intelix.focuX.followup.model.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final Collection<? extends GrantedAuthority> authorization;
    private final String username;

    public JwtResponse(Collection<? extends GrantedAuthority> authorization, String username, String jwttoken) {
        this.jwttoken = jwttoken;
        this.authorization = authorization;
        this.username = username;
    }

   public String getToken() {
       return this.jwttoken;
   }

    public Collection<? extends GrantedAuthority> getAuthorization() {
        return authorization;
    }

    public String getUsername() {
        return username;
    }

}
