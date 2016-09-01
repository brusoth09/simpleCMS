package com.cms.app.utils;

/**
 * Created by burusothman on 8/31/16.
 */
public enum PostStatus {
    Draft,
    ReadyPublish,
    Reject,
    Published,
    Archived;

    private static PostStatus[] vals = values();
    public PostStatus next(){
        return vals[(this.ordinal()+1) % vals.length];
    }
}
