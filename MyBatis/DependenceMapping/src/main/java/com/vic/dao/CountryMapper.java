package com.vic.dao;

import com.vic.pojo.Country;

public interface CountryMapper {
    Country selectCountryByCid(Integer cid);

    Country selectCountryByCid_delay(Integer cid);

    Country selectProvincialByCid(Integer cid);

}
