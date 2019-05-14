package com.mymovieapi.mymovieapi.dao;
import java.io.Serializable;

public interface PersistentObject<T> extends Serializable {

    T getId();
}