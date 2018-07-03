package com.itboy.demo.base;

import java.util.List;

/**
 * 描述：多条目类型接口
 * 作者： ITBOY
 * 日期： 2017/12/31
 * 时间： 22:10
 */

public interface MultiItemInterface<DATA> {
    int getLayoutId(List<DATA> items, int position);
}
