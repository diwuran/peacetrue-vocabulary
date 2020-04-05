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
public class VocabularyQuery extends OperatorCapableImpl<String> {

    public static final VocabularyQuery DEFAULT = new VocabularyQuery();

    private static final long serialVersionUID = 0L;

    /** 主键集合 */
    private Long[] ids;
    /** 词语 */
    private Long id;
    /** 名称 */
    private String name;
    /** 解释 */
    private String explanation;

    public VocabularyQuery(Long id) {
        this.id = id;
    }

    public VocabularyQuery(Long[] ids) {
        this.ids = ids;
    }


}
