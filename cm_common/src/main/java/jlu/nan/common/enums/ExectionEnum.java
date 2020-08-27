package jlu.nan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExectionEnum {
    INPUT_DATA_ERROR(400,"输入数据有误"),
    CATEGORY_NOT_FIND(404,"哎呀~找不到分类,可以联系管理员"),
    BUSINESS_NOT_FIND(404,"查询不到对应的商户"),
    BUSINESS_DELETE_ERROR(500,"商户删除错误"),
    BUSINESS_UPDATE_ERROR(500,"商户信息修改错误"),
    BUSINESS_SAVE_ERROR(500,"商户添加错误"),
    FILE_TYPE_NOT_ALLOW(400,"不支持该上传类型"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    FIND_SPEC_GROUP_ERROR(404,"无法查找对应分类的规则组"),
    FIND_SPEC_PARAM_ERROR(404,"无法查找对应分类的规则"),
    GOODS_NO_FIND(404,"查找不到对应商品"),
    GOODS_SPU_NO_FIND(404,"查找不到对应商品的详细信息"),
    GOODS_SKU_NO_FIND(404,"查找不到对应商品的SKU信息"),
    GOODS_STOCK_NO_FIND(404,"查找不到对应商品的库存信息"),
    SKU_ADD_STOCK_ERROR(500,"拼接Vo对象失败"),
    GOODS_SAVE_ERROR(500,"添加商品失败"),
    GOODS_UPDATE_ERROR(500,"商品更新失败"),
    SPU_SAVE_ERROR(500,"添加商品通用规格失败"),
    SPU_UPDATE_ERROR(500,"商品通用规格更新失败"),
    SKU_SAVE_ERROR(500,"添加商品特征规格失败"),
    SKU_UPDATE_ERROR(500,"商品特征规格更新失败"),
    STOCK_SAVE_ERROR(500,"添加商品库存失败"),
    STOCK_UPDATE_ERROR(500,"商品库存更新失败"),
    DEL_SKU_ERROR(500,"商品规格删除失败"),
    DEL_SKU_STOCK_ERROR(500,"商品规格删除失败"),
    DEL_GOODS_ERROR(500,"商品删除失败"),
    DEL_GOODS_SPU_ERROR(500,"商品详情删除失败"),
    GOODS_UPPER_ERROR(500,"商品上架失败"),
    GOODS_LOWER_ERROR(500,"商品下架失败"),
    INVALID_USER_DARA_TYPE(400,"无效的用户数据"),
    ;
    private Integer code;
    private String msg;
}
