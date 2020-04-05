package com.github.peacetrue.vocabulary;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.*;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyModify extends OperatorCapableImpl<String> {

    private static final long serialVersionUID = 0L;

    /** 词语 */
    private Long id;
    /** 名称 */
    private String name;
    /** 解释 */
    private String explanation;

}
