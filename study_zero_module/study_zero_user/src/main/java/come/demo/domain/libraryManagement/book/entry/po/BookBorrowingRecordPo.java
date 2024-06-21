package come.demo.domain.libraryManagement.book.entry.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书借阅记录
 * @TableName book_borrowing_record
 */
@TableName(value ="book_borrowing_record")
@Data
public class BookBorrowingRecordPo implements Serializable {
    /**
     * 图书id
     */
    @TableField(value = "book_id")
    private Integer bookId;

    /**
     * 借出时间
     */
    @TableField(value = "borrowing_time")
    private Date borrowingTime;

    /**
     * 归还时间
     */
    @TableField(value = "return_time")
    private Date returnTime;

    /**
     * 借书人
     */
    @TableField(value = "borrowing_by")
    private Integer borrowingBy;

    /**
     * 状态(0未还，1已还)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 记录人员
     */
    @TableField(value = "manager_by")
    private Integer managerBy;

    /**
     * 
     */
    @TableField(value = "create_by")
    private Integer createBy;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_by")
    private Integer updateBy;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}