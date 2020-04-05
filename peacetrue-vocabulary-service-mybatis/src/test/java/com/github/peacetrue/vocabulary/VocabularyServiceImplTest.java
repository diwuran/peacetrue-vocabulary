package com.github.peacetrue.vocabulary;

import com.github.peacetrue.spring.util.BeanUtils;
import com.google.common.collect.Sets;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Map;


/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestServiceVocabularyAutoConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VocabularyServiceImplTest {

    //TODO 需要修改值
    public static final java.lang.Long INIT_ID = 1L;
    public static final VocabularyAdd RECORD_ADD = EnhancedRandom.random(VocabularyAdd.class);

    static {
        RECORD_ADD.setOperatorId(EnhancedRandom.random(String.class));
    }

    @Autowired
    private VocabularyService vocabularyService;

    @Test
    public void test_01_query() {
        Page<VocabularyVO> vos = vocabularyService.query(new VocabularyQuery(), new PageRequest(0, 1));
        Assert.assertEquals(vos.getTotalElements(), 1);
    }

    @Test
    public void test_02_get() {
        VocabularyVO vo = vocabularyService.get(new VocabularyGet(INIT_ID));
        Assert.assertNotNull(vo);
    }

    @Test
    public void test_03_add() {
        VocabularyVO vo = vocabularyService.add(RECORD_ADD);
        ReflectionAssert.assertReflectionEquals(vo, vocabularyService.get(new VocabularyGet(vo.getId())));
    }

    @Test
    public void test_04_modify() {
        VocabularyVO vo = vocabularyService.get(new VocabularyGet(INIT_ID));
        VocabularyModify modify = EnhancedRandom.random(VocabularyModify.class);
        modify.setId(vo.getId());
        modify.setOperatorId(EnhancedRandom.random(String.class));
        int count = vocabularyService.modify(modify);
        Assert.assertEquals(count, 1);
        vo = vocabularyService.get(new VocabularyGet(INIT_ID));
        Map<String, Object> modifyMap = BeanUtils.map(modify);
        Map<String, Object> voMap = BeanUtils.map(vo);
        Sets.SetView<String> difference = Sets.difference(modifyMap.keySet(), voMap.keySet());
        difference.immutableCopy().forEach(modifyMap::remove);
        difference = Sets.difference(voMap.keySet(), modifyMap.keySet());
        difference.immutableCopy().forEach(voMap::remove);
        Assert.assertEquals(modifyMap, voMap);
    }

    @Test
    public void test_05_delete() {
        int count = vocabularyService.delete(new VocabularyDelete(INIT_ID));
        Assert.assertEquals(count, 1);
    }
}