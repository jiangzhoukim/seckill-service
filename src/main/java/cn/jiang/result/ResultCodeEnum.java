package cn.jiang.result;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author wangyiwen
 * @version 1.0 Created in 2019-10-17 11:17
 */
public enum ResultCodeEnum {
    RATE_LIMIT(20000, ""),
    SUCCESS(200, "成功"),
    FAIL(500, "服务器异常"),
    NOT_FOUND(404, "对象找不到了"),
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    REQUEST_BODY_IS_MISSING(1005, "requestBody is missing"),
    UPDATE_FAIL(1018, "更新失败，请重试"),
    INSERT_FAIL(1012, "插入失败，请重试"),
    PART_FAIL(1013, "部分成功，请检查数据"),
    // 第三方 900000 起步
    CODE_IS_INVALID(900001, "验证码错误"),
    SMS_SEND_FAIL(900002, "短信发送失败"),
    SMS_TEMPLATE_DELETE_FAIL(900003, "短信模版删除失败"),
    SMS_TEMPLATE_UPDATE_FAIL(900004, "短信模版修改失败"),
    SMS_TEMPLATE_QUERY_FAIL(900005, "短信模版查询失败"),
    SMS_TEMPLATE_INSERT_FAIL(900006, "短信模版申请失败"),
    ////////////////////////////////////////////////////////////////
    SUB_ADMIN_INVALID(1011, "子用户不存在!"),

    FLASH_HAVE_PRODUCT(1219, "商品已经绑定在秒杀中"),
    BARGAIN_HAVE_PRODUCT(1220, "商品已经绑定在砍单中"),
    GROUP_HAVE_PRODUCT(1221, "商品已经绑定在拼团中"),
    STAGE_GROUP_HAVE_PRODUCT(1222, "商品已经绑定在阶段拼团中"),
    PACKAGE_HAVE_PRODUCT(1223, "商品已经绑定在套餐中"),
    BANNER_HAVE_PRODUCT(1224, "商品已经绑定在橱窗或广告栏中"),
    //关于用户401000起步
    USER_INVALID(401001, "用户不存在!"),
    PASSWORD_INVALID(401002, "账户密码错误!"),
    OLD_PASSWORD_INVALID(401003, "原密码错误!"),
    OLD_PHONE_CANT_EQUAL_NEW_PHONE(401004, "换绑手机号与原手机号不能相同!"),
    USER_IS_BAN(401005, "该用户已经被冻结!"),
    PHONE_IS_BAN(401006, "该手机号已经被冻结!"),
    PHONE_IS_REGISTERED(401007, "该手机号已经注册!"),
    PHONE_IS_NOT_REGISTERED(401008, "该手机号未注册!"),
    USERNAME_IS_REGISTERED(401009, "该用户名已经注册!"),
    PASSWORD_NOT_EQUALS_CONFIRM_PASSWORD(401010, "两次输入的密码不一致!"),
    Old_PASSWORD_ERROR(401011, "旧密码错误!"),
    PASSWORD_NOT_SETTING(401012, "密码还未设置，请使用第三方登录或者忘记密码!"),
    LOGIN_FAIL(401013, "登录失败!"),
    MANAGER_IS_RELATED(401014, "该店长已被关联!"),
    MERCHANT_IS_BAN(401015, "该商家已经被禁用，请联系平台人员!"),
    USER_IS_BIND(401016, "该用户已经绑定三方账户!"),
    USER_TOKEN_EXPIRED(401, "User token expired!"),
    USER_CERT_NOT_EXIST(2001, "购物车商品记录不存在!"),
    USER_CERT_QUANTITY_IS_MIN(2002, "购物车商品库存不能再减少了!"),
    //用户其他 402000 起步
    USER_CANT_RECHARGE(402001, "无法充值！"),
    USER_INVITE_CODE_NOT_EXIST(402002, "用户邀请码不存在！"),
    USER_COLLECTION_NOT_EXIST(402003, "用户收藏不存在！"),
    USER_COLLECTION_IS_EXIST(402004, "已收藏！"),
    USER_ADDRESS_NOT_EXIST(402005, "用户地址不存在"),
    USER_LEVEL_PERMISSION_NOT_ENOUGH(402006, "您的社员等级不满足条件，请先去充值！"),
    PHONE_NUMBER_NOT_PATTERN(402007, "手机号不正确"),
    ////////////////////////////////////////////////////////////////
    STOCK_OUT(50000, "库存超出，无法出库"),
    STOCK_NO_ZERO(50001, "有占用库存，无法清零"),
    SKU_STOCK_NOT_ENOUGH(50002, "库存不足!"),
    BRAND_REPEAT(50003, "不能添加重复分类"),
    CAN_NOT_DELETE(50004, "该品牌已存在关联商品，无法删除"),
    LIMIT_IS_EXIST(50005, "该限制条件已存在"),
    NUMBER_EXITS(50006, "该货号已经存在，请修改。"),
    TAG_CAN_NOT_DELETE(50007, "该标签已存在关联商品，无法删除。"),
    SUPPLIER_STATUS_NOT_SUCCESS(50008, "供货商未通过审核，无法向此供货商借款。"),
    REMARK_IS_NOT_VAILD(50009, "申请说明不符合要求。"),
    TITLE_IS_NOT_VAILD(50010, "标题不符合要求。"),
    CONTENT_IS_NOT_VAILD(50011, "内容不符合要求。"),
    CAN_NOT_BE_NULL(50012, "请完善填写的信息内容。"),
    QUOTA_OUT(50013, "授信额度不足，无法借款。"),
    DEPARTMENT_CAN_NOT_DELETE(50014, "该部门下仍有员工，无法删除。"),
    TAG_IS_EXIST(50015, "标签名已存在!"),
    LOGISTICS_IS_BIND(50016, "物流模板已经绑定在商品，无法删除!"),
    MERCHANT_NAME_IS_EXIST(1101, "店铺名称已存在!"),
    MERCHANT_NOT_EXIST(1102, "该商户不存在"),
    MERCHANT_AUDIT_ALREADY_APPLY(1117, "申请已经提交!"),
    MERCHANT_AUDIT_ALREADY_SUCCESS(1118, "申请已经通过!"),
    ROLE_IS_NOT_ALLOWED(1119, "只有消费者合作社成员才能申请成为店长!"),
    ENABLE_MONEY_IS_NOT_ENOUGH(1120, "可提现金额不正确!"),
    LOGISTICS_RECORD_IS_EXIST(1121, "物流单号已存在，请重新检查物流单号填写是否正确!"),
    INVITE_USER_IS_NOT_ENOUGH(1122, "您邀请的社员不足120人，无法进行转正操作!"),
    EXCEL_IS_ERROR(1123, "excel格式错误!"),
    MERCHANT_ROLE_ALREADY_EXIST(1201, "该角色已存在!"),
    ORDER_CANCEL_FAIL(500001, "订单取消失败!"),
    ORDER_CONFIRM_PRODUCT_FAIL(500002, "订单确认收货失败,可能已经确认收货!"),
    ORDER_TO_REVIEW_FAIL(500003, "去评价失败!"),
    ORDER_TO_REVIEW_FAIL_BY_STATUS(500004, "该订单状态不可评价!"),
    ORDER_NOT_EXIST(500005, "订单不存在!"),
    ORDER_CANT_DELETE(500006, "该订单不可删除!"),
    PAY_ORDER_NOT_EXIST(500007, "支付订单不存在!"),
    PAY_ORDER_IS_ALREADY_PAID(500008, "该订单已支付!"),
    PAY_TYPE_NOT_EXIST(500009, "该支付方式不存在！"),
    PAY_FAIL(500010, "支付失败！"),
    ORDER_FAIL(500011, "下单失败"),
    PAY_PRICE_CANT_EQUALS_ZERO(500012, "支付金额不能小于或等于0，请再添加一些商品！"),
    EPAY_FAIL(500013, "支付生成失败!"),
    PRE_ORDER_FAIL(500014, "预支付失败！"),
    JSAPI_OPENID_NOT_EXIST(500015, "openId 不能为空"),
    ADD_PRODUCT_FAIL(301001, "该商品已经存在，不可重复添加"),
    ADD_SKU_FAIL(301002, "该商品sku已经存在，不可重复添加"),
    UPDATE_PRODUCT_FAIL(301003, "更新商品失败，不存在该商品"),
    DELETE_PRODUCT_FAIL(301004, "删除商品失败，不存在该商品"),
    DELETE_ATTR_FAIL(301005, "删除属性失败，已关联商品"),
    DELETE_CATEGORY_FAIL(301006, "删除分类失败，已关联商品"),
    DELETE_CONTENTCATEGORY_FAIL(301007, "删除分类失败，已关联文章内容"),
    DELETE_SKU_FAIL(301008, "删除商品sku失败，不存在该sku"),
    UPDATE_SKU_FAIL(301009, "更新商品sku失败，不存在该sku"),
    GET_ORDER_CONFIRMATION_FAIL(301010, "获取订单确认信息失败!"),
    ORDER_PRODUCT_LOST(7002, "订单中有商品不存在,请刷新重试"),
    ORDER_PRODUCT_SKU_LOST(7003, "订单中有商品sku不存在,请刷新重试"),
    ORDER_PRODUCT_STOCK_OUT(7003, "订单中商品库存不足"),
    REFUND_IS_ALREADY_EXIST(8001, "已经申请过退货服务"),
    REFUND_NOT_EXIST(8002, "退款信息不存在"),
    REFUND_CANT_CANCEL(8003, "该退款不可撤销"),
    EXCHANGE_IS_ALREADY_EXIST(8004, "已经申请过换货服务"),
    EXCHANGE_NOT_EXIST(8005, "换货信息不存在"),
    EXCHANGE_CANT_CANCEL(8006, "该换货不可撤销"),
    EXCHANGE_CANT_CONFIRM(8007, "该换货不可确认完成"),
    ITEM_ADJUST_THREE_TIMES(8008, "该商品改价次数已经达到三次，不允许继续改价。"),
    REFUND_ORDER_STATUS_ERROR(8009, "退换货订单状态异常，操作失败。"),
    REFUND_AGREE_FAIL(8010, "同意失败，请先在店铺保障填写退货地址。"),
    REFUND_STATUS_CANT_REVIEWED(8011, "当前退款状态无法评价！"),
    REFUND_NEED_RETURN_STATUS_CANT_REVIEWED(8011, "当前退货状态无法评价！"),
    EXCHANGE_STATUS_CANT_REVIEWED(8012, "当前换货状态无法评价！"),
    REFUND_EXCHANGE_IS_REVIEWED(8013, "已评价!"),
    ORDER_STATUS_NOT_ADJUST(8014, "订单状态不对，无法改价。"),
    ORDER_TYPE_NOT_ADJUST(8015, "订单类型不对，无法改价"),
    MERCHANT_WITHDRAW_HAVE(8016, "您有待处理的提现申请，请等待处理完成，再提交提现。"),
    MERCHANT_WITHDRAW_POINT_FAIL(8017, "提现失败，请重试。"),
    MERCHANT_WITHDRAW_AUDIT_FAIL(8018, "审核失败，请重试。"),
    //优惠券
    COUPON_USED(300001, "优惠券已使用!"),
    COUPON_NOT_EXIST(300002, "优惠券不存在"),
    COUPON_NOT_ON_SALE(300003, "优惠券已下架"),
    COUPON_CANT_RECEIVE(300004, "该时间段无法领取"),
    COUPON_IS_NOT_ENOUGH(300005, "优惠券抢光了"),
    COUPON_IS_RECEIVED(300006, "你已经领取过该优惠券!"),
    COUPON_TIME_WREONG(300007, "优惠劵有效时间必须在领取时间之后"),
    USER_COUPON_NOT_EXIST(303001, "优惠券不存在！"),
    USER_COUPON_CANT_USE(303002, "优惠券不可使用！"),
    USER_COUPON_IS_USED(303003, "优惠券已使用"),
    USER_COUPON_IS_EXPIRE(303004, "优惠期已经过期"),
    USER_COUPON_IS_NOT_PLATFORM_COUPON(303005, "优惠券不是平台优惠券！"),
    USER_COUPON_PLATFORM_LIMIT(303006, "不满足平台优惠券限制条件！"),
    USER_COUPON_PLATFORM_NOT_FULL_MONEY(303007, "不满足平台优惠券满减条件！"),
    USER_COUPON_MERCHANT_NOT_FULL_MONEY(303008, "不满足商家优惠券满减条件！"),
    POINT_IS_NOT_ENOUGH(10001, "积分不足！"),
    POINT_ACCOUNT_IS_BAN(10003, "积分账户无法使用，请先完成邀请任务或者充值成为消费者合作社会员！"),
    POINT_ACCOUNT_IS_NOT_EXIST(10002, "积分账户不存在！"),
    WALLET_IS_NOT_ENOUGH(10101, "钱包余额不足！"),
    WALLET_ACCOUNT_IS_NOT_EXIST(10102, "钱包账户不存在！"),
    APP_VERSION_NOT_EXIST(110001, "没有版本信息！"),
    PRODUCT_NOT_EXIST(120001, "商品已下架或已删除!"),
    PUSH_FAIL(140001, "推送失败"),
    LOGISTICS_MSG_QUERY_FAIL(13001, "物流信息查询失败或不存在！"),
    COOPERATIVE_POINT_NOT_EXIST(14001, "该积分商品不存在!"),
    POINT_EXCHANGE_FAIL(14002, "兑换失败"),
    POINT_EXCHANGE_OUT(14003, "兑换失败,积分不足"),
    CUSTOMER_ADMIN_EXIST(14004, "该账号已经绑定客服了，无法绑定其他客服。"),
    DAY_OUT(14005, "请在指定日期兑换积分"),
    //marketing 200000 起
    CODE_IS_NOT_EXIST(200001, "兑换码不存在"),
    CODE_IS_NOT_START(200002, "兑换尚未开始"),
    CODE_IS_END(200003, "兑换已结束"),
    USER_USED_CODE(200004, "您已使用过该兑换码"),
    RED_PACKET_IS_NOT_START(200005, "红包雨活动尚未开始"),
    RED_PACKET_IS_FINISH(200006, "红包雨活动已结束"),
    USER_HAS_JOIN(200007, "您已达到本次活动的领取上限"),
    RED_PACKET_IS_LIMIT_ONE(200008, "已存在进行中的红包雨活动"),
    PAY_ID_IS_USED(200009, "本次支付已经参与过活动了"),
    RED_PACKET_IS_NULL(200010, "暂无红包雨活动"),
    PAY_TYPE_CAN_NOT_JOIN(200011, "本次订单类型不参与红包活动"),
    PAY_FAILD(200012, "本次支付尚未成功"),
    CODE_IS_USED(200013, "该兑换码已使用"),
    //拼团 201000 起
    ADD_GROUP_FAIL(201001, "添加拼团失败，已经存在拼团"),
    UPDATE_GROUP_FAIL(201002, "该商品的拼团为优先团，请加入优先团"),
    GROUP_DOWN(201003, "团购商品已下架"),
    GROUP_NOT_EXIST(201004, "团购不存在！"),
    GROUP_USER_JOIN_NOT_EXIST(201031, "该用户的拼团不存在！"),
    GROUP_USER_JOIN_EXPIRE(201032, "该用户的拼团已过期！"),
    GROUP_USER_JOIN_IS_FULL(201033, "拼团已满，请加入别的拼团"),
    GROUP_USER_JOIN_JOINED(201034, "你已经加入该拼团!"),
    GROUP_RECORD_NOT_EXIST(201035, "拼团记录不存在！"),
    GROUP_USER_EXCEED_RESTRICTED_QUANTITY(201035, "超过拼团购买数量限制！"),
    GROUP_PRODUCT_SKU_NOT_EXIST(201061, "拼团 sku 不存在!"),
    GROUP_PRODUCT_SKU_STOCK_NOT_ENOUGH(201062, "该品类库存数量不足！"),
    GROUP_PRODUCT_IS_STARTING(201063, "已经有正在进行的拼团！"),
    GROUP_IS_NOT_PRIORITY(201064, "该拼团不是优先团！"),
    GROUP_PRODUCT_STOCK_NOT_ENOUGH(201065, "剩余可开团库存数量不足！"),
    GROUP_PRODUCT_NOT_EXIST(201066, "拼团商品不存在!"),
    //秒杀 202000 起
    FLASH_SALE_DOWN(202001, "秒杀商品已下架"),
    FLASH_SALE_FAIL(202002, "抢购失败"),
    FLASH_SALE_STOCK_NOT_ENOUGH(202003, "秒杀库存不足！"),
    FLASH_SALE_LOCK_ERROR(202008, "秒杀未抢到，请刷新重试。"),
    FLASH_SALE_PRODUCT_SKU_NOT_EXIST(202004, "秒杀商品规格不存在！"),
    FLASH_SALE_PRODUCT_IS_ORDER(202005, "秒杀只能下单一次！"),
    FLASH_SALE_PRODUCT_IS_OUT_BUY_NUM(202006, "超出限购数量！"),
    INITIAL_IS_NOT_VAILD(202007, "初始销量不能大于或等于库存数量！"),
    BARGAIN_FAIL(203001, "砍价失败"),
    BARGAIN_REPEAT(203002, "已经砍过"),
    BARGAIN_FINISH(203003, "砍价已完成，无需再次砍价"),
    BARGAIN_DOWN(203005, "砍价商品已下架"),
    BARGAIN_JOIN_FALSE(203006, "加入砍价失败"),
    BARGAIN_EXCEED_BUY_NUM(203011, "超过砍单购买数量限制！"),
    BARGAIN_STOCK_IS_NOT_ENOUGH(203012, "砍单商品库存不足"),
    BARGAIN_IS_JOINED(203013, "砍单已经加入过了！"),
    BARGAIN_IS_SKU_NOT_EXIST(203020, "砍单商品不存在"),
    //不可修改code，前端有依赖
    BARGAIN_HELP_REPEAT_FINAL(203014, "已经砍过"),
    BARGAIN_SELF_FINAL(203015, "自己的砍单不可帮砍!"),
    BARGAIN_USER_JOIN_NOT_SUCCESS(203016, "砍单还未完成！"),
    BARGAIN_USER_JOIN_INVALID(203017, "砍单已失效！"),
    BARGAIN_USER_JOIN_IS_ORDER(203018, "已经下单，去支付！"),
    BARGAIN_USER_ID_NOT_EQUAL(203019, "不是该用户的砍单！"),
    BARGAIN_USER_JOIN_NOT_EXIST(203020, "用户砍单不存在！"),
    WITHDRAW_CONFIRM_FAIL(400001, "提现处理失败,请重试!"),
    PAYENT_ERROR(400002,"转帐发起失败!")

    ;

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    Integer getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), "code");
        if (field == null) {
            return null;
        }
        try {
            field.setAccessible(true);
            return Integer.parseInt(field.get(this).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static ResultCodeEnum valueOfEnum(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("DisplayedEnum value should not be null");
        }
        ResultCodeEnum[] enums = ResultCodeEnum.class.getEnumConstants();
        for (ResultCodeEnum t : enums) {
            if (t.getValue().equals(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("cannot parse integer: " + value + " to " + ResultCodeEnum.class.getName());
    }
}
