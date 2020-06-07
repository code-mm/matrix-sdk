package org.ms.matrix.sdk.model.response;

public class RegisteredResponse {


    /**
     * user_id : @maohuawei3:matrix.mhw828.com
     * home_server : matrix.mhw828.com
     * access_token : MDAxZmxvY2F0aW9uIG1hdHJpeC5taHc4MjguY29tCjAwMTNpZGVudGlmaWVyIGtleQowMDEwY2lkIGdlbiA9IDEKMDAzMGNpZCB1c2VyX2lkID0gQG1hb2h1YXdlaTM6bWF0cml4Lm1odzgyOC5jb20KMDAxNmNpZCB0eXBlID0gYWNjZXNzCjAwMjFjaWQgbm9uY2UgPSBTLG03cE1ib1IrcEFUJklsCjAwMmZzaWduYXR1cmUgWOOTbG-_40KryjngAm3hh3qFwk9_AkPX0n8Rl-0NFeAK
     * device_id : OPVMBCCTNP
     */

    private String user_id;
    private String home_server;
    private String access_token;
    private String device_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHome_server() {
        return home_server;
    }

    public void setHome_server(String home_server) {
        this.home_server = home_server;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
