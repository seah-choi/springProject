package org.fullstack4.springmvc.dto;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private int total_count;
    private int page;
    private int page_size;
    private int page_skip_count;
    private int total_page;
    private int page_block_size;
    private int page_block_start;
    private int page_block_end;
    private boolean prev_page_flag;
    private boolean next_page_flag;

    List<E> dtoList;

    private String[] search_type;
    private String search_word;
    private String search_date1;
    private String search_date2;

    private String linkParams;

    private String sType;

    PageResponseDTO() {}

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO requestDTO, List<E> dtoList, int total_count) {
        log.info("================");
        log.info("PageRequestDTO START");

        this.total_count = total_count;
        this.page = requestDTO.getPage();
        this.page_size = requestDTO.getPage_size();
        this.page_skip_count = (this.page-1)*this.page_size;
        this.total_page = this.total_count > 0 ? (int)Math.ceil(this.total_count/(double)this.page_size) : 1;
        this.page_block_size = requestDTO.getPage_block_size();
        this.page_block_start = (int)(Math.ceil(page / (double)page_block_size) -1 ) * 10 + 1;
        this.page_block_end = (int)Math.ceil(page / (double)page_block_size) * 10;
        this.page_block_end = page_block_end > total_page ? total_page : page_block_end;
        this.prev_page_flag = (this.page_block_start > 1);
        this.next_page_flag = (this.total_page > this.page_block_end);
        this.dtoList = dtoList;
        this.search_type = requestDTO.getSearch_type();
        this.search_word = requestDTO.getSearch_word();
        this.search_date1 = requestDTO.getSearch_date1();
        this.search_date2 = requestDTO.getSearch_date2();

        if(search_type != null) {
            this.sType = Arrays.toString(search_type).replace("[", "").replace("]", "");
        } else {
            this.sType = "";
        }

        StringBuilder sb = new StringBuilder("?page_size="+ this.page_size);
        if(search_type != null ) {
            sb.append("&search_type="+sType+"&search_word="+search_word);
        }
        if(search_date1 != null ) {
            sb.append("&search_date1="+search_date1);
        }
        if(search_date2 != null ) {
            sb.append("&search_date2="+search_date2);
        }

        this.linkParams = sb.toString();

        log.info("PageRequestDTO END");
        log.info("================");
    }

    public int getTotal_page(){
        return (this.total_count > 0 ? (int)Math.ceil(this.total_count/(double)this.page_size) : 1);
    }

    public int getPage_skip_count(){
        return (this.page-1)*page_size;
    }

    public void setPage_block_start(){
        this.page_block_start = ((int)Math.floor(this.page/(double)this.page_block_size)*this.page_block_size) + 1;
    }

    public void setPage_block_end() {
        this.page_block_end = (
                (int)Math.floor(this.page/(double)this.page_block_size)
                        *this.page_block_size
        ) + this.page_block_size;
        this.page_block_end = ( this.total_page > this.page_block_end ? this.page_block_end : this.total_page);
    }
}
