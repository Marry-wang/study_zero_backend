package come.demo.domain.libraryManagement.book.entry.dto;

import lombok.Data;

@Data
public class BookTypeSummaryDto {

    private Integer id;

    /**
     * 图书类别
     */
    private String bookTypeName;
}
