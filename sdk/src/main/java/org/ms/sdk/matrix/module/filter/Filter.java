package org.ms.sdk.matrix.module.filter;

import java.util.List;

// 过滤规则

//POST /_matrix/client/r0/user/{userId}/filter
public class Filter {

    /**
     * account_data : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"]}
     * event_fields : ["string"]
     * event_format : client
     * presence : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"]}
     * room : {"account_data":{"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]},"ephemeral":{"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]},"include_leave":true,"not_rooms":["string"],"rooms":["string"],"state":{"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]},"timeline":{"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]}}
     */

    private AccountDataBean account_data;
    private String event_format;
    private PresenceBean presence;
    private RoomBean room;
    private List<String> event_fields;

    public AccountDataBean getAccount_data() {
        return account_data;
    }

    public void setAccount_data(AccountDataBean account_data) {
        this.account_data = account_data;
    }

    public String getEvent_format() {
        return event_format;
    }

    public void setEvent_format(String event_format) {
        this.event_format = event_format;
    }

    public PresenceBean getPresence() {
        return presence;
    }

    public void setPresence(PresenceBean presence) {
        this.presence = presence;
    }

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public List<String> getEvent_fields() {
        return event_fields;
    }

    public void setEvent_fields(List<String> event_fields) {
        this.event_fields = event_fields;
    }

    public static class AccountDataBean {
        /**
         * limit : 0
         * not_senders : ["string"]
         * not_types : ["string"]
         * senders : ["string"]
         * types : ["string"]
         */

        private int limit;
        private List<String> not_senders;
        private List<String> not_types;
        private List<String> senders;
        private List<String> types;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<String> getNot_senders() {
            return not_senders;
        }

        public void setNot_senders(List<String> not_senders) {
            this.not_senders = not_senders;
        }

        public List<String> getNot_types() {
            return not_types;
        }

        public void setNot_types(List<String> not_types) {
            this.not_types = not_types;
        }

        public List<String> getSenders() {
            return senders;
        }

        public void setSenders(List<String> senders) {
            this.senders = senders;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
    }

    public static class PresenceBean {
        /**
         * limit : 0
         * not_senders : ["string"]
         * not_types : ["string"]
         * senders : ["string"]
         * types : ["string"]
         */

        private int limit;
        private List<String> not_senders;
        private List<String> not_types;
        private List<String> senders;
        private List<String> types;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<String> getNot_senders() {
            return not_senders;
        }

        public void setNot_senders(List<String> not_senders) {
            this.not_senders = not_senders;
        }

        public List<String> getNot_types() {
            return not_types;
        }

        public void setNot_types(List<String> not_types) {
            this.not_types = not_types;
        }

        public List<String> getSenders() {
            return senders;
        }

        public void setSenders(List<String> senders) {
            this.senders = senders;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
    }

    public static class RoomBean {
        /**
         * account_data : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]}
         * ephemeral : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]}
         * include_leave : true
         * not_rooms : ["string"]
         * rooms : ["string"]
         * state : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]}
         * timeline : {"limit":0,"not_senders":["string"],"not_types":["string"],"senders":["string"],"types":["string"],"contains_url":true,"include_redundant_members":true,"lazy_load_members":true,"not_rooms":["string"],"rooms":["string"]}
         */

        private AccountDataBeanX account_data;
        private EphemeralBean ephemeral;
        private boolean include_leave;
        private StateBean state;
        private TimelineBean timeline;
        private List<String> not_rooms;
        private List<String> rooms;

        public AccountDataBeanX getAccount_data() {
            return account_data;
        }

        public void setAccount_data(AccountDataBeanX account_data) {
            this.account_data = account_data;
        }

        public EphemeralBean getEphemeral() {
            return ephemeral;
        }

        public void setEphemeral(EphemeralBean ephemeral) {
            this.ephemeral = ephemeral;
        }

        public boolean isInclude_leave() {
            return include_leave;
        }

        public void setInclude_leave(boolean include_leave) {
            this.include_leave = include_leave;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public TimelineBean getTimeline() {
            return timeline;
        }

        public void setTimeline(TimelineBean timeline) {
            this.timeline = timeline;
        }

        public List<String> getNot_rooms() {
            return not_rooms;
        }

        public void setNot_rooms(List<String> not_rooms) {
            this.not_rooms = not_rooms;
        }

        public List<String> getRooms() {
            return rooms;
        }

        public void setRooms(List<String> rooms) {
            this.rooms = rooms;
        }

        public static class AccountDataBeanX {
            /**
             * limit : 0
             * not_senders : ["string"]
             * not_types : ["string"]
             * senders : ["string"]
             * types : ["string"]
             * contains_url : true
             * include_redundant_members : true
             * lazy_load_members : true
             * not_rooms : ["string"]
             * rooms : ["string"]
             */

            private int limit;
            private boolean contains_url;
            private boolean include_redundant_members;
            private boolean lazy_load_members;
            private List<String> not_senders;
            private List<String> not_types;
            private List<String> senders;
            private List<String> types;
            private List<String> not_rooms;
            private List<String> rooms;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public boolean isContains_url() {
                return contains_url;
            }

            public void setContains_url(boolean contains_url) {
                this.contains_url = contains_url;
            }

            public boolean isInclude_redundant_members() {
                return include_redundant_members;
            }

            public void setInclude_redundant_members(boolean include_redundant_members) {
                this.include_redundant_members = include_redundant_members;
            }

            public boolean isLazy_load_members() {
                return lazy_load_members;
            }

            public void setLazy_load_members(boolean lazy_load_members) {
                this.lazy_load_members = lazy_load_members;
            }

            public List<String> getNot_senders() {
                return not_senders;
            }

            public void setNot_senders(List<String> not_senders) {
                this.not_senders = not_senders;
            }

            public List<String> getNot_types() {
                return not_types;
            }

            public void setNot_types(List<String> not_types) {
                this.not_types = not_types;
            }

            public List<String> getSenders() {
                return senders;
            }

            public void setSenders(List<String> senders) {
                this.senders = senders;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }

            public List<String> getNot_rooms() {
                return not_rooms;
            }

            public void setNot_rooms(List<String> not_rooms) {
                this.not_rooms = not_rooms;
            }

            public List<String> getRooms() {
                return rooms;
            }

            public void setRooms(List<String> rooms) {
                this.rooms = rooms;
            }
        }

        public static class EphemeralBean {
            /**
             * limit : 0
             * not_senders : ["string"]
             * not_types : ["string"]
             * senders : ["string"]
             * types : ["string"]
             * contains_url : true
             * include_redundant_members : true
             * lazy_load_members : true
             * not_rooms : ["string"]
             * rooms : ["string"]
             */

            private int limit;
            private boolean contains_url;
            private boolean include_redundant_members;
            private boolean lazy_load_members;
            private List<String> not_senders;
            private List<String> not_types;
            private List<String> senders;
            private List<String> types;
            private List<String> not_rooms;
            private List<String> rooms;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public boolean isContains_url() {
                return contains_url;
            }

            public void setContains_url(boolean contains_url) {
                this.contains_url = contains_url;
            }

            public boolean isInclude_redundant_members() {
                return include_redundant_members;
            }

            public void setInclude_redundant_members(boolean include_redundant_members) {
                this.include_redundant_members = include_redundant_members;
            }

            public boolean isLazy_load_members() {
                return lazy_load_members;
            }

            public void setLazy_load_members(boolean lazy_load_members) {
                this.lazy_load_members = lazy_load_members;
            }

            public List<String> getNot_senders() {
                return not_senders;
            }

            public void setNot_senders(List<String> not_senders) {
                this.not_senders = not_senders;
            }

            public List<String> getNot_types() {
                return not_types;
            }

            public void setNot_types(List<String> not_types) {
                this.not_types = not_types;
            }

            public List<String> getSenders() {
                return senders;
            }

            public void setSenders(List<String> senders) {
                this.senders = senders;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }

            public List<String> getNot_rooms() {
                return not_rooms;
            }

            public void setNot_rooms(List<String> not_rooms) {
                this.not_rooms = not_rooms;
            }

            public List<String> getRooms() {
                return rooms;
            }

            public void setRooms(List<String> rooms) {
                this.rooms = rooms;
            }
        }

        public static class StateBean {
            /**
             * limit : 0
             * not_senders : ["string"]
             * not_types : ["string"]
             * senders : ["string"]
             * types : ["string"]
             * contains_url : true
             * include_redundant_members : true
             * lazy_load_members : true
             * not_rooms : ["string"]
             * rooms : ["string"]
             */

            private int limit;
            private boolean contains_url;
            private boolean include_redundant_members;
            private boolean lazy_load_members;
            private List<String> not_senders;
            private List<String> not_types;
            private List<String> senders;
            private List<String> types;
            private List<String> not_rooms;
            private List<String> rooms;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public boolean isContains_url() {
                return contains_url;
            }

            public void setContains_url(boolean contains_url) {
                this.contains_url = contains_url;
            }

            public boolean isInclude_redundant_members() {
                return include_redundant_members;
            }

            public void setInclude_redundant_members(boolean include_redundant_members) {
                this.include_redundant_members = include_redundant_members;
            }

            public boolean isLazy_load_members() {
                return lazy_load_members;
            }

            public void setLazy_load_members(boolean lazy_load_members) {
                this.lazy_load_members = lazy_load_members;
            }

            public List<String> getNot_senders() {
                return not_senders;
            }

            public void setNot_senders(List<String> not_senders) {
                this.not_senders = not_senders;
            }

            public List<String> getNot_types() {
                return not_types;
            }

            public void setNot_types(List<String> not_types) {
                this.not_types = not_types;
            }

            public List<String> getSenders() {
                return senders;
            }

            public void setSenders(List<String> senders) {
                this.senders = senders;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }

            public List<String> getNot_rooms() {
                return not_rooms;
            }

            public void setNot_rooms(List<String> not_rooms) {
                this.not_rooms = not_rooms;
            }

            public List<String> getRooms() {
                return rooms;
            }

            public void setRooms(List<String> rooms) {
                this.rooms = rooms;
            }
        }

        public static class TimelineBean {
            /**
             * limit : 0
             * not_senders : ["string"]
             * not_types : ["string"]
             * senders : ["string"]
             * types : ["string"]
             * contains_url : true
             * include_redundant_members : true
             * lazy_load_members : true
             * not_rooms : ["string"]
             * rooms : ["string"]
             */

            private int limit;
            private boolean contains_url;
            private boolean include_redundant_members;
            private boolean lazy_load_members;
            private List<String> not_senders;
            private List<String> not_types;
            private List<String> senders;
            private List<String> types;
            private List<String> not_rooms;
            private List<String> rooms;

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public boolean isContains_url() {
                return contains_url;
            }

            public void setContains_url(boolean contains_url) {
                this.contains_url = contains_url;
            }

            public boolean isInclude_redundant_members() {
                return include_redundant_members;
            }

            public void setInclude_redundant_members(boolean include_redundant_members) {
                this.include_redundant_members = include_redundant_members;
            }

            public boolean isLazy_load_members() {
                return lazy_load_members;
            }

            public void setLazy_load_members(boolean lazy_load_members) {
                this.lazy_load_members = lazy_load_members;
            }

            public List<String> getNot_senders() {
                return not_senders;
            }

            public void setNot_senders(List<String> not_senders) {
                this.not_senders = not_senders;
            }

            public List<String> getNot_types() {
                return not_types;
            }

            public void setNot_types(List<String> not_types) {
                this.not_types = not_types;
            }

            public List<String> getSenders() {
                return senders;
            }

            public void setSenders(List<String> senders) {
                this.senders = senders;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }

            public List<String> getNot_rooms() {
                return not_rooms;
            }

            public void setNot_rooms(List<String> not_rooms) {
                this.not_rooms = not_rooms;
            }

            public List<String> getRooms() {
                return rooms;
            }

            public void setRooms(List<String> rooms) {
                this.rooms = rooms;
            }
        }
    }
}
