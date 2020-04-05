package com.github.peacetrue.vocabulary;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
public class VocabularyAdd extends OperatorCapableImpl<String> {

    private static final long serialVersionUID = 0L;

    /** 名称 */
    private String name;
    /** 解释 */
    private String explanation;

}
