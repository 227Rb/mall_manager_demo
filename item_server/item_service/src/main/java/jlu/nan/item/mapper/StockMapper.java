package jlu.nan.item.mapper;

import jlu.nan.item.pojo.Stock;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<Stock>,InsertListMapper<Stock>, SelectByIdListMapper<Stock,Long>
                                    , DeleteByIdListMapper<Stock,Long> { }
