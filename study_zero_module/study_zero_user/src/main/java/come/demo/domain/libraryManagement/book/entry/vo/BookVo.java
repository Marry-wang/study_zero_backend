package come.demo.domain.libraryManagement.book.entry.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 图书
 * @TableName book
 */
@Data
public class BookVo {
    /**
     * bookId
     */
    private Integer bookId;

    /**
     * bookTypeId
     */
    private Integer bookTypeId;

    /**
     * bookTypeName
     */
    private String bookTypeName;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 记录时间
     */
    private Date inTime;

    /**
     * 出版社
     */
    private String press;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图书编码
     */
    private String bookCode;

    /**
     * 
     */
    private Integer createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateBy;

    /**
     * 
     */
    private Date updateTime;
}