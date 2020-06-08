package org.ms.matrix.sdk.supper;

import org.ms.matrix.sdk.supper.inter.config.IConfig;
import org.ms.matrix.sdk.supper.inter.config.IConfigAdapter;
import org.ms.matrix.sdk.supper.inter.data.IData;
import org.ms.matrix.sdk.supper.inter.data.IDataAdapter;
import org.ms.matrix.sdk.supper.inter.listener.MatrixListener;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.matrix.sdk.supper.inter.room.IRoomAdapter;
import org.ms.matrix.sdk.supper.inter.room.IRooms;
import org.ms.matrix.sdk.supper.inter.room.IRoomsAdapter;
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


    private static IData data;

    public static IData getData() {

        if (data == null) {
            try {
                Class<?> aClass = Class.forName("org.ms.matrix.sdk.impl.data.DataImpl");
                if (aClass != null) {
                    try {
                        Object o = aClass.newInstance();
                        if (o != null) {
                            if (o instanceof IData) {
                                data = (IData) o;

                                return data;
                            }
                        } else {
                            data = new IDataAdapter();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        data = new IDataAdapter();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        data = new IDataAdapter();
                    }
                } else {
                    data = new IDataAdapter();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                data = new IDataAdapter();
            }
        }
        return data;
    }

    private static IRooms rooms;

    public static IRooms getRooms() {

        if (rooms == null) {
            try {
                Class<?> aClass = Class.forName("org.ms.matrix.sdk.impl.room.RoomsImpl");
                if (aClass != null) {
                    try {
                        Object o = aClass.newInstance();
                        if (o != null) {
                            if (o instanceof IRooms) {
                                rooms = (IRooms) o;

                                return rooms;
                            }
                        } else {
                            rooms = new IRoomsAdapter();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        rooms = new IRoomsAdapter();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        rooms = new IRoomsAdapter();
                    }
                } else {
                    rooms = new IRoomsAdapter();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                rooms = new IRoomsAdapter();
            }
        }
        return rooms;
    }

    public static MatrixListener matrixListener;

    public static void addListener(MatrixListener listener) {
        matrixListener = listener;
    }
}
