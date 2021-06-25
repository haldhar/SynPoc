package com.order.orderdemo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
