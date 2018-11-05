package tech.wetech.myapp.core.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页返回结果集
 *
 * @author cjbi
 */
@ApiModel("分页返回结果")
public class PageResultSet<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final PageResultSet EMPTY_RESULT = new PageResultSet<>();

    /**
     * 总数
     */
    @ApiModelProperty("总数")
    private int total;
    /**
     * 返回的数据
     */
    @ApiModelProperty("返回的数据")
    private List<T> data;

    public PageResultSet() {
    }

    public PageResultSet(int total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
