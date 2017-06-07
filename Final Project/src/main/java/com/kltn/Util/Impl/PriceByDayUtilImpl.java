package com.kltn.Util.Impl;

import com.kltn.Util.PriceByDayUtil;
import com.kltn.Util.TempPriceByDay;
import com.kltn.entities.PriceByDay;
import org.springframework.beans.factory.annotation.Autowired;

//imports as static

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Created by TinNguyen on 6/7/17.
 */
@Repository("PriceByDayUtil")
public class PriceByDayUtilImpl implements PriceByDayUtil {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<TempPriceByDay> getAllLatestPriceByDayDistinct() {

        final Aggregation aggregation = newAggregation(
                sort(Sort.Direction.DESC, "date"),
                group("productId").first("_id").as("pricebydayId")

        );
        //Convert the aggregation result into a List
        AggregationResults<TempPriceByDay> groupResults
                = mongoTemplate.aggregate(aggregation, "pricebyday",TempPriceByDay.class);
        List<TempPriceByDay> result = groupResults.getMappedResults();
        return result;
    }
}
