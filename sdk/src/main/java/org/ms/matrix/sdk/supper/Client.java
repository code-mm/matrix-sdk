package org.ms.matrix.sdk.supper;

import org.ms.matrix.sdk.supper.inter.config.IConfig;
import org.ms.matrix.sdk.supper.inter.config.IConfigAdapter;
import org.ms.matrix.sdk.supper.inter.user.IUser;
import org.ms.matrix.sdk.supper.inter.user.IUserAdapter;

public class Client {
    private static IConfig config;

    public static IConfig getConfig() {
        if (config == null) {
            try {
                Class<?> aClass = Class.forName("org.ms.matrix.sdk.impl.config.ConfigImpl");
                if (aClass != null) {
                    try {
                        Object o = aClass.newInstance();
                        if (o != null) {
                            if (o instanceof IConfig) {
                                config = (IConfig) o;

                                return config;
                            }
                        } else {
                            config = new IConfigAdapter();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        config = new IConfigAdapter();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        config = new IConfigAdapter();
                    }
                } else {
                    config = new IConfigAdapter();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                config = new IConfigAdapter();
            }
        }
        return config;
    }


    private static IUser user;

    public static IUser getUser() {
        if (user == null) {
            try {
                Class<?> aClass = Class.forName("org.ms.matrix.sdk.impl.user.UserImpl");
                if (aClass != null) {
                    try {
                        Object o = aClass.newInstance();
                        if (o != null) {
                            if (o instanceof IUser) {
                                user = (IUser) o;

                                return user;
                            }
                        } else {
                            user = new IUserAdapter();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        user = new IUserAdapter();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        user = new IUserAdapter();
                    }
                } else {
                    user = new IUserAdapter();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                user = new IUserAdapter();
            }
        }
        return user;
    }
}
