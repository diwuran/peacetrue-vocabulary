package com.github.peacetrue.vocabulary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xiayx
 */
@Controller
@RequestMapping(value = "${peacetrue.vocabulary.base-path:/vocabularys}")
public class VocabularyController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VocabularyService vocabularyService;

    @ResponseBody
    @PostMapping
    public VocabularyVO add(VocabularyAdd params) {
        logger.info("新增信息[{}]", params);
        return vocabularyService.add(params);
    }

    @ResponseBody
    @GetMapping(params = "page")
    public Page<VocabularyVO> query(VocabularyQuery params,
                                    @PageableDefault(sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable) {
        logger.info("分页查询信息[{}]", params);
        return vocabularyService.query(params, pageable);
    }

    @ResponseBody
    @GetMapping(value = "${peacetrue.vocabulary.urls.query:}")
    public List<VocabularyVO> query(VocabularyQuery params) {
        logger.info("查询信息[{}]", params);
        return vocabularyService.query(params);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public VocabularyVO get(@PathVariable Long id) {
        logger.info("获取信息[{}]详情", id);
        return vocabularyService.get(new VocabularyGet(id));
    }

    @ResponseBody
    @PutMapping("/{id}")
    public int modify(VocabularyModify params) {
        logger.info("修改信息[{}]", params);
        return vocabularyService.modify(params);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        logger.info("删除信息[{}]", id);
        return vocabularyService.delete(new VocabularyDelete(id));
    }


}
