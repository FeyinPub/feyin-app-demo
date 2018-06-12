package net.feyin.openapi.enumeration;

public enum FeyinCallbackType {
    heartbeat,
    auth_added,
    auth_removed,
    msg_printed,
    device_status;

    public static FeyinCallbackType check(String type) {
        for (FeyinCallbackType callbackType : FeyinCallbackType.values()) {
            if (callbackType.name().equals(type)) {
                return callbackType;
            }
        }
        return null;
    }
}
