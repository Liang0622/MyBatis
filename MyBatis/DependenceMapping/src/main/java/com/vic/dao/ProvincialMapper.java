package com.vic.dao;

import com.vic.pojo.Provincial;

import java.util.Set;

public interface ProvincialMapper {
    Set<Provincial> selectProvincialByCid(Integer cid);
    Provincial selectProvincialByPid(Integer pid);
}
