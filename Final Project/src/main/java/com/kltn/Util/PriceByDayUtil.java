package com.kltn.Util;

import com.kltn.entities.PriceByDay;

import java.util.List;

/**
 * Created by TinNguyen on 6/7/17.
 */
public interface PriceByDayUtil {
    List<TempPriceByDay> getAllLatestPriceByDayDistinct();
}
