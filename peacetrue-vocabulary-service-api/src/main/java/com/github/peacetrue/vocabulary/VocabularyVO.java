package com.github.peacetrue.vocabulary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
public class VocabularyVO implements Serializable {

    private static final long serialVersionUID = 0L;

    /** 词语 */
    private Long id;
    /** 名称 */
    private String name;
    /** 解释 */
    private String explanation;
}
