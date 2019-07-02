package com.coding.sales.output;

import org.junit.Assert;
import org.junit.Test;

import com.coding.sales.FileUtils;
import com.coding.sales.entiy.Activity;
import com.coding.sales.entiy.Member;
import com.coding.sales.entiy.Order;
import com.coding.sales.entiy.OrderItem;
import com.coding.sales.entiy.Product;
import com.coding.sales.input.OrderCommand;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepresentationTest {

    private Date createTime = new Date();
    private String orderId = "000001";
    private String memberNo = "6236609999";
    private String memberName = "马丁";
    private int memberPointsIncreased = 2;
    private int memberPoints = 10001;
    private String newMemberType = "金卡";
    private BigDecimal totalPrice = BigDecimal.valueOf(404.40);
    private BigDecimal totalDiscountPrice = new BigDecimal("20.0");
    private BigDecimal receivables = new BigDecimal("384.40");
    private String oldMemberType = "普卡";
    
    private static Map activityMap = new HashMap();
    private static Map memberMap = new HashMap();
    private static Map productMap = new HashMap();
    static {
    	activityMap.put("001", new Activity("001", "每满3000元减350"));
    	activityMap.put("002", new Activity("002", "每满2000元减30"));
    	activityMap.put("003", new Activity("003", "每满1000元减10"));
    	activityMap.put("004", new Activity("004", "第3件半价（买3件及以上，其中1件半价）"));
    	activityMap.put("005", new Activity("005", "满3送1（买4件及以上，其中1件免费）"));
    	memberMap.put("6236609999", new Member("马丁", "普卡", "6236609999", BigDecimal.valueOf(9860)));
    	memberMap.put("6630009999", new Member("王立", "金卡", "6630009999", BigDecimal.valueOf(48860)));
    	memberMap.put("8230009999", new Member("李想", "白金卡", "8230009999", BigDecimal.valueOf(98860)));
    	memberMap.put("9230009999", new Member("张三", "钻石卡", "9230009999", BigDecimal.valueOf(198860)));
    	productMap.put("001001", new Product("001001", "世园会五十国钱币册", BigDecimal.valueOf(998.00),"","0"));
    	productMap.put("001002", new Product("001002", "2019北京世园会纪念银章大全40g", BigDecimal.valueOf(1380.00),"","1"));
    	productMap.put("003001", new Product("003001", "招财进宝", BigDecimal.valueOf(1580.00),"","1"));
    	productMap.put("003002", new Product("003002", "水晶之恋", BigDecimal.valueOf(980.00),"004|005",""));
    	productMap.put("002002", new Product("002002", "中国经典钱币套装", BigDecimal.valueOf(998.00),"002|003","0"));
    	productMap.put("002001", new Product("002001", "守扩之羽比翼双飞4.8g", BigDecimal.valueOf(1080.00),"004|005","1"));
    	productMap.put("002003", new Product("002003", "中国银象棋12g", BigDecimal.valueOf(698.00),"001|002|003","1"));
    };
    
    
    
    @Test
    public void should_print_order_in_correct_format() {
        OrderRepresentation orderRepresentation = new OrderRepresentation(
                orderId, createTime,
                memberNo, memberName, oldMemberType, newMemberType,
                memberPointsIncreased, memberPoints,
                getOrderItems(), totalPrice,
                getDiscountItems(), totalDiscountPrice, receivables, getPayments(), getDiscountCards());
        String actualPrintResult = orderRepresentation.toString();

        String expectedResult = getExpectedTitle() + getExpectedBody();

        Assert.assertEquals(expectedResult, actualPrintResult);
    }

    @Test
    public void should_print_order_when_buy_one_001001() {
    	String absolutePath = getResourceFilePath("sample_command.json");
        String commandString = FileUtils.readFromFile(absolutePath);
        OrderCommand command = OrderCommand.from(commandString);
        
        Product product = (Product)productMap.get("001001");
        OrderItem orderItem = new OrderItem(product,1);
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        orderItemList.add(orderItem);
        Order order = new Order(command.getOrderId(), orderItemList);
        
        Assert.assertEquals(BigDecimal.valueOf(998.00), order.getTotalPrice());
        Assert.assertEquals(BigDecimal.valueOf(998.00), order.getReceivablePrice());
        Assert.assertEquals(BigDecimal.valueOf(0), order.getDiscountPrice());
        
        Member member= (Member)memberMap.get("6236609999");
        Assert.assertEquals(new BigDecimal("10858.0"), member.addScore(order.getReceivablePrice()));
    }
    
    @Test
    public void should_print_order_when_buy_two_001001() {
    	String absolutePath = getResourceFilePath("sample_command.json");
        String commandString = FileUtils.readFromFile(absolutePath);
        OrderCommand command = OrderCommand.from(commandString);
        
        Product product = (Product)productMap.get("001001");
        OrderItem orderItem = new OrderItem(product,2);
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        orderItemList.add(orderItem);
        Order order = new Order(command.getOrderId(), orderItemList);
        
        Assert.assertEquals(BigDecimal.valueOf(1996.00), order.getTotalPrice());
        Assert.assertEquals(BigDecimal.valueOf(1996.00), order.getReceivablePrice());
        Assert.assertEquals(BigDecimal.valueOf(0), order.getDiscountPrice());
        
        Member member= (Member)memberMap.get("6630009999");
        Assert.assertEquals(new BigDecimal("51854.00"), member.addScore(order.getReceivablePrice()));
    }
    
    private List<String> getDiscountCards() {
        return Arrays.asList("9折券");
    }

    private List<OrderItemRepresentation> getOrderItems() {
        return Arrays.asList(
                    new OrderItemRepresentation("0001", "AAA", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2)),
                    new OrderItemRepresentation("0002", "BBB", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2))
            );
    }

    private List<DiscountItemRepresentation> getDiscountItems() {
        return Arrays.asList(
                new DiscountItemRepresentation("0001", "AAA", new BigDecimal(10.0)),
                new DiscountItemRepresentation("0002", "BBB", new BigDecimal(10.0))
        );
    }

    private List<PaymentRepresentation> getPayments() {
        return Arrays.asList(new PaymentRepresentation("账户余额", new BigDecimal(184.4)));
    }

    private String getExpectedTitle() {
        return String.format("方鼎银行贵金属购买凭证\n\n" +
                        "销售单号：%s 日期：%s\n" +
                        "客户卡号：%s 会员姓名：%s 客户等级：%s 累计积分：%d\n",
                orderId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime),
                memberNo, memberName, newMemberType, memberPoints);
    }

    private String getExpectedBody() {
        return "\n商品及数量           单价         金额\n" +
                "(0001)AAAx2, 101.10, 202.20\n" +
                "(0002)BBBx2, 101.10, 202.20\n" +
                "合计：404.40\n" +
                "\n" +
                "优惠清单：\n" +
                " (0001)AAA: -10.00\n" +
                " (0002)BBB: -10.00\n" +
                "优惠合计：20.00\n" +
                "\n" +
                "应收合计：384.40\n" +
                "收款：\n" +
                " 9折券\n" +
                " 账户余额：184.40\n\n" +
                "客户等级与积分：\n" +
                " 新增积分：2\n" +
                " 恭喜您升级为金卡客户！\n";
    }
    
    private String getResourceFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
    
    private List<OrderItemRepresentation> getOrderItems(Product product) {
        return Arrays.asList(
                    new OrderItemRepresentation("0001", product.getProductName(), product.getPrice(), new BigDecimal("1"), product.getPrice())
           );
    }
}