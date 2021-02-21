package org.sid.secservice.sec.Pyload;

public class JWTUtil {

    public static final String SECRET="MySecret1234";
    public static final String AUTH_HEADER="Authorization";
    public static final String PREFIX="Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN=5*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN=15*60*1000;

}
