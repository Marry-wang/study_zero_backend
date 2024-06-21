package come.demo.domain.libraryManagement.book.entry.vo;

import lombok.Data;

import java.util.Date;

/**
 * 图书类别汇总
 *
 * @TableName book_type_summary
 */
@Data
public class BookTypeSummaryVo {
    /**
     *
     */
    private Integer id;

    /**
     * 图书类别
     */
    private String bookTypeName;

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