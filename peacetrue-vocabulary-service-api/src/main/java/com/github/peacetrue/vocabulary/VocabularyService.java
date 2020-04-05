package com.github.peacetrue.vocabulary;

import com.github.peacetrue.result.exception.ResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author xiayx
 */
public interface VocabularyService {

    /** 新增 */
    VocabularyVO add(VocabularyAdd params) throws ResultException;

    /** 分页查询 */
    Page<VocabularyVO> query(@Nullable VocabularyQuery params, @Nullable Pageable pageable, String... projection) throws ResultException;

    /** 全量查询 */
    List<VocabularyVO> query(VocabularyQuery params, String... projection) throws ResultException;

    /** 获取 */
    VocabularyVO get(VocabularyGet params, String... projection) throws ResultException;

    /** 修改 */
    int modify(VocabularyModify params) throws ResultException;

    /** 删除 */
    int delete(VocabularyDelete params) throws ResultException;
}
