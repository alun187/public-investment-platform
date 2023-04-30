package com.cuidl.pip.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cuidl.pip.core.dto.ExcelDictDto;
import com.cuidl.pip.core.mapper.DictMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cuidl
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictListener extends AnalysisEventListener<ExcelDictDto> {

    private DictMapper mapper;

    private List<ExcelDictDto> list = new ArrayList<>();

    private static final int BATCH_NUM = 5;

    public ExcelDictListener(DictMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void invoke(ExcelDictDto data, AnalysisContext analysisContext) {
        log.info("解析数据， {}", data);
        list.add(data);
        if (list.size() >= BATCH_NUM) {
            saveBatch();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveBatch();
        log.info("所有数据解析完毕");
    }

    private void saveBatch() {
        mapper.insertBatch(list);
    }
}
