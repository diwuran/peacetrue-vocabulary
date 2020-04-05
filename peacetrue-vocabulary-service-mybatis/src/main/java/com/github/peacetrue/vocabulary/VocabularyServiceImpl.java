package com.github.peacetrue.vocabulary;

import com.github.pagehelper.PageHelper;
import com.github.peacetrue.mybatis.dynamic.MybatisDynamicUtils;
import com.github.peacetrue.pagehelper.PageHelperUtils;
import com.github.peacetrue.spring.util.BeanUtils;
import com.github.peacetrue.util.EntityNotFoundException;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.peacetrue.vocabulary.VocabularyDynamicSqlSupport.*;

/**
 * @author xiayx
 */
@Service
public class VocabularyServiceImpl implements VocabularyService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VocabularyMapper vocabularyMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public VocabularyVO add(VocabularyAdd params) {
        logger.info("新增信息[{}]", params);
        Vocabulary vocabulary = new Vocabulary();
        BeanUtils.copyProperties(params, vocabulary);
        logger.debug("保存信息[{}]", vocabulary);
        int count = vocabularyMapper.insertSelective(vocabulary);
        logger.debug("共影响[{}]条记录", count);
        VocabularyVO vo = BeanUtils.map(vocabulary, VocabularyVO.class);
        eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
        return vo;
    }

    @Override
    public Page<VocabularyVO> query(@Nullable VocabularyQuery params, @Nullable Pageable pageable, String... projection) {
        logger.info("分页查询信息[{}]", params);
        if (params == null) params = VocabularyQuery.DEFAULT;
        if (pageable == null) pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "createdTime"));
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Vocabulary> entities = vocabularyMapper.selectByExample()
                .where()
                .and(id, MybatisDynamicUtils.getConditionWhenPresent(params.getIds(), SqlBuilder::isIn))
                .and(id, SqlBuilder.isEqualToWhenPresent(params.getId()))
                .and(name, SqlBuilder.isLikeWhenPresent(MybatisDynamicUtils.likeValue(params.getName())))
                .and(explanation, SqlBuilder.isLikeWhenPresent(MybatisDynamicUtils.likeValue(params.getExplanation())))
                .orderBy(MybatisDynamicUtils.orders(vocabulary, pageable.getSort()))
                .build().execute();
        logger.debug("共取得'{}'条记录", entities.size());
        if (entities.isEmpty()) return new PageImpl<>(Collections.emptyList());

        List<VocabularyVO> vos = entities.stream()
                .map(vocabulary -> BeanUtils.map(vocabulary, VocabularyVO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(vos, pageable, PageHelperUtils.getTotal(entities));
    }

    @Override
    public List<VocabularyVO> query(VocabularyQuery params, String... projection) {
        return this.query(params, new PageRequest(0, Integer.MAX_VALUE), projection).getContent();
    }

    @Override
    public VocabularyVO get(VocabularyGet params, String... projection) {
        logger.info("获取符合条件[{}]的信息", params);
        return this.getOptional(params)
                .orElseThrow(() -> new EntityNotFoundException(Vocabulary.class, "id", params.getId()));
    }

    protected Optional<VocabularyVO> getOptional(VocabularyGet params) {
        return vocabularyMapper.selectByExample()
                .where(id, SqlBuilder.isEqualTo(params.getId()))
                .build().execute().stream()
                .reduce((l, r) -> r)
                .map(vocabulary -> BeanUtils.map(vocabulary, VocabularyVO.class));
    }


    @Override
    public int modify(VocabularyModify params) {
        logger.info("修改信息[{}]", params);
        VocabularyVO vo = this.get(BeanUtils.map(params, VocabularyGet.class));
        Vocabulary vocabulary = new Vocabulary();
        BeanUtils.copyProperties(params, vocabulary);
        int count = vocabularyMapper.updateByPrimaryKeySelective(vocabulary);
        logger.debug("共影响[{}]条记录", count);
        eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
        return count;
    }

    @Override
    public int delete(VocabularyDelete params) {
        logger.info("删除信息[{}]", params);
        VocabularyVO vo = this.get(BeanUtils.map(params, VocabularyGet.class));
        int count = vocabularyMapper.deleteByPrimaryKey(params.getId());
        logger.debug("共影响[{}]条记录", count);
        eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
        return count;
    }
}
