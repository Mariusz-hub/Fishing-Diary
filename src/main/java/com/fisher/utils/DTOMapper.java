package com.fisher.utils;

public interface DTOMapper<F,T>{
    T from(F from);
}